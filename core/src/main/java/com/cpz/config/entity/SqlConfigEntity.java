package com.cpz.config.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author Hikari
 * @version 1.0.0
 * @ClassName SqlConfigEntity.java
 * @createTime 2020年12月16日 23:24:00
 */
@Data
@Builder
public class SqlConfigEntity {

    private String driverClass;

    private String jdbcUrl;

    private String userName;

    private String passWord;
}
