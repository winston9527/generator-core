package com.winston.code.generator.core.entity;


import lombok.Data;

/**
 * @author Winston
 * @version 2017/3/21- 10:44
 */
@Data
public class UserTabColumn {

    private String tableName;

    private String columnName;

    private String beautifyColumnName;

    private String dataType;

    private String javaType;

    private String comments;

    private String pkName;

    @Override
    public String toString() {
        return getColumnName() + "||" + comments + "||" + dataType;
    }
}
