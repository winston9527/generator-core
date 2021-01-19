package com.winston.code.generator.core.dao;

import com.winston.code.generator.core.entity.UserTabColumn;
import com.winston.code.generator.core.entity.UserTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseQueryMapper {

    String test();

    List<UserTabColumn> getTabCloumnsByTableName(@Param("tableName") String tableName);

    List<UserTable> queryUserTables (String searchKey);
}
