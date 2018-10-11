package cn.onestravel.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;

public class CellRender extends JTextFieldMenu
        implements TreeCellRenderer {
    protected Color selectedBackground;
    protected Color selectedForeground;
    protected Color noselectedBackground;
    protected Color noselectedForeground;

    public CellRender() {
        super();
        this.selectedBackground = Color.white;
        this.selectedForeground = Color.red;
        this.noselectedBackground = Color.white;
        this.noselectedForeground = Color.black;
        this.setForeground(this.noselectedForeground);
        this.setBackground(this.noselectedBackground);
        setEditable(false);
        setBorder(new EmptyBorder(0,0,0,0));
    }

    public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                  boolean selected,
                                                  boolean expanded, boolean leaf,
                                                  int row, boolean hasFocus) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        NodeData nd;


//        if (!nd.isOverflow()) {
        if (selected) {
            this.setForeground(this.selectedForeground);
            this.setBackground(this.selectedBackground);
        } else {
            this.setBackground(this.noselectedBackground);
            this.setForeground(this.noselectedForeground);
        }
//        }
//        else {
//            if(selected)
//            {
//                this.setBackground(this.overflowSelectedBG);
//                this.setForeground(this.overflowSelectedFG);
//            }
//            else
//            {
//                this.setBackground(this.overflowBackground);
//                this.setForeground(this.overflowForeground);
//            }
//        }

        this.setText(value.toString());
        return this;
    }

    private NodeData checkChild(DefaultMutableTreeNode childNode) {
        NodeData child = null;
//        int count = childNode.getChildCount();
//        for (int i = 0; i < count; i++) {
//            DefaultMutableTreeNode childNodes =
//                    (DefaultMutableTreeNode) childNode.getChildAt(i);
//            if (childNodes.isLeaf()) {
//                child = (NodeData) childNodes.getUserObject();
////                if(child.isOverflow())
////                {
////                    break;
////                }
//            } else {
//                child = checkChild(childNodes);
//            }
//        }
        return child;
    }

}