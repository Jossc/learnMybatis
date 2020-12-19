package com.cpz.sql.session;

import java.util.List;

/**
 * @author Hikari
 * @version 1.0.0
 * @ClassName SqlSession.java
 * @createTime 2020年12月17日 22:34:00
 */
public interface SqlSession<T> {

    /**
     * 查询所有
     *
     * @param statementId sqlId
     * @param params      可变参数
     * @return
     */
    public <T> List<T> selectList(String statementId, Object... params);


    /**
     * 查询单个
     *
     * @param statementId sqlId
     * @param params      可变参数
     * @param <T>
     * @return
     */
    public <T> T selectOne(String statementId, Object... params);
}
