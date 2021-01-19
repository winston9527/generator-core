package com.winston.code.generator.core.ui;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ColumnJTable extends JTable{
	private DefaultTableCellRenderer render
			= new DefaultTableCellRenderer();

	public ColumnJTable(DefaultTableModel tableModel){
		super(tableModel);
		//setDefaultRenderer(null,render);
		isCellEditable(0,0);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		render.setHorizontalAlignment(SwingConstants.CENTER);
		setRowHeight(20);
		for(int i=0;i<getColumnCount();i++){								//设置表格内容居中对齐
			getColumn(getColumnName(i)).setCellRenderer(render);
		}
	}
	public boolean isCellEditable(int row, int column){						//设置表格不可编辑
		if(column==2){
			return true;
		}
		return false;
	}

}
