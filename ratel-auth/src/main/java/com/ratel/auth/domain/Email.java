/**
* @描述
* @文件名:Email.java
* @版权:Copyright 2018 版权所有：大庆金桥信息技术工程有限公司成都分公司
* @描述:Email.java
* @修改人:技术部-文章
* @修改时间:2018年12月23日 下午8:04:20
* @修改内容:新增
*/
package com.ratel.auth.domain;

/**
 * @文件名:Email.java
 * @版权:Copyright 2018 版权所有：大庆金桥信息技术工程有限公司成都分公司
 * @描述:自定义邮箱对象
 * @修改人:技术部-文章
 * @修改时间:2018年12月23日 下午8:04:20
 * @修改内容:新增
 */
public class Email {

	private String from;// 发送人

	private String to;// 接收人

	private String cc;// 抄送人

	private String subject;// 邮件主题

	private String content;// 邮件内容

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 *
	 * @param from
	 * @param to
	 * @param cc
	 * @param subject
	 * @param content
	 */
	public Email(String to, String cc, String subject, String content) {
		super();
		this.to = to;
		this.cc = cc;
		this.subject = subject;
		this.content = content;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
