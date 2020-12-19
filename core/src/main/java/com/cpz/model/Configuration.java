package com.cpz.model;

import lombok.Builder;
import lombok.Data;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Hikari
 * @version 1.0.0
 * @ClassName Configuration.java
 * @createTime 2020年12月16日 22:38:00
 */
@Data
public class Configuration {

    private DataSource dataSource;

    /**
     * String nameSpace.id value就是mapped对象
     */
    private Map<String, MappedStatement> mappedStatementMap = new ConcurrentHashMap<>();


    public Configuration() {
    }
}
