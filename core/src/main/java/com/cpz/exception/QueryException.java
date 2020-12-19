package com.cpz.exception;

/**
 * @author Hikari
 * @version 1.0.0
 * @ClassName QueryException.java
 * @createTime 2020年12月19日 18:10:00
 */
public class QueryException extends RuntimeException {

    private static final long serialVersionUID = 2733745315196370475L;

    private String msg;

    public QueryException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
