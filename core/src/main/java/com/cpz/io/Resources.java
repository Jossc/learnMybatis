package com.cpz.io;

import com.cpz.exception.ResourceException;

import java.io.InputStream;

/**
 * 加载资源接口
 *
 * @author Hikari
 * @version 1.0.0
 * @ClassName Resources.java
 * @createTime 2020年12月15日 23:05:00
 */
public interface Resources {

    /**
     * 加载指定路径资源
     *
     * @param path 指定路径
     * @return
     */
    public InputStream getResourceInputStream(String path) throws ResourceException;
}
