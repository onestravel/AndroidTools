package cn.onestravel.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

	/**
	 * 获取字符串的32位小写MD5值
	 * @param sourceStr
	 * @return
	 */
	public static String md5ToL32(String sourceStr) {
		return _md5ToL32(sourceStr);
	}
	
	/**
	 * 获取字符串的32位大写MD5值
	 * @param sourceStr
	 * @return
	 */
	public static String md5ToU32(String sourceStr) {
		return _md5ToU32(sourceStr);
	}
	
	/**
	 * 获取字符串的16位小写MD5值
	 * @param sourceStr
	 * @return
	 */
	public static String md5ToL16(String sourceStr) {
		return _md5ToL16(sourceStr);
	}
	
	/**
	 * 获取字符串的16位大写MD5值
	 * @param sourceStr
	 * @return
	 */
	public static String md5ToU16(String sourceStr) {
		return _md5ToU16(sourceStr);
	}
	
	
	/**
	 * 将字符串转成32位小写MD5值
	 * @param sourceStr
	 * @return
	 */
	private static String _md5ToL32(String sourceStr) {
		return MD5(sourceStr);
	}
	
	/**
	 * 将字符串转成32位大写MD5值
	 * @param sourceStr
	 * @return
	 */
	private static String _md5ToU32(String sourceStr) {
		return _md5ToL32(sourceStr).toUpperCase();
	}
	
	/**
	 * 将字符串转成16位小写MD5值
	 * @param sourceStr
	 * @return
	 */
	private static String _md5ToL16(String sourceStr) {
		if(sourceStr!=null){
			String md5ToL32 = MD5(sourceStr);
			if(md5ToL32!=null&&md5ToL32.length()>24){
			return md5ToL32.substring(8, 24);
			}
		}
			return "";
	}
	
	/**
	 * 将字符串转成16位大写MD5值
	 * @param sourceStr
	 * @return
	 */
	private static String _md5ToU16(String sourceStr) {
		return _md5ToL16(sourceStr).toUpperCase();
	}
	
	
	/**
	 * 将字符串转成32位小写MD5值
	 * @param sourceStr
	 * @return
	 */
	private static String MD5(String sourceStr) {
		String result = "";
		if(sourceStr!=null){
		    try {
		      MessageDigest md = MessageDigest.getInstance("MD5");
		      md.update(sourceStr.getBytes());
		      byte b[] = md.digest();
		      int i;
		      StringBuffer buf = new StringBuffer("");
		      for (int offset = 0; offset < b.length; offset++) {
		        i = b[offset];
		        if (i < 0)
		          i += 256;
		        if (i < 16)
		          buf.append("0");
		        buf.append(Integer.toHexString(i));
		      }
		      result = buf.toString();
//		      System.out.println("MD5(" + sourceStr + ",32) = " + result);
//		      System.out.println("MD5(" + sourceStr + ",16) = " + buf.toString().substring(8, 24));
		    } catch (NoSuchAlgorithmException e) {
		      System.out.println(e);
		    }
		}
	    return result;
	}
	
	
//	private static boolean isEmpty(String value) {
//		if(value==null||value.length()==0){
//			return true;
//		}
//		return false;
//		
//	}
}
