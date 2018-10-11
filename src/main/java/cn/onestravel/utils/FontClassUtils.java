package cn.onestravel.utils;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Enumeration;

public class FontClassUtils {
    public static void loadIndyFont() {
        try {

            String pathString = System.getProperty("user.dir") + "/resource/font/songti.ttf";
//            Font dynamicFont = Font.createFont(Font.TRUETYPE_FONT, new File(pathString));
            Font dynamicFont = new Font("STXihei",Font.TRUETYPE_FONT,15);
            initGlobalFontSetting(dynamicFont);
//            UIManager.put("ToolTip.font", dynamicFont);
//
//            UIManager.put("Table.font", dynamicFont);
//
//            UIManager.put("TableHeader.font", dynamicFont);
//
//            UIManager.put("TextField.font", dynamicFont);
//
//            UIManager.put("ComboBox.font", dynamicFont);
//
//            UIManager.put("TextField.font", dynamicFont);
//
//            UIManager.put("PasswordField.font", dynamicFont);
//
//            UIManager.put("TextArea.font", dynamicFont);
//
//            UIManager.put("TextPane.font", dynamicFont);
//
//            UIManager.put("EditorPane.font", dynamicFont);
//
//            UIManager.put("FormattedTextField.font", dynamicFont);
//
//            UIManager.put("Button.font", dynamicFont);
//
//            UIManager.put("CheckBox.font", dynamicFont);
//
//            UIManager.put("RadioButton.font", dynamicFont);
//
//            UIManager.put("ToggleButton.font", dynamicFont);
//
//            UIManager.put("ProgressBar.font", dynamicFont);
//
//            UIManager.put("DesktopIcon.font", dynamicFont);
//
//            UIManager.put("TitledBorder.font", dynamicFont);
//
//            UIManager.put("Label.font", dynamicFont);
//
//            UIManager.put("List.font", dynamicFont);
//
//            UIManager.put("TabbedPane.font", dynamicFont);
//
//            UIManager.put("MenuBar.font", dynamicFont);
//
//            UIManager.put("Menu.font", dynamicFont);
//
//            UIManager.put("MenuItem.font", dynamicFont);
//
//            UIManager.put("PopupMenu.font", dynamicFont);
//
//            UIManager.put("CheckBoxMenuItem.font", dynamicFont);
//
//            UIManager.put("RadioButtonMenuItem.font", dynamicFont);
//
//            UIManager.put("Spinner.font", dynamicFont);
//
//            UIManager.put("Tree.font", dynamicFont);
//
//            UIManager.put("ToolBar.font", dynamicFont);
//
//            UIManager.put("OptionPane.messageFont", dynamicFont);
//
//            UIManager.put("OptionPane.buttonFont", dynamicFont);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void initGlobalFontSetting(Font fnt)

    {
        FontUIResource fontRes = new FontUIResource(fnt);
        for(Enumeration keys = UIManager.getDefaults().keys(); keys.hasMoreElements();)

        {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if(value instanceof FontUIResource)
                UIManager.put(key, fontRes);
        }

    }
}
