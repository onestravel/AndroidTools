package cn.onestravel;


import cn.onestravel.utils.*;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.ArrayList;
import java.util.Enumeration;

public class Main {
    private JTabbedPane tabbedPane;
    private JPanel mainLayout;
    private JPanel formatPane;
    private JPanel encodePane;
    private JPanel aboutPane;
    private JPanel requestPane;
    private static int frameWidth = 1000;
    private static int frameHeight = 600;


    public static void main(String[] args) {
        if (!FileUtils.isExists((String) PropertiesUtils.getValue(PropertiesKeyUtils.PROPERTIES_NAME_BASE_CONFIG,PropertiesKeyUtils.HISTORY_URL))) {
            ArrayList arrayList = new ArrayList();
            arrayList.add("https://devapi.brzhongyi.cn:9090/easydoctorv2-ws/apiController");
            arrayList.add("http://dev.api.haoniuzhongyi.com:8080/easydoctorv2-ws/apiController");
            arrayList.add("http://api.brzhongyi.cn:8080/easydoctorv2-ws/apiController");
            FileUtils.saveList(arrayList, "historyUrl.config");
        }
        JFrame frame = new JFrame("JSONTools");
//        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/logo.png")));
        frame.setContentPane(new Main().mainLayout);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setBounds(SizeUtils.getScreenWidth() / 2 - frameWidth / 2, SizeUtils.getScreenHeight() / 2 - frameHeight / 2, frameWidth, frameHeight);
        FontClassUtils.loadIndyFont();
        InitGlobalFont(new Font("宋体", Font.PLAIN, 12));
    }

    /**
     * 统一设置字体，父界面设置之后，所有由父界面进入的子界面都不需要再次设置字体
     */
    private static void InitGlobalFont(Font font) {
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys();
             keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }
    }
}
