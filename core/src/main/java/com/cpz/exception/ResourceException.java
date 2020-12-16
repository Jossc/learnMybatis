package com.cpz.exception;

import lombok.Data;

/**
 * @author Hikari
 * @version 1.0.0
 * @ClassName ResourceException.java
 * @createTime 2020年12月15日 23:17:00
 */
@Data
public class ResourceException extends Exception {

    private static final long serialVersionUID = -1115724509783144547L;

    private String msg;

    private int errorCode;

    public ResourceException(String message, String msg, int errorCode) {
        super(message);
        this.msg = msg;
        this.errorCode = errorCode;
    }
}
