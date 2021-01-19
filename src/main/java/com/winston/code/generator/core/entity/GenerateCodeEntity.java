package com.winston.code.generator.core.entity;

import java.util.List;

/**
 * @author Winston
 * @version 2017/4/1- 17:04
 */
public class GenerateCodeEntity {

    private String tableName;
    private String beautifyTableName;
    private String tableComment;
    private String entityPath;
    private String sqlPath;
    private String daoPath;
    private String packagePath;
    private List<UserTabColumn> columnList;


    public String getEntityPath() {
        return entityPath;
    }

    public void setEntityPath(String entityPath) {
        this.entityPath = entityPath;
    }

    public String getSqlPath() {
        return sqlPath;
    }

    public void setSqlPath(String sqlPath) {
        this.sqlPath = sqlPath;
    }

    public String getDaoPath() {
        return daoPath;
    }

    public void setDaoPath(String daoPath) {
        this.daoPath = daoPath;
    }

    public List<UserTabColumn> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<UserTabColumn> columnList) {
        this.columnList = columnList;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getBeautifyTableName() {
        return beautifyTableName;
    }

    public void setBeautifyTableName(String beautifyTableName) {
        this.beautifyTableName = beautifyTableName;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }
}
