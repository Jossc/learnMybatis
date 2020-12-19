package com.cpz.executor.entity;

import com.cpz.utils.ParameterMapping;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hikari
 * @version 1.0.0
 * @ClassName BoundSql.java
 * @createTime 2020年12月19日 18:25:00
 */
@Data
public class BoundSql {

    /**
     * 解析出来的sql
     */
    private String sqlText;

    /**
     * 参数集合
     */
    private List<ParameterMapping> mappingList = new ArrayList<>();

    public BoundSql(String sqlText, List<ParameterMapping> mappingList) {
        this.sqlText = sqlText;
        this.mappingList = mappingList;
    }
}
