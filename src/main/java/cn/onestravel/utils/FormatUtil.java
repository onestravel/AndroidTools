package cn.onestravel.utils;


/**
 * 格式化输入工具类
 * 
 * @author lizhgb
 * @date 2015-10-14
 *
 */
public final class FormatUtil {

	/**
	 * 打印输入到控制台
	 * 
	 * @param jsonStr
	 * @author lizhgb
	 * @Date 2015-10-14 下午1:17:22
	 */
	public static void printJson(String jsonStr) {
		System.out.println(formatJson(jsonStr));
	}

	/**
	 * 格式化
	 * 
	 * @param jsonStr
	 * @return
	 * @author lizhgb
	 * @Date 2015-10-14 下午1:17:35
	 */
	public static String formatJson(String jsonStr) {
		if (null == jsonStr || "".equals(jsonStr))
			return "";
		jsonStr = jsonStr.replace('\n', '\0');
		jsonStr = jsonStr.replace('\r', '\0');
		jsonStr = jsonStr.replace("    ", "");
		StringBuilder sb = new StringBuilder();
		char last = '\0';
		char current = '\0';
		int indent = 0;
		for (int i = 0; i < jsonStr.length(); i++) {
			last = current;
			current = jsonStr.charAt(i);
			switch (current) {
			case '{':
			case '[':
				sb.append(current);
				sb.append('\n');
				indent++;
				addIndentBlank(sb, indent);
				break;
			case '}':
			case ']':
				sb.append('\n');
				indent--;
				addIndentBlank(sb, indent);
				sb.append(current);
				break;
			case ',':
				sb.append(current);
				if (last != '\\') {
					sb.append('\n');
					addIndentBlank(sb, indent);
				}
				break;
			default:
				sb.append(current);
			}
		}

		return sb.toString();
	}

	/**
	 * 添加space
	 * 
	 * @param sb
	 * @param indent
	 * @author lizhgb
	 * @Date 2015-10-14 上午10:38:04
	 */
	private static void addIndentBlank(StringBuilder sb, int indent) {
		for (int i = 0; i < indent; i++) {
			sb.append("    ");
		}
	}

	/**
	 * html 必须是格式良好的
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String formatHtml(String str) {

		if (null == str || "".equals(str))
			return "";
		str = str.replace('\n', '\0');
		str = str.replace('\r', '\0');
		str = str.replace("    ", "");
		StringBuilder sb = new StringBuilder();
		char last = '\0';
		char current = '\0';
		int indent = 0;
		for (int i = 0; i < str.length(); i++) {
			
			current = str.charAt(i);
			if(i<str.length()-1){
			last = str.charAt(i+1);
			if('<'==current&&'/'!=last){
				
				sb.append("\n");
				indent++;
				addIndentBlank(sb, indent);
				sb.append(current);
			}else if('<'==current&&'/'==last){
				sb.append('\n');
				indent--;
				addIndentBlank(sb, indent);
				sb.append(current);
			}else{
				sb.append(current);
			}
		}else{
			sb.append(current);
			}
		}

		return sb.toString();

	}
}