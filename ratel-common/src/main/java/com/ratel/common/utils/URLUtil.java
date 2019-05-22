package com.ratel.common.utils;

import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * 
 * ClassName: URLUtil <br/>
 * Function: 链接地址处理工具类. <br/>
 * date: 2018年2月11日 上午10:51:11 <br/>
 *
 * @author wenzhang
 * @version
 * @see [相关类/方法]
 * @Description:
 * @since JDK 1.8
 */
public class URLUtil {

	private static Logger logger = Logger.getLogger(URLUtil.class);

	/**
	 * 
	 * @Description:判断是否为连接.
	 *
	 * @author wenzhang
	 * @date:2018年2月11日 上午10:51:26
	 * @Title:isConnect
	 * @param urlStr
	 * @return true：是；false否
	 * @since JDK 1.8
	 */
	public static synchronized boolean isConnect(String urlStr) {
		int counts = 0;
		if (urlStr == null || urlStr.length() <= 0) {
			return false;
		}
		while (counts < 3) {
			try {
				URL url = new URL(urlStr);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				int state = con.getResponseCode();
				if (state == 200) {
					return true;
				}
				break;
			} catch (Exception ex) {
				counts++;
				continue;
			}
		}
		return false;
	}

	/**
	 * @author wenzhang
	 * @date:2018年2月11日 上午10:51:58
	 * @Title:getDomainUrl
	 * @param urlStr
	 * @return
	 * @since JDK 1.8
	 */
	public static String getDomainUrl(String urlStr) {
		String domainUrl = urlStr;
		try {
			URL url = new URL(urlStr);
			domainUrl = url.getProtocol() + "://" + url.getAuthority();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("getDomainUrl is erro,url :" + urlStr, e);
		}
		return domainUrl;
	}

	/**
	 * 
	 * @Description:获取连接地址的host.
	 *
	 * @author wenzhang
	 * @date:2018年2月11日 上午10:52:11
	 * @Title:getHost
	 * @param urlStr
	 * @return
	 * @since JDK 1.8
	 */
	public static String getHost(String urlStr) {
		String host = urlStr;
		try {
			URL url = new URL(urlStr);
			host = url.getHost();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("getHost is erro,url :" + urlStr, e);
		}
		return host;
	}

	/**
	 * 获取真实客户端ip
	 * 
	 * @param request
	 *            请求
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknow".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
				// 根据网卡获取本机配置的IP地址
				InetAddress inetAddress = null;
				try {
					inetAddress = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
				}
				ipAddress = inetAddress.getHostAddress();
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实的IP地址，多个IP按照','分割
		if (null != ipAddress && ipAddress.length() > 15) {
			// "***.***.***.***".length() = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}
	
	public static String[] stringToStringArray(String prmString) throws Exception {
		/*
		 * Convert this string into an array such that another method could be invoked.
		 */
		int tempCounter = 1;
		for (int i = 0; i < prmString.length(); i++) {
			if (prmString.charAt(i) == ',') {
				tempCounter++;
			} // Of if
		} // Of for i

		String[] theStringArray = new String[tempCounter];

		String remainString = new String(prmString) + ",";
		for (int i = 0; i < tempCounter; i++) {
			theStringArray[i] = remainString.substring(0, remainString.indexOf(",")).trim();
			if (theStringArray[i].equals("")) {
				throw new Exception("Error occurred in common.SimpleTool.stringToStringArray()."
						+ "\n\tBlank attribute or data is not allowed as a data. " + "\n\tThe string is:" + prmString);
			} // Of if
				// Common.println(theStringArray[i]);
			remainString = remainString.substring(remainString.indexOf(",") + 1);
			// Common.println("remainString: " + remainString);
		} // Of for i

		return theStringArray;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(URLUtil.stringToStringArray("dasdas,dasdas,dasdasdas"));
	}

}
