package cn.onestravel.utils;

import java.io.*;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;

/**
 * 读取属性文件version.properties
 */
public class PropertiesUtils {
    private static String basePath = System.getProperty("user.dir");

    /**
     * 将资源文件读取到一个map集合中
     * @param fileName
     * @return
     */
    public static HashMap<String, Object> readProperties(String fileName) {

        Properties prop = new Properties();
        InputStream in = null;
        HashMap<String, Object> map = new HashMap<>();
        try {
            File directory = new File("");//设定为当前文件夹
            //"/version.properties"
            in = PropertiesUtils.class.getClass().getResourceAsStream("/"+fileName);
            prop.load(new InputStreamReader(in, "GBK"));
//            prop.load(in);     ///加载属性列表
            Iterator<String> it = prop.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                map.put(key, prop.getProperty(key));
                System.out.println(key + ":" + prop.getProperty(key));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return map;
    }

    /**
     * 根据Key获取资源文件中的值
     * @param fileName
     * @param key
     * @return
     */
    public static Object getValue(String fileName, String key) {
        HashMap<String, Object> map = readProperties(fileName);
        return map.get(key);
    }
}
