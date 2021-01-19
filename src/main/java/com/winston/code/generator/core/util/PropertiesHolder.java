package com.winston.code.generator.core.util;


import com.winston.code.generator.core.common.enums.DBTypeEnum;

/**
 * 属性持有工具类
 * @Author Winston
 * @Version 1.0 2019年6月13日 下午3:36:24
 */
public class PropertiesHolder {
	
	private static DBTypeEnum dbTypeEnum;
	
	private static String dataBase;
	
	public static DBTypeEnum getDbTypeEnum () {
		return dbTypeEnum;
	}
	
	public static void setDbTypeEnum (DBTypeEnum daDbTypeEnum) {
		PropertiesHolder.dbTypeEnum = daDbTypeEnum;
	}

	
	public static String getDataBase () {
		return dataBase;
	}

	
	public static void setDataBase (String dataBase) {
		PropertiesHolder.dataBase = dataBase;
	}
	
	
	
}
