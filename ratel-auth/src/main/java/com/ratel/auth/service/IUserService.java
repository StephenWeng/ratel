package com.ratel.auth.service;

import javax.servlet.http.HttpServletResponse;

import com.ratel.auth.domain.User;
import com.ratel.common.response.ResponseData;

/**
 * @文件名:IUserService.java
 * @版权:Copyright 2018 版权所有：平头哥
 * @描述:用户相关操作业务层接口
 * @修改人:Stephen
 * @修改时间:2018年12月13日 下午4:34:11
 * @修改内容:新增
 */
public interface IUserService {

	public ResponseData test();

	public ResponseData page(Integer current, Integer pageSize);

	/**
	 *
	 * @Title queryUser
	 * @author :Stephen
	 * @Description 根据用户账号查询用户,不模糊
	 * @date 2018年12月22日 上午11:08:23
	 * @param account
	 *            用户账号
	 * @return ResponseData 操作是否成功，data：user对象
	 */
	public ResponseData queryUser(String account);

	/**
	 *
	 * @Title login
	 * @author :Stephen
	 * @Description 用户登录
	 * @date 2018年12月22日 上午11:09:06
	 * @param user
	 *            包含用户账号和密码
	 * @param response
	 *            用于存储cookie，jwt令牌
	 * @return ResponseData 操作是否成功，data：user对象
	 */
	public ResponseData login(User user, HttpServletResponse response);

	/**
	 * @Title editInformation
	 * @author :Stephen
	 * @Description 用户首页修改葛粉信息
	 * @date 2018年12月22日 上午11:09:42
	 * @param user
	 * @return ResponseData 操作是否成功，data：user对象
	 */
	public ResponseData editInformation(User user);

	/**
	 * @Title sendSecurityCode
	 * @author :技术部-文章
	 * @Description 向用户邮箱发送重置密码的验证码
	 * @date 2018年12月23日 下午7:18:55
	 * @param account
	 *            用户账号
	 * @param email
	 *            用户邮箱
	 * @return ResponseData 操作是否成功
	 */
	public ResponseData sendSecurityCode(User user);

	/**
	 * @Title reset
	 * @author :Stephen
	 * @Description 重置密码，将密码发送至用户邮箱
	 * @date 2018年12月22日 上午11:09:42
	 * @param user
	 *            包含用户账号和密码
	 * @return ResponseData 操作是否成功，data：user对象
	 */
	public ResponseData reset(User user);

	/**
	 * @Title resetSelf
	 * @author :Stephen
	 * @Description 用户首页重置密码
	 * @date 2018年12月22日 上午11:09:42
	 * @param user
	 *            包含用户账号和密码
	 * @return ResponseData 操作是否成功，data：user对象
	 */
	public ResponseData resetSelf(User user);

	/**
	 * @Title checkPwd
	 * @author :Stephen
	 * @Description 首页编辑密码，检验原密码是否正确
	 * @date 2018年12月22日 上午11:09:42
	 * @param user
	 *            包含用户账号和密码
	 * @return ResponseData 操作是否成功
	 */
	public ResponseData checkPwd(User user);

	/**
	 * @Title checkEmail
	 * @author :Stephen
	 * @Description 首页编辑邮箱，检验邮箱是否可用
	 * @date 2018年12月22日 上午11:09:42
	 * @param user
	 *            包含用户账号和邮箱
	 * @return ResponseData 操作是否成功
	 */
	public ResponseData checkEmail(User user);

}
