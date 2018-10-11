package cn.onestravel.utils;

import javax.net.ssl.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map.Entry;

public class HttpUtils {
	private static StringBuffer header;
	private static StringBuffer body;
	private static int status = 0;

	public static int getStatus() {
		return status;
	}

	public static String getResponseHeader(String path, String params, String request_type) {
		if ("POST".equals(request_type)) {
			if(path.contains("?")){
				path = path.substring(0, path.indexOf("?"));
			}
			System.out.println(path);
			http_post(path, params);
		} else {
			if(params!=null){
				String[] pars = params.split("&");
				if(pars!=null){
					for (String p : pars) {
						if(p!=null&&!path.contains(p)){
							if(path.contains("?")&&path.indexOf("?")!=path.length()-1){
								path=path+"&"+p;
							}else if(path.contains("?")&&path.indexOf("?")==path.length()-1){
								path=path+p;
							}else{
								path=path+"?"+p;
							}
						}
					}
				}
				System.out.println(path);
				http_get(path);
			}else{
				http_get(path);
			}
		}
		return header.toString();
	}

	public static String getResponseBody() {
		return body.toString();
	}

	private static void http_post(String path, String params) {
		ByteArrayOutputStream baos = null;
		InputStream is = null;
		HttpURLConnection urlConn;
		if (header == null) {
			header = new StringBuffer();
		}
		if (body == null) {
			body = new StringBuffer();
		}
		// 确定资源路径
		// String path = "http://10.9.153.38:8080/JavaEE0826/LoginServlet";
		// 需要传递的参数
		// String param = "username=zhangsan&password=123456";
		try {
			// 封装成URL对象
			URL url = new URL(path);
			// 创建HttpURLConnection连接
			if (url.getProtocol().toLowerCase().equals("https")) {
				 trustAllHosts();
				 HttpsURLConnection https = (HttpsURLConnection)url.openConnection();
	                https.setHostnameVerifier(DO_NOT_VERIFY);
	                urlConn = https;
	            } else {
	            	urlConn = (HttpURLConnection)url.openConnection();
	            }
			// 设置传输方式
			urlConn.setRequestMethod("POST");
			urlConn.setConnectTimeout(30000);
			// 设置输入输出连接
			urlConn.setDoInput(true);
			urlConn.setDoOutput(true);
			// 输出流，将分数写入服务器
			OutputStream os = urlConn.getOutputStream();
			os.write(params.getBytes());
			os.close();
			// 打开连接
			urlConn.connect();
			status = urlConn.getResponseCode();
			if (status == 200) {
				is = urlConn.getInputStream();
				baos = new ByteArrayOutputStream();
				byte[] b = new byte[1024];
				int n = 0;
				while ((n = is.read(b)) != -1) {
					baos.write(b, 0, n);
					baos.flush();
				}
				header.delete(0, header.length());
				for (Entry<String, List<String>> entry : urlConn.getHeaderFields().entrySet()) {
					header.append("" + entry.getValue() + "\n");
				}
				byte[] bs = baos.toByteArray();
				body.delete(0, body.length());
				body.append(new String(bs, "utf-8"));

			} else {
				header.delete(0, header.length());
				for (Entry<String, List<String>> entry : urlConn.getHeaderFields().entrySet()) {
					header.append("" + entry.getValue() + "\n");
				}
				body.delete(0, body.length());
				body.append(urlConn.getResponseCode() + " ");
				body.append(urlConn.getResponseMessage());
				baos = new ByteArrayOutputStream();
				is = urlConn.getErrorStream();
				byte[] b = new byte[1024];
				int n = 0;
				while ((n = is.read(b)) != -1) {
					baos.write(b, 0, n);
					baos.flush();
				}
				byte[] bs = baos.toByteArray();
				body.append("\n");
				body.append("\n");
				body.append(new String(bs, "utf-8"));

			}
		} catch (MalformedURLException e) {
			body.delete(0, body.length());
			body.append(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			body.delete(0, body.length());
			body.append(e.toString());
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (baos != null) {
				try {
					baos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private static void http_get(String path) {
		ByteArrayOutputStream baos = null;
		InputStream is = null;
		HttpURLConnection urlConn;
		if (header == null) {
			header = new StringBuffer();
		}
		if (body == null) {
			body = new StringBuffer();
		}
		// 确定资源路径
		// String path = "http://10.9.153.38:8080/JavaEE0826/LoginServlet";
		// 需要传递的参数
		// String param = "username=zhangsan&password=123456";
		try {
			// 封装成URL对象
			URL url = new URL(path);
			// 创建HttpURLConnection连接
			if (url.getProtocol().toLowerCase().equals("https")) {
				 trustAllHosts();
				 HttpsURLConnection https = (HttpsURLConnection)url.openConnection();
	                https.setHostnameVerifier(DO_NOT_VERIFY);
	                urlConn = https;
	            } else {
	            	urlConn = (HttpURLConnection)url.openConnection();
	            }
			// 设置传输方式
			urlConn.setRequestMethod("GET");
			urlConn.setConnectTimeout(30000);
			// 设置输入输出连接
			urlConn.setDoInput(true);
			// urlConn.setDoOutput(true);
			// 输出流，将分数写入服务器
			// OutputStream os = urlConn.getOutputStream();
			// os.write(params.getBytes());
			// os.close();
			// 打开连接
			urlConn.connect();
			status = urlConn.getResponseCode();
			if (status == 200) {
				is = urlConn.getInputStream();
				baos = new ByteArrayOutputStream();
				byte[] b = new byte[1024];
				int n = 0;
				while ((n = is.read(b)) != -1) {
					baos.write(b, 0, n);
					baos.flush();
				}
				header.delete(0, header.length());
				for (Entry<String, List<String>> entry : urlConn.getHeaderFields().entrySet()) {
					header.append("" + entry.getValue() + "\n");
				}
				byte[] bs = baos.toByteArray();
				body.delete(0, body.length());
				body.append(new String(bs, "utf-8"));

			} else {
				header.delete(0, header.length());
				for (Entry<String, List<String>> entry : urlConn.getHeaderFields().entrySet()) {
					header.append("" + entry.getValue() + "\n");
				}
				body.delete(0, body.length());
				body.append(urlConn.getResponseCode() + " ");
				body.append(urlConn.getResponseMessage());
				baos = new ByteArrayOutputStream();
				is = urlConn.getErrorStream();
				byte[] b = new byte[1024];
				int n = 0;
				while ((n = is.read(b)) != -1) {
					baos.write(b, 0, n);
					baos.flush();
				}
				byte[] bs = baos.toByteArray();
				body.append("\n");
				body.append("\n");
				body.append(new String(bs, "utf-8"));

			}
		} catch (MalformedURLException e) {
			body.delete(0, body.length());
			body.append(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			body.delete(0, body.length());
			body.append(e.toString());
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (baos != null) {
				try {
					baos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {

		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	};

	/**
	 * Trust every server - dont check for any certificate
	 */
	private static void trustAllHosts() {
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[] {};
			}

			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				System.out.println("checkClientTrusted");
			}

			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				System.out.println("checkServerTrusted");
			}
		} };

		// Install the all-trusting trust manager
		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
