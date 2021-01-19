package com.winston.code.generator.core.ui;

import com.winston.code.generator.core.entity.GenerateCodeEntity;
import com.winston.code.generator.core.entity.UserTabColumn;
import com.winston.code.generator.core.factory.QueryServiceFactory;
import com.winston.code.generator.core.service.QueryService;
import com.winston.code.generator.core.util.GenerateCodeUtil;
import com.winston.code.generator.core.util.PropertiesHolder;
import com.winston.code.generator.core.util.StringUtil;
import com.winston.code.generator.core.util.TableUtil;

import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * @Description:表列展示面板
 * @Author Winston
 * @Version 1.0 2018年8月29日 下午5:37:20
 */
public class TabColumnFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private final int WIDTH = 700, HEIGHT = 500;

    private JLabel lblObjectName;

    private JTextField txtObjectName;

    private JButton btnBack, btnFinish, btnCancel, btnEntityPath, btnSqlPath, btnDaoPath;

    private JPanel pnlContent;

    private Container container;

    private DefaultTableModel tableModel3;

    private ColumnJTable table3;

    private JScrollPane spnlTable3;

    QueryService queryService = QueryServiceFactory.getQueryServiceByDbType(PropertiesHolder.getDbTypeEnum());

    private JFileChooser entityFileChooser, sqlFileChooser, daoFileChooser;

    // 初始化表格数据
    java.util.List<UserTabColumn> tableColumnList;

    private JTextField txtEntityPath, txtSqlPath, txtDaoPath;

    private String tableName, tableComment, filePath, packagePath;

    public TabColumnFrame(String tableName, String tableComment, String filePath, String packagePath) {
        this.filePath = filePath;
        this.packagePath = packagePath;
        this.tableName = tableName;
        this.tableComment = tableComment;
        setSize(WIDTH, HEIGHT);
        setTitle("generator");
        Image icon =
          Toolkit.getDefaultToolkit().getImage("super.png"); // 通过getDefaultToolkit()获取一个Toolkit类型的工具，调用getImage()获取一个图片
        setIconImage(icon); // 设置图标
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        container = getContentPane();
        pnlContent = new JPanel();
        pnlContent.setLayout(null);
        lblObjectName = new JLabel("Object Name：");
        lblObjectName.setBounds(50, 320, 100, 25);
        txtObjectName = new JTextField("");
        txtObjectName.setBounds(140, 320, 300, 25);

        String beautifyTableName = StringUtil.capFirst(StringUtil.coverSeparatorToHump(tableName));
        // beautifyTableName Name
        txtObjectName.setText(beautifyTableName);
        txtObjectName.addKeyListener(new MyKeyListener() {

            public void keyReleased(KeyEvent e) {

            }
        });

        String[] columnNames = new String[] {"数据库列明", "数据库备注", "java 字段名"};
        tableModel3 = new DefaultTableModel(columnNames, 0);
        table3 = new ColumnJTable(tableModel3);
        table3.getTableHeader().setReorderingAllowed(false);// 设置不可移动列位置
        table3.getTableHeader().setResizingAllowed(false);// 设置不可调整列宽
        table3.setRowHeight(30);

        table3.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (table3.getSelectedRowCount() != 0) {
                    // 获取选中行
                    /*
                     * int row = table3.getSelectedRow();
                     * txtHId.setText(table3.getValueAt(row, 0).toString());
                     * txtHRId.setText(table3.getValueAt(row, 1).toString());
                     * jcbBuildId.setSelectedIndex(Integer.parseInt(table3.
                     * getValueAt(row, 3).toString())-1); String[] strUnit =
                     * table3.getValueAt(row, 4).toString().split("单");
                     * txtHUnit.setText(strUnit[0]); String strDoorNum =
                     * table3.getValueAt(row, 5).toString().replace("号", "");
                     * txtHDoorNum.setText(strDoorNum);
                     */
                }
            }

        });

        // 初始化表格数据
        tableColumnList = queryService.getTabCloumnsByTableName(tableName);

        TableUtil.fillTableDataColumn(tableModel3, tableColumnList);

        spnlTable3 = new JScrollPane(table3);
        spnlTable3.setBounds(40, 10, 500, 300);
        add(spnlTable3);

        btnBack = new JButton("<back");
        btnBack.setBounds(400, 440, 75, 25);
        btnBack.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
                new TableNameFrame(filePath, packagePath);
            }
        });

        btnFinish = new JButton("Finish");
        btnFinish.setBounds(480, 440, 75, 25);
        btnFinish.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if (StringUtil.isEmpty(txtObjectName.getText())) {
                    JOptionPane.showMessageDialog(null, "Please enter object name!");
                    return;
                }

                if (StringUtil.isEmpty(txtEntityPath.getText())) {
                    JOptionPane.showMessageDialog(null, "Please  select a entity path!");
                    return;
                }
                // java 字段名是否修改
                for (int i = 0; i < tableColumnList.size(); i++) {
                    UserTabColumn userTabColumn = tableColumnList.get(i);
                    String userBeautifyColumn = table3.getValueAt(i, 2).toString();
                    if (!userTabColumn.getBeautifyColumnName().equals(userBeautifyColumn)) {
                        userTabColumn.setBeautifyColumnName(userBeautifyColumn);
                    }
                }

                // 获取选中行
                GenerateCodeEntity generateCodeEntity = new GenerateCodeEntity();
                generateCodeEntity.setEntityPath(txtEntityPath.getText() + "/");
                generateCodeEntity.setSqlPath(txtSqlPath.getText() + "/");
                generateCodeEntity.setDaoPath(txtDaoPath.getText() + "/");
                generateCodeEntity.setColumnList(tableColumnList);
                generateCodeEntity.setTableName(tableName);
                generateCodeEntity.setBeautifyTableName(txtObjectName.getText());
                generateCodeEntity.setPackagePath(packagePath);
                generateCodeEntity.setTableComment(tableComment);
                // 调用生成代码工具
                GenerateCodeUtil.generateCode(generateCodeEntity);
                //生成后续 任务 如 刷新文件夹
                //EclipseUtis.refreshFloder();
                dispose();
            }
        });

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(580, 440, 75, 25);
        btnCancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        btnEntityPath = new JButton("Entity Path");
        btnEntityPath.setBounds(40, 350, 95, 25);
        btnEntityPath.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                entityFileChooser = new JFileChooser();
                entityFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                entityFileChooser.showDialog(new JLabel(), "choose Entity Path ");
                File file = entityFileChooser.getSelectedFile();
                if (file != null && file.isDirectory()) {
                    String path = file.getAbsolutePath();
                    txtEntityPath.setText(path);
                    if (StringUtil.isEmpty(txtSqlPath.getText())) {
                        txtSqlPath.setText(path);
                    }
                    if (StringUtil.isEmpty(txtDaoPath.getText())) {
                        txtDaoPath.setText(path);
                    }
                }

            }
        });
        txtEntityPath = new JTextField();
        txtEntityPath.setBounds(140, 350, 300, 25);
        txtEntityPath.setEnabled(false);

        btnSqlPath = new JButton("Sql Path");
        btnSqlPath.setBounds(40, 380, 95, 25);
        btnSqlPath.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                sqlFileChooser = new JFileChooser();
                sqlFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                sqlFileChooser.showDialog(new JLabel(), "choose Sql Path ");
                File file = sqlFileChooser.getSelectedFile();
                if (file != null && file.isDirectory()) {
                    txtSqlPath.setText(file.getAbsolutePath());
                }

            }
        });

        txtSqlPath = new JTextField();
        txtSqlPath.setBounds(140, 380, 300, 25);
        txtSqlPath.setEnabled(false);

        btnDaoPath = new JButton("Dao Path");
        btnDaoPath.setBounds(40, 410, 95, 25);
        btnDaoPath.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                daoFileChooser = new JFileChooser();
                daoFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                daoFileChooser.showDialog(new JLabel(), "choose Dao Path ");
                File file = daoFileChooser.getSelectedFile();
                if (file != null && file.isDirectory()) {
                    txtDaoPath.setText(file.getAbsolutePath());
                }

            }
        });

        txtDaoPath = new JTextField();
        txtDaoPath.setBounds(140, 410, 300, 25);
        txtDaoPath.setEnabled(false);

        if (!StringUtil.isEmpty(filePath)) {
            txtEntityPath.setText(filePath);
            txtDaoPath.setText(filePath);
            txtSqlPath.setText(filePath);
        }

        pnlContent.add(lblObjectName);
        pnlContent.add(txtObjectName);
        pnlContent.add(btnBack);
        pnlContent.add(btnFinish);
        pnlContent.add(btnCancel);
        pnlContent.add(btnEntityPath);
        pnlContent.add(txtEntityPath);
        pnlContent.add(btnSqlPath);
        pnlContent.add(txtSqlPath);
        pnlContent.add(btnDaoPath);
        pnlContent.add(txtDaoPath);
        container.add(pnlContent);
    }

}
