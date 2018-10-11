package cn.onestravel.utils;

import javax.swing.*;
import java.awt.*;
/**
 * 弹框类
 * */
public class DialogUtils {

    /**
     * 提示弹框
     * @param parentComponent
     * @param message
     */
    public static void showHintDialog(Component parentComponent,
                               Object message) {
        JOptionPane.showMessageDialog(parentComponent, message, "提示消息",
                JOptionPane.WARNING_MESSAGE);
    }

}
