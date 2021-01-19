package com.winston.code.generator.core.service.impl;


import com.winston.code.generator.core.dao.mysql.MysqlQueryMapper;
import com.winston.code.generator.core.entity.UserTabColumn;
import com.winston.code.generator.core.entity.UserTable;
import com.winston.code.generator.core.service.QueryService;
import com.winston.code.generator.core.util.PropertiesHolder;
import com.winston.code.generator.core.util.SqlSessionFactoryUtil;
import com.winston.code.generator.core.util.UserTabColumnUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author Winston
 * @version 2017/3/21- 10:39
 */
public class MysqlQueryServiceImpl implements QueryService {


    public List<UserTabColumn> getTabCloumnsByTableName(String tableName){
        SqlSession session = SqlSessionFactoryUtil.getSqlSessionFactoryInstance().openSession();
        List<UserTabColumn> list;
        try {
        	MysqlQueryMapper mysqlQueryMapper =  session.getMapper(MysqlQueryMapper.class);
            list = mysqlQueryMapper.getTabCloumnsByTableName(PropertiesHolder.getDataBase(),tableName);
            UserTabColumnUtil.coverUserTabColumn(list);
        }finally {
            session.close();
        }

        return list;
    }

    public List<UserTable> queryUserTables(String serachkey){
        SqlSession session = SqlSessionFactoryUtil.getSqlSessionFactoryInstance().openSession();
        List<UserTable> list;
        try {
        	MysqlQueryMapper mysqlQueryMapper =  session.getMapper(MysqlQueryMapper.class);
            list = mysqlQueryMapper.queryUserTables(serachkey);
        }finally {
            session.close();
        }

        return list;
    }


    public void test() {
        SqlSession session = SqlSessionFactoryUtil.getSqlSessionFactoryInstance().openSession();
        try {
        	MysqlQueryMapper mysqlQueryMapper =  session.getMapper(MysqlQueryMapper.class);
        	mysqlQueryMapper.test();
        }finally {
            session.close();
		}

	}
}
