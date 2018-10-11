package cn.onestravel.layout;

import javax.swing.*;

public class TabButton extends JPanel {
    private JButton btnTab;
    private JPanel root;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public TabButton setTabTitle(String title) {
        btnTab.setText(title);
        return this;
    }

}
