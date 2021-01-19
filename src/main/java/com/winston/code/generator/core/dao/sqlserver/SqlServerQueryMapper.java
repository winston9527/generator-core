package com.winston.code.generator.core.dao.sqlserver;

import com.winston.code.generator.core.dao.BaseQueryMapper;
import com.winston.code.generator.core.dao.sqlserver.provider.SqlserverDynaSqlProvider;
import com.winston.code.generator.core.entity.UserTabColumn;
import com.winston.code.generator.core.entity.UserTable;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface SqlServerQueryMapper extends BaseQueryMapper {

    @Select("Select 1")
    String test();

    @Select("SELECT\n" +
      "\td.name as tableName,\n" +
      "\ta.name as columnName,\n" +
      "\tb.name as dataType,\n" +
      "\tisnull(g.[value],'') AS comments,\n" +
      "\t'' as pkName\n" +
      "FROM\n" +
      "\tsyscolumns a\n" +
      "left join systypes b on\n" +
      "\ta.xtype = b.xusertype\n" +
      "inner join sysobjects d on\n" +
      "\ta.id = d.id\n" +
      "\tand d.xtype = 'U'\n" +
      "left join syscomments e on\n" +
      "\ta.cdefault = e.id\n" +
      "left join sys.extended_properties g on\n" +
      "\ta.id = g.major_id\n" +
      "\tAND a.colid = g.minor_id\n" +
      "where\n" +
      "\td.name = #{tableName}\n" +
      "\torder by a.colorder asc")
    List<UserTabColumn> getTabCloumnsByTableName(@Param("tableName") String tableName);

    @SelectProvider(type = SqlserverDynaSqlProvider.class, method = "queryUserTables")
    List<UserTable> queryUserTables(String searchKey);
}
