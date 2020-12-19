package com.cpz.sql;

import com.cpz.sql.session.SqlSession;

/**
 * @author Hikari
 * @version 1.0.0
 * @ClassName SqlSessionFactorySession.java
 * @createTime 2020年12月16日 22:44:00
 */
public interface SqlSessionFactory {

    /**
     * 创建sqlSssion
     *
     * @return
     */
    public SqlSession openSession();
}
