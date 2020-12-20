package com.cpz.executor;

import com.cpz.model.Configuration;
import com.cpz.model.MappedStatement;

import java.util.List;

/**
 * @author Hikari
 * @version 1.0.0
 * @ClassName Exector.java
 * @createTime 2020年12月19日 18:03:00
 */
public interface Executor {


    /**
     * 执行查询
     *
     * @param configuration   数据库配置想信息
     * @param mappedStatement mapper id
     * @param params          可变参数
     * @param <T>
     * @return
     */
    public <T> List<T> query(Configuration configuration,
                             MappedStatement mappedStatement,
                             Object... params) throws Exception;
}
