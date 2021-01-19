package com.winston.code.generator.core.util;


import com.winston.code.generator.core.entity.UserTabColumn;

import java.util.List;

/**
 * @author Winston
 * @version 2017/3/22- 11:06
 */
public class UserTabColumnUtil {

    public static void/*List<UserTabColumn>*/ coverUserTabColumn(List<UserTabColumn> userTabColumns){
        String columnName,dataType,javaType;
        for(UserTabColumn userTabColumn:userTabColumns){
            columnName = userTabColumn.getColumnName();
            userTabColumn.setBeautifyColumnName(StringUtil.coverSeparatorToHump(columnName));

            dataType = userTabColumn.getDataType();
            if("VARCHAR2".equals(dataType)){
                javaType = "String";
            }else if("DATE".equalsIgnoreCase(dataType)){
                javaType = "Date";
            }else if("NUMBER".equalsIgnoreCase(dataType)){
                javaType = "BigDecimal";
            }else{
                javaType = "String";
            }

            userTabColumn.setJavaType(javaType);
        }
    }
}
