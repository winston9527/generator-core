package com.winston.code.generator.core.dao.mysql.provider;


import com.winston.code.generator.core.util.PropertiesHolder;
import com.winston.code.generator.core.util.StringUtil;

public class MysqlDynaSqlProvider {

    public String  queryUserTables(String searchKey){
        StringBuffer stf = new StringBuffer();
        stf.append("select\n" +
                "    a.TABLE_NAME as tableName,\n" +
                "    a.TABLE_COMMENT as tableComments\n" +
                "from information_schema.TABLES a\n" +
                "where 1=1 "
                );
        if(!StringUtil.isEmpty(PropertiesHolder.getDataBase())) {
        	stf.append("and a.TABLE_SCHEMA = '"+PropertiesHolder.getDataBase()+"'");
        }
        if(!StringUtil.isEmpty(searchKey)){
            stf.append("and (upper(a.table_name) like '%'||#{searchKey}||'%' or UPPER(b.comments) like '%'||#{searchKey}||'%')");
        }
        stf.append("order by a.table_name");
        return stf.toString();
    }
}
