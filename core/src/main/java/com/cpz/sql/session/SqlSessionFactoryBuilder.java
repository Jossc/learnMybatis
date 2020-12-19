package com.cpz.sql.session;

import com.cpz.config.XMLConfigFactory;
import com.cpz.config.XMLConfigFactoryBuilder;
import com.cpz.exception.ParseConfigException;
import com.cpz.model.Configuration;
import com.cpz.sql.DefaultSqlSessionFactory;
import com.cpz.sql.SqlSessionFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

/**
 * @author Hikari
 * @version 1.0.0
 * @ClassName SqlSessionFactoryBuilder.java
 * @createTime 2020年12月16日 22:45:00
 */
@Slf4j
public class SqlSessionFactoryBuilder {

    /***
     * 解析配置文件换砖 sqlSession
     * @param inputStream 配置文件信息
     * @return
     */
    public SqlSessionFactory buildSqlSessionFactory(InputStream inputStream) throws ParseConfigException {
        log.debug("buildSqlSessionFactory start ");
        XMLConfigFactory<Configuration> XMLConfigFactory = new XMLConfigFactoryBuilder();
        final Configuration configuration = XMLConfigFactory.parseConfig(inputStream);
        log.debug("parseConfig configuration {}", configuration);
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(configuration);
        return null;
    }
}
