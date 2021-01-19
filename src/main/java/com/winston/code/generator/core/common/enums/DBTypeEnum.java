package com.winston.code.generator.core.common.enums;

import lombok.Getter;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

@Getter
public enum DBTypeEnum {

    ORACLE("oracle", "oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@192.168.0.182:1521:orcl"),
    MYSQL("mysql", "com.mysql.jdbc.Driver", "jdbc:mysql://192.168.0.182:3306/bene_db2"),
    SQLSERVER("sqlserver", "com.microsoft.sqlserver.jdbc.SQLServerDriver",
      "jdbc:192.168.0.182:1433;DatabaseName=payment");

    private final String dbType;

    private final String driver;

    private final String defaultUrl;

    private static final Map<String, DBTypeEnum> map = new HashMap<>();

    static {
        for (DBTypeEnum dBTypeEnum : EnumSet.allOf(DBTypeEnum.class)) {
            map.put(dBTypeEnum.getDbType(), dBTypeEnum);
        }
    }

    DBTypeEnum(String dbType, String driver, String defaultUrl) {
        this.dbType = dbType;
        this.driver = driver;
        this.defaultUrl = defaultUrl;
    }

    public static DBTypeEnum find(String name) {
        return map.get(name);
    }


}
