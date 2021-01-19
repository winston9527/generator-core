package com.winston.code.generator.core.factory;


import com.winston.code.generator.core.common.enums.DBTypeEnum;
import com.winston.code.generator.core.service.QueryService;
import com.winston.code.generator.core.service.impl.MysqlQueryServiceImpl;
import com.winston.code.generator.core.service.impl.OracleQueryServiceImpl;
import com.winston.code.generator.core.service.impl.SqlServerQueryServiceImpl;

import java.util.EnumMap;
import java.util.Map;

/**
 * 查询服务工厂类
 *
 * @Author Winston
 * @Version 1.0 2019年6月13日 下午3:16:11
 */
public class QueryServiceFactory {


    private static final Map<DBTypeEnum, QueryService> map;

    static {
        map = new EnumMap<>(DBTypeEnum.class);
        map.put(DBTypeEnum.MYSQL, new MysqlQueryServiceImpl());
        map.put(DBTypeEnum.ORACLE, new OracleQueryServiceImpl());
        map.put(DBTypeEnum.SQLSERVER, new SqlServerQueryServiceImpl());
    }

    public static QueryService getQueryServiceByDbType(DBTypeEnum dBTypeEnum) {
        return map.get(dBTypeEnum);
    }

    private QueryServiceFactory() {

    }
}
