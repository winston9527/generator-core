package com.winston.code.generator.core.dao.mysql;


import com.winston.code.generator.core.dao.BaseQueryMapper;
import com.winston.code.generator.core.dao.mysql.provider.MysqlDynaSqlProvider;
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
public interface MysqlQueryMapper extends BaseQueryMapper {

    @Select("SELECT 1")
    String test();

    @Select("select\n" +
      "      a.TABLE_NAME as tableName,\n" +
      "      a.COLUMN_NAME as columnName,\n" +
      "      a.COLUMN_TYPE AS dataType,\n" +
      "      a.COLUMN_COMMENT as comments,\n" +
      "    (SELECT \n" +
      "      b.COLUMN_NAME \n" +
      "    FROM \n" +
      "      INFORMATION_SCHEMA.KEY_COLUMN_USAGE b \n" +
      "    WHERE a.table_schema = b.table_schema \n" +
      "      AND a.TABLE_NAME = b.TABLE_NAME \n" +
      "      AND a.COLUMN_NAME = b.COLUMN_NAME \n" +
      "      AND b.CONSTRAINT_NAME = 'PRIMARY') AS pkName \n" +
      "from information_schema.COLUMNS a \n" +
      "WHERE   a.table_name = #{tableName} and  a.TABLE_SCHEMA = #{dataBase}")
    List<UserTabColumn> getTabCloumnsByTableName(@Param("dataBase") String dataBase, @Param("tableName") String tableName);

    @SelectProvider(type = MysqlDynaSqlProvider.class, method = "queryUserTables")
    List<UserTable> queryUserTables(String searchKey);

}
