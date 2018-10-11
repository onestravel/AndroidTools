package cn.onestravel.layout;

import cn.onestravel.utils.FormatUtil;
import cn.onestravel.view.JTextAreaMenu;
import cn.onestravel.view.JTextPaneMenu;
import cn.onestravel.view.JTreeMenu;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class FormatLayout extends JPanel implements DocumentListener {
    private JPanel rootPane;
    private JTextAreaMenu tAJson;
    private JSplitPane splitPane;
    private JTreeMenu treeJson;
    private JTextAreaMenu tAFormat;

    public FormatLayout() {
        initData();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    private void initData() {
        tAJson.getDocument().addDocumentListener(this);
    }


    @Override
    public void insertUpdate(DocumentEvent e) {
        formatData(tAJson.getText().trim());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        formatData(tAJson.getText().trim());
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        formatData(tAJson.getText().trim());
    }

    private void formatData(String trim) {
        try {
            tAFormat.setText(FormatUtil.formatJson(trim));
            treeJson.setData(trim);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
