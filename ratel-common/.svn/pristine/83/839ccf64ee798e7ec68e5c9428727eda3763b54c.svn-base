package com.dqgb.common.utils;

/**
 * 
 * ClassName: MessageUtil <br/>
 * Function: 信息处理工具类. <br/>
 * date: 2018年2月11日 上午10:59:25 <br/>
 *
 * @author wenzhang
 * @version
 * @see [相关类/方法]
 * @Description:
 * @since JDK 1.8
 */
public class MessageUtil {

	/**
	 * @author wenzhang
	 * @date:2018年2月11日 上午10:59:41
	 * @Title:getMessage
	 * @param template
	 * @param keys
	 * @return
	 * @since JDK 1.8
	 */
	public static String getMessage(String template, String... keys) {
		int count = 0;
		for (String key : keys) {
			template = template.replace("{" + count++ + "}", key);
		}
		return template;
	}

}
