/**  
* @文件名 Const.java
* @版权 Copyright 2009-2017 版权所有：大庆金桥信息技术工程有限公司
* @描述 系统常量
* @修改人 Mr.Wang
* @修改时间 2017年5月11日 下午2:47:12
* @修改内容 新增
*/
package com.dqgb.common.utils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.springframework.stereotype.Component;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.Type;

/**
 * 系统常量
 * 
 * @author Mr.Wang
 * @version V1.0,2017年5月11日 下午2:47:12
 * @see [相关类/方法]
 * @since V1.0
 * 
 */
@Component
public class EmailUtil {
	/**
	 * 
	 * @Description:验证邮箱是否满足邮件格式
	 *
	 * @author wenzhang
	 * @date:2018年2月12日 上午10:24:36
	 * @Title:checkEmail
	 * @param from
	 *            发件人
	 * @param to
	 *            收件人
	 * @param cc
	 *            抄送人
	 * @return
	 * @since JDK 1.8
	 */
	public static boolean checkEmail(String from, String to, String cc) {
		if (checkEmailAddress(from) && StringUtil.isNotBlank(from) && checkEmailAddress(to)
				&& StringUtil.isNotBlank(to)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @Description:验证邮箱是否满足邮箱格式
	 *
	 * @author wenzhang
	 * @date:2018年2月12日 上午10:25:08
	 * @Title:checkEmailAddress
	 * @param address
	 *            邮箱地址
	 * @return
	 * @since JDK 1.8
	 */
	public static boolean checkEmailAddress(String address) {
		boolean flag = false;
		try {
			String check = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(address);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证邮箱是否真实有效
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isRealEmail(String email) {
		if (!email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
			return false;
		}
		String host = "";
		String hostName = email.split("@")[1];
		Record[] result = null;
		SMTPClient client = new SMTPClient();
		try {
			// 查找MX记录
			Lookup lookup = new Lookup(hostName, Type.MX);
			lookup.run();
			if (lookup.getResult() != Lookup.SUCCESSFUL) {
				return false;
			} else {
				result = lookup.getAnswers();
			}
			// 连接到邮箱服务器
			for (int i = 0; i < result.length; i++) {
				host = result[i].getAdditionalName().toString();
				client.connect(host);
				if (!SMTPReply.isPositiveCompletion(client.getReplyCode())) {
					client.disconnect();
					continue;
				} else {
					break;
				}
			}
			// 以下2项自己填写快速的，有效的邮箱
			client.login("163.com");
			client.setSender("sxgkwei@163.com");
			client.addRecipient(email);
			if (250 == client.getReplyCode()) {
				return true;
			}
		} catch (Exception e) {

		} finally {
			try {
				client.disconnect();
			} catch (IOException e) {
			}
		}
		return false;
	}
}
