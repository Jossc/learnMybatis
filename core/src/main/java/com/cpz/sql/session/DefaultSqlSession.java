package com.cpz.sql.session;

import com.cpz.exception.QueryException;
import com.cpz.executor.DefaultExecutor;
import com.cpz.executor.Executor;
import com.cpz.model.Configuration;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * @author Hikari
 * @version 1.0.0
 * @ClassName DefaultSqlSession.java
 * @createTime 2020年12月17日 22:34:00
 */
@Slf4j
public class DefaultSqlSession<T> implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public List<T> selectList(String statementId, Object... params) throws Exception {
        log.debug("selectList statementId {},params {}", statementId, params);
        Executor executor = new DefaultExecutor();
        if (Objects.isNull(configuration.getMappedStatementMap().get(statementId))) {
            log.error("statementId is null {}", statementId);
            throw new QueryException("mappedStatement is null");
        }
        return executor.query(configuration,
                configuration.getMappedStatementMap().get(statementId), params);
    }

    @Override
    public T selectOne(String statementId, Object... params) throws Exception {
        log.debug("selectOne statementId {},params {}", statementId, params);
        final List<T> selectList = selectList(statementId, params);
        if (selectList.size() == 1) {
            return selectList.get(0);
        } else {
            throw new RuntimeException();
        }
    }
}
