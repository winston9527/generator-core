package com.winston.code.generator.core.entity;

/**
 * @author Winston
 * @version 2017/3/21- 10:44
 */
public class UserTable {

	private String tableName;

	private String tableComments;

	public String getTableName () {
		return tableName;
	}

	public void setTableName (String tableName) {
		this.tableName = tableName;
	}

	public String getTableComments () {
		return tableComments;
	}

	public void setTableComments (String tableComments) {
		this.tableComments = tableComments;
	}

	@Override
	public String toString () {
		return tableName + "||" + tableComments;
	}
}
