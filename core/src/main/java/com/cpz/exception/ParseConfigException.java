package com.cpz.exception;

import lombok.Data;

/**
 * @author Hikari
 * @version 1.0.0
 * @ClassName ParseConfigException.java
 * @createTime 2020年12月16日 23:07:00
 */
@Data
public class ParseConfigException extends Exception {

    private static final long serialVersionUID = -8294759425840793728L;

    private String msg;

    public ParseConfigException(String message) {
        super(message);
        this.msg = msg;
    }
}
