package cn.onestravel.layout;

import cn.onestravel.utils.*;
import cn.onestravel.view.JTableMenu;
import cn.onestravel.view.JTextAreaMenu;
import cn.onestravel.view.JTextFieldMenu;
import com.sun.xml.internal.ws.util.StreamUtils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public class RequestDataLayoutItem extends JPanel {
    private JPanel root;
    private JComboBox cbRequestType;
    private JButton btnAddParam;
    private JButton btnDelete;
    private JButton btnRawAdd;
    private JButton btnCopyParam;
    private JButton btnSendRequest;
    private JCheckBox cbUrlEncode;
    private JTextFieldMenu tfCopiedParams;
    private JScrollPane spParams;
    private JTableMenu tableParams;
    private JLabel lbResponseBody;
    private JScrollPane spBody;
    private JTextAreaMenu taBody;
    private JTextFieldMenu tfUrl;
    private DefaultTableModel tableModel;
    private Map<String, Object> map;

    private ArrayList<String> urlHintList;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public RequestDataLayoutItem() {
        initViewData();

    }

    //初始化控件数据
    private void initViewData() {
        urlHintList = new ArrayList<>();
        ArrayList<String> list = FileUtils.getList((String) PropertiesUtils.getValue(PropertiesKeyUtils.PROPERTIES_NAME_BASE_CONFIG,PropertiesKeyUtils.HISTORY_URL));
        if (list != null) {
            urlHintList.addAll(list);
        }
        setAutoCompleteData(urlHintList);
        String[] columnNames = {"参数名称", "参数值"}; // 列名
        String[][] tableVales = {{"", ""}}; // 数据
        tableModel = new DefaultTableModel(tableVales, columnNames);
        tableParams.setModel(tableModel);
        sendRequest();
        addParam();
        deleteParam();
        rawAddParams();
        copyParams();
    }

    private void setAutoCompleteData(ArrayList<String> urlHintList) {
//        final DefaultComboBoxModel model = new DefaultComboBoxModel();
//        tfUrl.setModel(model);
////        tfUrl = new JComboBox(model) {
////            public Dimension getPreferredSize() {
////                return new Dimension(super.getPreferredSize().width, 0);
////            }
////        };
////        setAdjusting(cbInputHint, false);
//        for (String item : urlHintList) {
//            model.addElement(item);
//        }
//        tfUrl.setSelectedItem(null);
//        tfUrl.getEditor().getEditorComponent().addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//                updateList();
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//
//            }
//            private void updateList() {
//                try {
//                    setAdjusting(tfUrl, true);
//                    model.removeAllElements();
//                    String input = tfUrl.getEditor().getItem().toString();
//                    if (!input.isEmpty()) {
//                        for (String item : urlHintList) {
//                            if (item.toLowerCase().contains(input.toLowerCase())) {
//                                model.addElement(item);
//                            }
//                        }
//                    }
//                    if (model.getSize() > 0) {
//                        tfUrl.setPopupVisible(true);
//                    } else {
//                        tfUrl.setPopupVisible(false);
//                    }
//                    setAdjusting(tfUrl, false);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
    }
    private static void setAdjusting(JComboBox cbInput, boolean adjusting) {
        cbInput.putClientProperty("is_adjusting", adjusting);
    }


    /**
     * 添加参数
     */
    private void addParam() {
        btnAddParam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] rowValues = {"", ""};
                Object oa = tableModel.getValueAt(tableParams.getRowCount() - 1, 0);
                String key = oa + "";
                if (key != null && !"".equals(key)) {
                    tableModel.addRow(rowValues); // 添加一行
//					int rowCount = table.getRowCount() + 1; // 行数加上1
                } else {
                    DialogUtils.showHintDialog(root, "请填入参数后在添加");
                }
            }
        });

    }

    /**
     * 删除参数
     */
    private void deleteParam() {
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableParams.getSelectedRow();// 获得选中行的索引
                if (selectedRow != -1 && tableParams.getRowCount() > 1) // 存在选中行
                {
                    tableModel.removeRow(selectedRow); // 删除行
                } else {
                    tableModel.removeRow(selectedRow); // 删除行
                    String[] rowValues = {"", ""};
                    tableModel.addRow(rowValues); // 添加一行
                }
            }
        });
    }

    /**
     * 批量添加参数
     */
    private void rawAddParams() {
        btnRawAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableParams.clearSelection();
                if (tableParams.isEditing())
                    tableParams.getCellEditor().stopCellEditing();
                tfUrl.requestFocus();
                String input = JOptionPane.showInputDialog("输入您要批量添加的参数(&隔开)");
                if (StringUtils.isEmpty(input)) {
                    return;
                }
                String raw = input.trim();
                if (raw != null && !"".equals(raw) && !"null".equals(raw)) {
                    while (tableModel.getRowCount() > 0) {
                        tableModel.removeRow(tableModel.getRowCount() - 1);
                    }
                    String[] raws = raw.split("&");
                    for (int i = 0; i < raws.length; i++) {
                        String[] str = raws[i].split("=");
                        // if(str.length>=2){
                        tableModel.addRow(str);
                        // }
                    }
                }
            }
        });
    }

    /**
     * 复制参数
     */
    private void copyParams() {
        btnCopyParam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Toolkit toolkit = Toolkit.getDefaultToolkit();

                // Get the clipboard
                java.awt.datatransfer.Clipboard clipboard = toolkit.getSystemClipboard();
                String params = getParams();
                Transferable tText = new StringSelection(params);
                clipboard.setContents(tText, null);
                tfCopiedParams.setText(params);
                DialogUtils.showHintDialog(root, "参数列表复制成功！");
            }
        });
    }

    /**
     * 发送请求
     */
    private void sendRequest() {
        btnSendRequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String params = getParams();
                String path = tfUrl.getText().toString().trim();
                boolean isEncode = cbUrlEncode.isSelected();
                if (isEncode) {
                    try {
                        params = URLEncoder.encode(params, "utf-8");
                    } catch (UnsupportedEncodingException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
                System.out.println(params);
                if (StringUtils.isEmpty(path)) {
                    DialogUtils.showHintDialog(root, "sorry,请求URL不能为空！");
                } else if (!path.startsWith("http://") && !path.startsWith("https://")) {
                    DialogUtils.showHintDialog(root, "sorry,请输入正确的URL !");
                } else {

//                    taHeader.setText("执行中。。。");
                    taBody.setText("");
                    final String type = cbRequestType.getSelectedItem().toString();
                    new RequestThread(taBody, path, params, type).start();

                    if (!urlHintList.contains(path)) {
                        urlHintList.add(0,path);
                        setAutoCompleteData(urlHintList);
                        FileUtils.saveList(urlHintList, (String) PropertiesUtils.getValue(PropertiesKeyUtils.PROPERTIES_NAME_BASE_CONFIG,PropertiesKeyUtils.HISTORY_URL));
                    }
                }
//                btnSendRequest.setText("发送请求");
            }
        });
    }

    /**
     * 获取参数列表中的参数
     */
    public String getParams() {
        tableParams.setFocusable(false);
        map = new HashMap<>();
        int count = tableParams.getRowCount();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            Object oa = tableModel.getValueAt(i, 0);
            Object ob = tableModel.getValueAt(i, 1);
            map.put(oa.toString(), ob);
        }
        Set<Map.Entry<String, Object>> set = map.entrySet();
        for (Map.Entry<String, Object> entry : set) {
            if (entry.getKey() != null && !"".equals(entry.getKey())) {
                if (sb.length() > 0) {
                    sb.append("&" + entry.getKey() + "=" + entry.getValue());
                } else {
                    sb.append(entry.getKey() + "=" + entry.getValue());
                }
            }
        }
        System.out.println(sb.toString());
        return sb.toString();
    }


    /**
     * 请求子线程
     */
    class RequestThread extends Thread {
        private JTextAreaMenu response_body;
        private String path, params, type;

        public RequestThread(JTextAreaMenu response_body, String path, String params, String type) {
            this.params = params;
            this.path = path;
            this.type = type;
            this.response_body = response_body;

        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            if (params == null || "".equals(params))
                type = "GET";
            String head_str = HttpUtils.getResponseHeader(path, params, type);
            String body_str = HttpUtils.getResponseBody();
            if (body_str.startsWith("java.net.UnknownHostException")) {
//                response_header.setText("网络请求失败，请检查网络后重试！！！");
            } else if (HttpUtils.getStatus() == 200) {
//                response_header.setText(head_str);
                response_body.setText(FormatUtil.formatJson(Convert.ascii2native(body_str)));
            } else {
//                response_header.setText(head_str);
                response_body.setText(body_str);
            }
        }
    }
}
