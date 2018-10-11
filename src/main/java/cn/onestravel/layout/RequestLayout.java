package cn.onestravel.layout;

import com.intellij.uiDesigner.core.GridConstraints;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.jar.JarEntry;

public class RequestLayout extends JPanel {
    private JPanel root;
    private JList listHistory;
    private JButton btnAddPanel;
    private JPanel tabRoot;

    public RequestLayout() {
        LayoutManager manager = tabRoot.getLayout();
        btnAddPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel1 = new TabButton();
                System.out.println(tabRoot.getComponentCount()-2);
                tabRoot.add(panel1,new com.intellij.uiDesigner.core.GridConstraints(0, tabRoot.getComponentCount()-2, 1, 1, GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code
        // here
    }
}
