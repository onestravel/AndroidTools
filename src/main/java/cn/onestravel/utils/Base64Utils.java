package cn.onestravel.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;

public class Base64Utils {

	 //加密
    public static String getBase64(String str){
        byte[]  b = null;
        String result = "";
        if(str!=null){
	        try {
	            b = str.getBytes("utf-8");
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
	        if( b != null){
	        	result = new BASE64Encoder().encode(b);
	        }
        }
        return result;

    }

    //解密
    public static String getFromBase64(String str){
        byte[] b = null;
        String result = "";
        if(str != null){
             BASE64Decoder decoder = new BASE64Decoder();  
             try {  
                    b = decoder.decodeBuffer(str);  
                    result = new String(b, "utf-8");  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
        }
        return result;
    }
}
