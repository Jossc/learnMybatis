package com.cpz.executor;

import com.cpz.exception.QueryException;
import com.cpz.executor.entity.BoundSql;
import com.cpz.model.Configuration;
import com.cpz.model.MappedStatement;
import com.cpz.utils.GenericTokenParser;
import com.cpz.utils.ParameterMapping;
import com.cpz.utils.ParameterMappingTokenHandler;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Hikari
 * @version 1.0.0
 * @ClassName DefaultExecutor.java
 * @createTime 2020年12月19日 18:06:00
 */
@Slf4j
public class DefaultExecutor implements Executor {
    @Override
    public <T> List<T> query(Configuration configuration, MappedStatement mappedStatement,
                             Object... params) throws Exception {
        log.debug("query configuration {} ,mappedStatement {}, params {}",
                configuration, mappedStatement, params);
        Connection connection = configuration.getDataSource().getConnection();
        if (Objects.isNull(connection)) {
            log.error("connection is null configuration {}", configuration);
            throw new QueryException("connection is null");
        }
        /**
         * sql 有可能是这种形式 select * from xxx where xx =#{} and xxx =#{}
         * 需要把  select * from xxx where xx =#{} and xxx =#{} 换成
         * select * from xxx where xx = ? and xxx = ?
         */
        String sql = mappedStatement.getSql();
        log.debug("query sql {}", sql);
        final BoundSql boundSql = buildBoundSql(sql);
        final PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());
        /**
         * 获取参数类型
         */
        String resultType = mappedStatement.getParameterType();
        Class<?> parameterClass = getClassType(resultType);
        final List<ParameterMapping> mappingList = boundSql.getMappingList();
        /**
         * 解析设置参数
         */
        for (int i = 0; i < mappingList.size(); i++) {
            ParameterMapping parameterMapping = mappingList.get(i);
            String context = parameterMapping.getContent();
            Field declaredField = parameterClass.getDeclaredField(context);
            declaredField.setAccessible(true);
            final Object value = declaredField.get(params[0]);
            preparedStatement.setObject(i + 1, value);
        }
        final ResultSet resultSet = preparedStatement.executeQuery();
        return buildResultSet(resultSet, mappedStatement.getResultType());
    }

    /**
     * 解析绑定sql
     * 替换 替换#{}
     *
     * @param sql
     * @return
     */
    private BoundSql buildBoundSql(String sql) {
        log.debug("buildBoundSql sql {}", sql);
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        String parseSql = genericTokenParser.parse(sql);
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();
        log.debug("buildBoundSql parseSql {}  parameterMappings {}", parseSql, parameterMappings);
        return new BoundSql(parseSql, parameterMappings);
    }

    /**
     * 获取参数类型
     *
     * @param parameterType
     * @return
     */
    private Class<?> getClassType(String parameterType) throws ClassNotFoundException {
        log.debug("getClassType parameterType {}", parameterType);
        if (Objects.isNull(parameterType)) {
            throw new RuntimeException();
        }
        return Class.forName(parameterType);
    }

    /**
     * 封装返回值而
     *
     * @param resultSet
     * @param classPath
     * @return
     */
    private <T> List<T> buildResultSet(ResultSet resultSet, String classPath) throws Exception {
        log.debug("buildResultSet  classPath {}", classPath);
        if (Objects.isNull(resultSet) || Objects.isNull(classPath)) {
            // todo
            throw new RuntimeException();
        }
        Class<?> resultType = getClassType(classPath);
        final Object newInstance = resultType.newInstance();
        List<T> result = new ArrayList<>();
        /**
         *  获取元数据
         *  把指写到对象中
         */
        while (resultSet.next()) {
            final ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                final String columnName = metaData.getColumnName(i);
                Object value = resultSet.getObject(columnName);
                PropertyDescriptor propertyDescriptor =
                        new PropertyDescriptor(columnName, resultType);
                Method method = propertyDescriptor.getWriteMethod();
                method.invoke(newInstance, value);
            }
            result.add((T) newInstance);
        }
        return result;
    }
}
