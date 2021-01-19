package com.winston.code.generator.core.dao.oracle.provider;

import com.winston.code.generator.core.util.StringUtil;

public class OralceDynaSqlProvider {

    public String queryUserTables(String searchKey) {
        StringBuffer stf = new StringBuffer();
        stf.append("select\n" +
          "    a.table_name as tableName,\n" +
          "    b.comments as tableComments\n" +
          "from User_Tables a,user_tab_comments b\n" +
          "where a.table_name = b.table_name\n"
        );
        if (!StringUtil.isEmpty(searchKey)) {
            stf.append("and (upper(a.table_name) like '%'||#{searchKey}||'%' or UPPER(b.comments) like '%'||#{searchKey}||'%')");
        }
        stf.append("order by a.table_name");
        return stf.toString();
    }
}
