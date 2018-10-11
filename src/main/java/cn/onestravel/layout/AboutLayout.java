package cn.onestravel.layout;

import cn.onestravel.utils.PropertiesKeyUtils;
import cn.onestravel.utils.PropertiesUtils;

import javax.swing.*;
import java.util.HashMap;
import java.util.Properties;

public class AboutLayout extends JPanel {

    private JPanel root;
    private JLabel versionCode;
    private JLabel contactWay;
    private JLabel qqContact;
    private JLabel emailContact;
    private JLabel copywrite;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public AboutLayout() {
        initViewData();
    }

    private void initViewData() {
        HashMap<String, Object> map = PropertiesUtils.readProperties(PropertiesKeyUtils.PROPERTIES_NAME_VERSION);
        versionCode.setText(map.get(PropertiesKeyUtils.VERSION_NAME)+" - "+map.get(PropertiesKeyUtils.VERSION_ID));
        qqContact.setText("Q  Q："+map.get(PropertiesKeyUtils.QQ)+"");
        emailContact.setText("Email："+map.get(PropertiesKeyUtils.EMAIL)+"");
        copywrite.setText(map.get(PropertiesKeyUtils.COPYRIGHT)+"");
    }
}
