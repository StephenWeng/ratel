/**
* @文件名:IEmailService.java
* @版权:Copyright 2018 版权所有：平头哥
* @描述:邮件操作
* @修改人:Stephen
* @修改时间:2018年12月22日 下午5:47:16
* @修改内容:新增
*/
package com.ratel.auth.service;

import com.ratel.auth.domain.Email;

/**
 * @文件名:IEmailService.java
 * @版权:Copyright 2018 版权所有：平头哥
 * @描述:邮件操作接口
 * @修改人:Stephen
 * @修改时间:2018年12月22日 下午5:47:16
 * @修改内容:新增
 */
public interface IEmailService {

	/**
	 * @Title sendEmail
	 * @author :技术部-文章
	 * @Description 发送邮件
	 * @date 2018年12月23日 下午8:06:04
	 * @param param
	 * @param email
	 * @return boolean true：发送成功 false：发送失败
	 */
	public boolean sendSimpleEmail(Email email);

}
