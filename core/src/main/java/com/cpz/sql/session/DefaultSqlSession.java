package com.cpz.sql.session;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author Hikari
 * @version 1.0.0
 * @ClassName DefaultSqlSession.java
 * @createTime 2020年12月17日 22:34:00
 */
@Slf4j
public class DefaultSqlSession<T> implements SqlSession {


    @Override
    public List<T> selectList(String statementId, Object... params) {
        log.debug("selectList statementId {},params {}", statementId, params);
        return null;
    }

    @Override
    public T selectOne(String statementId, Object... params) {
        log.debug("selectOne statementId {},params {}", statementId, params);
        final List<T> selectList = selectList(statementId, params);
        if (selectList.size() == 1) {
            return selectList.get(0);
        } else {
            throw new RuntimeException();
        }
    }
}
