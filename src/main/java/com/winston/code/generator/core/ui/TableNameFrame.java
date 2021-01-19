package com.winston.code.generator.core.ui;


import com.winston.code.generator.core.entity.UserTable;
import com.winston.code.generator.core.factory.QueryServiceFactory;
import com.winston.code.generator.core.service.QueryService;
import com.winston.code.generator.core.util.PropertiesHolder;
import com.winston.code.generator.core.util.StringUtil;
import com.winston.code.generator.core.util.TableUtil;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * @Description: 表名面板
 * @Author Winston
 * @Version 1.0 2018年8月29日 下午5:36:25
 */
public class TableNameFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private final int WIDTH = 700, HEIGHT = 500;
    private JTextField txtSearchTable;
    private JButton btnNext, btnCancel;
    private JPanel pnlContent;
    private Container container;


    private DefaultTableModel tableModel3;
    private MyJTable table3;
    private JScrollPane spnlTable3;
    private String packagePath, filePath;
    QueryService queryService = QueryServiceFactory.getQueryServiceByDbType(PropertiesHolder.getDbTypeEnum());

    //初始化表格数据
    java.util.List<UserTable> tableList;

    public TableNameFrame(String filePath, String packagePath) {
        setSize(WIDTH, HEIGHT);
        setTitle("generator");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        this.filePath = filePath;
        this.packagePath = packagePath;
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        container = getContentPane();
        pnlContent = new JPanel();
        pnlContent.setLayout(null);

        String[] columnNames = new String[] {"TableName", "TableComment"};
        tableModel3 = new DefaultTableModel(columnNames, 0);
        table3 = new MyJTable(tableModel3);
        table3.getTableHeader().setReorderingAllowed(false);//设置不可移动列位置
        table3.getTableHeader().setResizingAllowed(false);//设置不可调整列宽
        table3.setRowHeight(30);


        table3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table3.getSelectedRowCount() != 0) {
                    //获取选中行
					/*int row = table3.getSelectedRow();
					txtHId.setText(table3.getValueAt(row, 0).toString());
					txtHRId.setText(table3.getValueAt(row, 1).toString());
					jcbBuildId.setSelectedIndex(Integer.parseInt(table3.getValueAt(row, 3).toString())-1);
					String[] strUnit = table3.getValueAt(row, 4).toString().split("单");
					txtHUnit.setText(strUnit[0]);
					String strDoorNum = table3.getValueAt(row, 5).toString().replace("号", "");
					txtHDoorNum.setText(strDoorNum);*/
                }
            }
        });

        //初始化表格数据
        tableList = queryService.queryUserTables(null);

        TableUtil.fillTableData(tableModel3, tableList);

        txtSearchTable = new JTextField("");
        txtSearchTable.setBounds(40, 10, 180, 25);
        txtSearchTable.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {

            }

            public void keyReleased(KeyEvent e) {
                String searchKey = txtSearchTable.getText();
                if (!StringUtil.isEmpty(searchKey)) {
                    searchKey = searchKey.trim().toUpperCase();
                }
                tableList = queryService.queryUserTables(searchKey);
                /*System.out.println(tableList);*/
                TableUtil.fillTableData(tableModel3, tableList);
            }
        });
        spnlTable3 = new JScrollPane(table3);
        spnlTable3.setBounds(40, 50, 500, 300);
        add(spnlTable3);

        JLabel jLabel = new JLabel(
          "________________________________________________________________________________________________________________________________________________");
        jLabel.setBounds(0, 390, 1000, 30);

        btnNext = new JButton("next>");
        btnNext.setBounds(400, 420, 75, 25);
        btnNext.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if (table3.getSelectedRowCount() == 1) {
                    //获取选中行
                    int row = table3.getSelectedRow();
                    String tableName = table3.getValueAt(row, 0).toString();
                    Object tableCommentO = table3.getValueAt(row, 1);
                    String tableComment = "";
                    if (tableCommentO != null) {
                        tableComment = tableCommentO.toString();
                    }
                    new TabColumnFrame(tableName, tableComment, filePath, packagePath);
                    dispose();

                } else {
                    JOptionPane.showMessageDialog(null, "Please choose a tableName!");
                    return;
                }

            }
        });
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(590, 420, 75, 25);
        btnCancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        pnlContent.add(txtSearchTable);
        pnlContent.add(jLabel);
        pnlContent.add(btnNext);
        pnlContent.add(btnCancel);
        container.add(pnlContent);
    }
}
