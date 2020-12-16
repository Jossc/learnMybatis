package com.cpz.io;

import com.cpz.exception.ResourceException;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

/**
 * 加载配置资源
 *
 * @author Hikari
 * @version 1.0.0
 * @ClassName LoadDataConfigResource.java
 * @createTime 2020年12月15日 23:06:00
 */
@Slf4j
public class LoadDataConfigResource implements Resources {
    @Override
    public InputStream getResourceInputStream(String path) throws ResourceException {
        log.debug("getResourceInputStream path {}", path);
        return Resources.class.getClassLoader().getResourceAsStream(path);
    }
}
