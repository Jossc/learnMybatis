package com.cpz.config;

import com.cpz.exception.ParseConfigException;

import java.io.InputStream;

/**
 * @author Hikari
 * @version 1.0.0
 * @ClassName XMConfigFactory.java
 * @createTime 2020年12月16日 22:47:00
 */
public interface XMLConfigFactory<T> {

    /**
     * 解析输入流
     *
     * @param inputStream
     * @return
     */
    public T parseConfig(InputStream inputStream) throws ParseConfigException;
}
