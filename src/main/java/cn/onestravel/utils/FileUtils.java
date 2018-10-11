package cn.onestravel.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class FileUtils {
    private static String basePath = System.getProperty("user.dir") + "/data/";

    public static boolean isExists(String fileName) {
        String path = basePath + fileName;
        File filePath = new File(path);
        return filePath.exists();
    }

    public static ArrayList getList(String fileName) {
        InputStreamReader reader = null;
        BufferedReader br = null;
        ArrayList<String> list = new ArrayList<String>();
        try {
            String path = basePath + fileName;
            File filePath = new File(basePath);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            reader = new InputStreamReader(new FileInputStream(path));
            br = new BufferedReader(reader);
            String line = "";
            Integer max = null;
            Integer min = null;

            while ((line = br.readLine()) != null) {
                Scanner sca = new Scanner(line.trim());
                while (sca.hasNext()) {
                    list.add(sca.next());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(list);
        return list;
    }


    public static void saveList(ArrayList<String> list, String fileName) {
        try {
            String path = basePath + fileName;
            File filePath = new File(basePath);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter out;

            out = new BufferedWriter(new FileWriter(file));
            for (String s : list) {
                out.write(s + "\r\n");
            }
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
