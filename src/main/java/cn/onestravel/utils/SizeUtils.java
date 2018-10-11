package cn.onestravel.utils;

import java.awt.*;

public class SizeUtils {
    /**
     * 获取屏幕宽度
     * @return
     */
    public static int getScreenWidth(){
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        return  width;
    }

    /**
     * 获取屏幕高度
     * @return
     */
    public static int getScreenHeight(){
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        return  height;
    }
}
