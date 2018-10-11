package cn.onestravel.layout;

import cn.onestravel.utils.Base64Utils;
import cn.onestravel.utils.MD5Utils;
import cn.onestravel.view.JTextAreaMenu;
import sun.security.provider.MD5;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class EncodeLayout extends JPanel {
    private JPanel encodeRoot;
    private JPanel JpanelMd5Encryption;
    private JButton btnMd5Encode;
    private JPanel JpanelBase64;
    private JPanel JpanelEncode;
    private JTextAreaMenu taMd5Origin;
    private JTextAreaMenu taMd5Encode;
    private JButton btnBaseEncryption;
    private JButton btnBaseDecode;
    private JTextAreaMenu taBaseOrigin;
    private JTextAreaMenu taBase;
    private JButton btnUrlEncoding;
    private JButton btnUrlDecode;
    private JTextAreaMenu taUrlEncodeOrigin;
    private JTextAreaMenu taUrlEncode;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public EncodeLayout() {
        md5Encryption();
        baseEncryption();
        baseDecode();
        urlEncode();
        UrlDecode();
    }

    /**
     * UrlEncode转码
     */
    private void UrlDecode() {
        btnBaseDecode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = taUrlEncodeOrigin.getText().trim().toString();
                String encodeStr = "";
                try {
                    if (str != null) {
                        encodeStr = URLDecoder.decode(str, "utf-8");
                    }
                } catch (Exception ecxeption) {
                    ecxeption.printStackTrace();
                }
                if (encodeStr != null) {
                    taUrlEncode.setText(encodeStr);
                }
            }
        });
    }

    /**
     * UrlEncode编码
     */
    private void urlEncode() {
        btnUrlEncoding.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = taUrlEncodeOrigin.getText().trim().toString();
                String encodeStr = "";
                try {
                    if (str != null) {
                        encodeStr = URLEncoder.encode(str, "utf-8");
                    }
                } catch (Exception ecxeption) {
                    ecxeption.printStackTrace();
                }
                if (encodeStr != null) {
                    taUrlEncode.setText(encodeStr);
                }
            }
        });
    }

    /**
     * base64解密
     */
    private void baseDecode() {
        btnBaseDecode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String encodeStr = taBaseOrigin.getText().trim().toString();
                String str = Base64Utils.getFromBase64(encodeStr);
                if (str != null) {
                    taBase.setText(str);
                }
            }
        });
    }

    /**
     * Base64加密
     */
    private void baseEncryption() {
        btnBaseEncryption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = taBaseOrigin.getText().trim().toString();
                String encodeStr = Base64Utils.getBase64(str);
                if (encodeStr != null) {
                    taBase.setText(encodeStr);
                }
            }
        });
    }


    /**
     * MD5加密操作
     */
    private void md5Encryption() {
        btnMd5Encode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = taMd5Origin.getText().trim().toString();
                StringBuilder sBuilder = new StringBuilder();
                sBuilder.append("16位  小写：");
                sBuilder.append(MD5Utils.md5ToL16(str));
                sBuilder.append("\n");
                sBuilder.append("16位  大写：");
                sBuilder.append(MD5Utils.md5ToU16(str));
                sBuilder.append("\n");
                sBuilder.append("32位  小写：");
                sBuilder.append(MD5Utils.md5ToL32(str));
                sBuilder.append("\n");
                sBuilder.append("32位  大写：");
                sBuilder.append(MD5Utils.md5ToU32(str));
                sBuilder.append("\n");
                taMd5Encode.setText(sBuilder.toString());
            }
        });
    }


}
