package com.ratel.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * ClassName: StringUtil <br/>
 * Function: 字符串处理工具类. <br/>
 * date: 2018年2月11日 上午10:57:06 <br/>
 *
 * @author wenzhang
 * @version
 * @see [相关类/方法]
 * @Description:
 * @since JDK 1.8
 */
public abstract class StringUtil extends StringUtils {

	// 包含大小写字母和数字的数组
	private final static String[] ARR = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
			"P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
			"k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4",
			"5", "6", "7", "8", "9" };

	/**
	 * 判断是否为null或者长度为0的空字符串
	 * 
	 * @Title isEmpty
	 * @author wanglc
	 * @Description:
	 * @date 2013-12-6
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	/**
	 * 字符串转化小写
	 * 
	 * @Title getLowerStr
	 * @author wanglc
	 * @Description:
	 * @date 2013-12-6
	 * @param str
	 * @return
	 */
	public static String getLowerStr(String str) {
		return str.toLowerCase();
	}

	/**
	 * 字符串转化大写
	 * 
	 * @Title getUpperStr
	 * @author wanglc
	 * @Description:
	 * @date 2013-12-6
	 * @param str
	 * @return
	 */
	public static String getUpperStr(String str) {
		return str.toUpperCase();
	}

	/**
	 * 去除字符串中的空格、回车、换行符、制表符
	 * 
	 * @Title replaceBlank
	 * @author wanglc
	 * @Description:
	 * @date 2013-12-6
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("").replaceAll("　", "");
		}
		return dest;
	}

	/**
	 * 
	 * @Description:按照格式切割字符串。若出现异常或空，则返回null
	 *
	 * @author wenzhang
	 * @date:2018年2月11日 上午10:57:50
	 * @Title:split
	 * @param str   需要切割的字符串
	 * @param regex 切割格式
	 * @return 字符串数组
	 * @since JDK 1.8
	 */
	public static String[] split(String str, String regex) {
		if (StringUtil.isBlank(str) || StringUtil.isBlank(regex)) {
			return null;
		}
		String[] strArray = str.split(regex);
		if (strArray != null && strArray.length > 0) {
			return strArray;
		} else {
			return null;
		}
	}

	/**
	 * 字符串数组元素以,连接
	 * 
	 * @param arr 字符串数组
	 * @return
	 */
	public static String arrToString(String[] arr) {
		try {
			StringBuffer sb = new StringBuffer();
			if (null != arr && arr.length > 0) {
				for (String str : arr) {
					sb.append(str + ",");
				}
				return sb.toString();
			} else {
				return "";
			}
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * @Title randomStr
	 * @author :Stephen
	 * @Description 生成随机的字符串
	 * @date 2018年12月22日 上午11:25:11
	 * @param strLenth 字符串长度
	 * @return String 随机字符串
	 */
	public static String randomStr(int strLenth) {
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < strLenth; i++) {
			int randomIndex = (int) (Math.random() * (ARR.length - 1));
			sb.append(ARR[randomIndex]);
		}
		return sb.toString();
	}

	/**
	 * 
	 * @Title email2Lower
	 * @author :Stephen
	 * @Description 邮箱后缀统一转小写
	 * @date 2019年1月2日 上午9:31:16
	 * @param email 邮箱地址
	 * @return String
	 */
	public static String email2Lower(String email) {
		if (!StringUtil.isEmpty(email)) {
			String[] emailArr = email.split("@");
			return emailArr[0] + "@" + emailArr[1].toLowerCase();
		}
		return null;
	}
}
