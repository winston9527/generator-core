package com.winston.code.generator.core.util;



import com.winston.code.generator.core.entity.UserTabColumn;
import com.winston.code.generator.core.entity.UserTable;

import java.util.List;

import javax.swing.table.DefaultTableModel;


public class TableUtil {

	/**
	 * 加载表格的数据
	 * @param tableModel 需要加载的表模型
	 * @param userTabColumnList 添加到表模型的数据集合
	 */
	public static void fillTableDataColumn(DefaultTableModel tableModel,List<UserTabColumn> userTabColumnList) {
		clearTableData(tableModel);
		for(UserTabColumn userTabColumn : userTabColumnList){
			Object[] rowData = new Object[]{
					userTabColumn.getColumnName(),
					userTabColumn.getComments(),
					userTabColumn.getBeautifyColumnName()
			};
			tableModel.insertRow(tableModel.getRowCount(), rowData);
		}

	}


	/**
	 * 加载表格的数据
	 * @param tableModel 需要加载的表模型
	 * @param tableList 添加到表模型的数据集合
	 */
	public static void fillTableData(DefaultTableModel tableModel,List<UserTable> tableList) {
		clearTableData(tableModel);
		for(UserTable userTable : tableList){
			Object[] rowData = new Object[]{
					userTable.getTableName(),
					userTable.getTableComments()
			};
			tableModel.insertRow(tableModel.getRowCount(), rowData);
		}

	}

	/**
	 * 根据传入的默认表模型，情况表模型里面的数据
	 * @param tableModel 需要被清空数据的表模型
	 */
	public static void clearTableData(DefaultTableModel tableModel){
		int count = tableModel.getRowCount();
		for(int row=0;row<count;row++){
			tableModel.removeRow(0);
		}
	}




}
