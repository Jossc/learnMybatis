package com.cpz.sql;

import com.cpz.model.Configuration;
import com.cpz.sql.session.DefaultSqlSession;
import com.cpz.sql.session.SqlSession;

/**
 * @author Hikari
 * @version 1.0.0
 * @ClassName DefaultSqlSessionFactory.java
 * @createTime 2020年12月17日 22:30:00
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
