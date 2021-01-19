package com.winston.code.generator.core.util;


import com.winston.code.generator.core.common.enums.DBTypeEnum;
import com.winston.code.generator.core.dao.mysql.MysqlQueryMapper;
import com.winston.code.generator.core.dao.oracle.OracleQueryMapper;
import com.winston.code.generator.core.dao.sqlserver.SqlServerQueryMapper;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;

/**
 * @author Winston
 * @version 2017/3/21- 15:38
 */
public class SqlSessionFactoryUtil {

    private static SqlSessionFactory sqlSessionFactory;

    public static void createSqlSessionFactoryInstance(DBTypeEnum dBTypeEnum, String url, String userName, String password) {
        String driver = dBTypeEnum.getDriver();
        DataSource dataSource = new PooledDataSource(driver, url, userName, password);
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);

        configuration.addMapper(MysqlQueryMapper.class);
        configuration.addMapper(OracleQueryMapper.class);
        configuration.addMapper(SqlServerQueryMapper.class);
		
        //configuration.addMappers("com.winston.generator.dao");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    }

    public static SqlSessionFactory getSqlSessionFactoryInstance() {
        return sqlSessionFactory;
    }

    public static void destroySqlSessionFactory() {
        sqlSessionFactory = null;
    }

}
