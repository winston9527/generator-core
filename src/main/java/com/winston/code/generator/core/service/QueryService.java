package com.winston.code.generator.core.service;


import com.winston.code.generator.core.entity.UserTabColumn;
import com.winston.code.generator.core.entity.UserTable;

import java.util.List;

/**
 * @author Winston
 * @version 2017/3/21- 15:43
 */
public interface QueryService {

    List<UserTabColumn> getTabCloumnsByTableName (String tableName);

    List<UserTable> queryUserTables (String serachkey);

    void test ();
}
