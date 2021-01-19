package com.winston.code.generator.core.dao.sqlserver.provider;

import com.winston.code.generator.core.util.StringUtil;

public class SqlserverDynaSqlProvider {

    public String queryUserTables(String searchKey) {
        StringBuffer stf = new StringBuffer();
        stf.append("select\n" +
          "    a.name as tableName,\n" +
          "    '' as tableComments\n" +
          "from sysobjects a \n" +
          "where xtype ='U'\n"
        );
        if (!StringUtil.isEmpty(searchKey)) {
            stf.append("and upper(a.name) like '%'+#{searchKey}+'%'");
        }
        stf.append("order by a.name");
        return stf.toString();
    }
}