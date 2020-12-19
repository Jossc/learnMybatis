package com.cpz.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author Hikari
 * @version 1.0.0
 * @ClassName MappedStatement.java
 * @createTime 2020年12月16日 22:31:00
 */
@Data
@Builder
public class MappedStatement {

    private String id;

    private String resultType;

    private String parameterType;

    private String sql;

}
