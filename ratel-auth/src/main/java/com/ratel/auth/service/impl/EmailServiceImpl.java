/**
* @文件名:EmailServiceImpl.java
* @版权:Copyright 2018 版权所有：平头哥
* @描述:邮件操作
* @修改人:Stephen
* @修改时间:2018年12月22日 下午5:48:00
* @修改内容:新增
*/
package com.ratel.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ratel.auth.domain.Email;
import com.ratel.auth.service.IEmailService;

/**
 * @描述
 * @文件名:EmailServiceImpl.java
 * @版权:Copyright 2018 版权所有：平头哥
 * @描述:邮件操作实现类
 * @修改人:Stephen
 * @修改时间:2018年12月22日 下午5:48:00
 * @修改内容:新增
 */
@Service
public class EmailServiceImpl implements IEmailService {

	@Autowired
	private JavaMailSender jms;

	@Value("${spring.mail.username}")
	private String from;

	@Override
	public boolean sendSimpleEmail(Email email) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			String[] ccArr = { "ratel_stephen@163.com" };
			message.setFrom(from);
			message.setCc(ccArr);
			message.setTo(email.getTo());
			message.setSubject(email.getSubject());
			message.setText(email.getContent());
			jms.send(message);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
