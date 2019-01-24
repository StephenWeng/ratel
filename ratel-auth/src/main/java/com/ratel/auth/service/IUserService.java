package com.ratel.auth.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

	/**
	 * @Title queryUserTree
	 * @author :Stephen
	 * @Description 查询用户树，没有顶层组织
	 * @date 2019年1月11日 上午10:30:08
	 * @return ResponseData
	 */
	public ResponseData queryUserTree();

	/**
	 * 
	 * @Title queryUserPage
	 * @author :Stephen
	 * @Description 分页查询用户信息
	 * @date 2019年1月11日 上午10:15:37
	 * @param current  当前页
	 * @param pageSize 每页个数
	 * @param userDo   可能包含 昵称，账号，邮箱
	 * @return ResponseData
	 */
	public ResponseData queryUserPage(Integer current, Integer pageSize, User userDo);

	/**
	 * @Title addUser
	 * @author :Stephen
	 * @Description 新增用户，密码通过邮箱发送
	 * @date 2019年1月11日 下午2:34:11
	 * @param user 用户对象
	 * @return ResponseData
	 */
	public ResponseData addUser(User user);

	/**
	 * @Title updateUser
	 * @author :Stephen
	 * @Description 更新用户信息
	 * @date 2019年1月11日 下午2:41:43
	 * @param user 用户对象
	 * @return ResponseData
	 */
	public ResponseData updateUser(User user);

	/**
	 * @Title updateRole
	 * @author :Stephen
	 * @Description 更新用户角色信息
	 * @date 2019年1月18日 下午4:56:13
	 * @param user 用户对象
	 * @return ResponseData
	 */
	public ResponseData updateRole(User user);

	/**
	 * @Title delUsers
	 * @author :Stephen
	 * @Description 删除用户
	 * @date 2019年1月11日 下午2:45:31
	 * @param ids 用户id集合
	 * @return ResponseData
	 */
	public ResponseData delUsers(List<String> ids);

	/**
	 *
	 * @Title queryUser
	 * @author :Stephen
	 * @Description 根据用户账号查询用户,不模糊
	 * @date 2018年12月22日 上午11:08:23
	 * @param account 用户账号
	 * @return ResponseData 操作是否成功，data：user对象
	 */
	public ResponseData queryUser(String account);

	/**
	 *
	 * @Title login
	 * @author :Stephen
	 * @Description 用户登录
	 * @date 2018年12月22日 上午11:09:06
	 * @param user     包含用户账号和密码
	 * @param response 用于存储cookie，jwt令牌
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
	 * @param account 用户账号
	 * @param email   用户邮箱
	 * @return ResponseData 操作是否成功
	 */
	public ResponseData sendSecurityCode(User user);

	/**
	 * @Title reset
	 * @author :Stephen
	 * @Description 重置密码，将密码发送至用户邮箱
	 * @date 2018年12月22日 上午11:09:42
	 * @param user 包含用户账号和密码
	 * @return ResponseData 操作是否成功，data：user对象
	 */
	public ResponseData reset(User user);

	/**
	 * @Title reset
	 * @author :Stephen
	 * @Description 重置用户密码
	 * @date 2019年1月11日 下午5:00:06
	 * @param id 用户id
	 * @return ResponseData
	 */
	public ResponseData reset(String id);

	/**
	 * @Title resetSelf
	 * @author :Stephen
	 * @Description 用户首页重置密码
	 * @date 2018年12月22日 上午11:09:42
	 * @param user 包含用户账号和密码
	 * @return ResponseData 操作是否成功，data：user对象
	 */
	public ResponseData resetSelf(User user);

	/**
	 * @Title checkPwd
	 * @author :Stephen
	 * @Description 首页编辑密码，检验原密码是否正确
	 * @date 2018年12月22日 上午11:09:42
	 * @param user 包含用户账号和密码
	 * @return ResponseData 操作是否成功
	 */
	public ResponseData checkPwd(User user);

	/**
	 * @Title checkEmail
	 * @author :Stephen
	 * @Description 首页编辑邮箱，检验邮箱是否可用
	 * @date 2018年12月22日 上午11:09:42
	 * @param user 包含用户账号和邮箱
	 * @return ResponseData 操作是否成功
	 */
	public ResponseData checkEmail(User user);

	/**
	 * @Title checkOnly
	 * @author :Stephen
	 * @Description 检测新增或修改时，昵称，账号，邮箱唯一性
	 * @date 2019年1月11日 下午4:06:17
	 * @param name    昵称
	 * @param account 账号
	 * @param email   邮箱
	 * @param method  0:新增 1：修改
	 * @param id      修改时需要传入用户id
	 * @return ResponseData
	 */
	public ResponseData checkOnly(String name, String account, String email, Integer method, String id);

	/**
	 * @Title uploadIcon
	 * @author :Stephen
	 * @Description 用户头像上传
	 * @date 2019年1月24日 上午10:14:45
	 * @param user
	 * @return ResponseData
	 */
	public ResponseData uploadIcon(HttpServletRequest request, String account, String fileBathPath);

	/**
	 * @Title queryActiveIcon
	 * @author :Stephen
	 * @Description 获取当前用户的头像文件流
	 * @date 2019年1月24日 上午11:45:51
	 * @return byte[] 文件流
	 */
	public byte[] queryIcon(String account, String fileBathPath);

	/**
	 * @Title logout
	 * @author :Stephen
	 * @Description 退出，清楚cookid
	 * @date 2019年1月24日 下午3:31:34
	 * @return ResponseData
	 */
	public ResponseData logout(String account, HttpServletResponse res, HttpServletRequest req);

}
