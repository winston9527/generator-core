package com.winston.code.generator.core.util;


import com.winston.code.generator.core.entity.GenerateCodeEntity;
import com.winston.code.generator.core.entity.UserTabColumn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Winston
 * @version 2017/4/1- 17:01
 */
public class GenerateCodeUtil {


    /**
     * 生成
     * @param generateCodeEntity
     */
    public static void generateCode(GenerateCodeEntity generateCodeEntity){

        List<UserTabColumn> list = generateCodeEntity.getColumnList();
        String tableName = generateCodeEntity.getTableName();
        //1、创建数据模型
        Map<String,Object> root = new HashMap<String,Object>();
        //2、为数据模型添加值
        root.put("columns", list);
        //获取author
        root.put("author", SystemUtil.getHostName());
        //获取entity里面import的类型
        List<String> importList = new ArrayList<String>();
        for(UserTabColumn userTabColumn:list){
            String javaType = userTabColumn.getJavaType();
            if("String".equalsIgnoreCase(javaType)){
                continue;
            }else if ("Date".equals(javaType)){
                if(!importList.contains("java.util.Date")){
                    importList.add("java.util.Date");
                }
            }else if("BigDecimal".equals(javaType)){
                if(!importList.contains("java.math.BigDecimal")){
                    importList.add("java.math.BigDecimal");
                }
            }
        }
        root.put("importList",importList);
        root.put("tableComment",generateCodeEntity.getTableComment());
        String beautifyTableName = generateCodeEntity.getBeautifyTableName();
        String entityName = beautifyTableName+"Entity";
        root.put("entityName",entityName);
        String packagePath =generateCodeEntity.getPackagePath();
        if(StringUtil.isEmpty(packagePath)){
            packagePath = "com.winston.entity";
        }
        root.put("packagePath",packagePath);

        //生成Entity
        FreemarkerUtil.fprint("EntityTemplate.ftl",root,generateCodeEntity.getEntityPath(),entityName+".java");

        //生成Dao
        String daoName = beautifyTableName+"Dao";
        root.put("daoName",daoName);
        FreemarkerUtil.fprint("DaoTemplate.ftl",root,generateCodeEntity.getDaoPath(),daoName+".java");

        //生成SQL文件
        String sqlName = beautifyTableName+"Sql";
        root.put("sqlName",sqlName);
        root.put("tableName",tableName);
        FreemarkerUtil.fprint("SqlTemplate.ftl",root,generateCodeEntity.getSqlPath(),sqlName+".xml");

        //RefreshDictionary.refreshDictionary();
    }
}
