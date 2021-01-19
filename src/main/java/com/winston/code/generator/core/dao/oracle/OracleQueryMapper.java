package com.winston.code.generator.core.dao.oracle;


import com.winston.code.generator.core.dao.BaseQueryMapper;
import com.winston.code.generator.core.dao.oracle.provider.OralceDynaSqlProvider;
import com.winston.code.generator.core.entity.UserTabColumn;
import com.winston.code.generator.core.entity.UserTable;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @Description: 数据库连接测试
 * @Author Winston
 * @Version 1.0 2018年8月29日 下午5:53:34
 */
public interface OracleQueryMapper extends BaseQueryMapper {

    @Select("Select 1 from dual")
    String test();

    @Select("select\n" +
      "      a.table_name as tableName,\n" +
      "      a.column_name as columnName,\n" +
      "      a.DATA_TYPE AS dataType,\n" +
      "      b.comments as comments,\n" +
      "      (SELECT C.CONSTRAINT_NAME\n" +
      "          FROM USER_CONS_COLUMNS C, USER_CONSTRAINTS D\n" +
      "         WHERE C.CONSTRAINT_NAME = d.CONSTRAINT_NAME\n" +
      "           AND C.COLUMN_NAME = A.COLUMN_NAME\n" +
      "           AND C.TABLE_NAME = A.TABLE_NAME AND d.constraint_type ='P') AS pkName\n" +
      "from User_Tab_Columns a,user_col_comments b\n" +
      "WHERE a.COLUMN_NAME = b.column_name\n" +
      "      AND a.TABLE_NAME =b.table_name\n" +
      "      AND a.table_name = #{tableName}\n" +
      "ORDER BY a.column_id asc")
    List<UserTabColumn> getTabCloumnsByTableName(@Param("tableName") String tableName);

    @SelectProvider(type = OralceDynaSqlProvider.class, method = "queryUserTables")
    List<UserTable> queryUserTables(String searchKey);

}
