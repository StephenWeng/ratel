package com.ratel.auth.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ratel.auth.domain.User;
import com.ratel.auth.service.IUserService;
import com.ratel.common.response.ResponseData;
import com.ratel.common.web.BaseController;

/**
 * @文件名:UserController.java
 * @版权:Copyright 2018 版权所有：平头哥
 * @描述:用户信息控制层
 * @修改人:Stephen
 * @修改时间:2018年12月13日 下午4:39:22
 * @修改内容:新增
 */
@Controller
@EnableAutoConfiguration
@RestController
@RequestMapping(value = "users")
public class UserController extends BaseController {

	@Autowired
	private IUserService userService;

	/**
	 * @Title queryUserTree
	 * @author :Stephen
	 * @Description 查询用户树，没有顶层组织
	 * @date 2019年1月11日 上午10:29:04
	 * @return ResponseData
	 */
	@ResponseBody
	@RequestMapping(value = "/tree", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseData queryUserTree() {
		return userService.queryUserTree();
	}

	/**
	 * 
	 * @Title queryUserPage
	 * @author :Stephen
	 * @Description 分页查询用户信息
	 * @date 2019年1月11日 上午10:12:15
	 * @param currentPage  当前页
	 * @param pagesize     每页个数
	 * @param departmentId 部门id
	 * @param userDo       可能包含 昵称，账号，邮箱
	 * @return ResponseData
	 */
	@ResponseBody
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseData queryUserPage(@RequestParam("currentPage") Integer currentPage,
			@RequestParam("pagesize") Integer pagesize, @ModelAttribute User userDo) {
		return userService.queryUserPage(currentPage - 1, pagesize, userDo);
	}

	/**
	 * @Title addUser
	 * @author :Stephen
	 * @Description 新增用户，密码通过邮箱发送
	 * @date 2019年1月11日 下午2:34:11
	 * @param user 用户对象
	 * @return ResponseData
	 */
	@ResponseBody
	@RequestMapping(value = "/users", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseData addUser(@RequestBody User user) {
		return userService.addUser(user);
	}

	/**
	 * @Title updateUser
	 * @author :Stephen
	 * @Description 更新用户信息
	 * @date 2019年1月11日 下午2:41:43
	 * @param user 用户对象
	 * @return ResponseData
	 */
	@ResponseBody
	@RequestMapping(value = "/users", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseData updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}

	/**
	 * @Title delUsers
	 * @author :Stephen
	 * @Description 删除用户
	 * @date 2019年1月11日 下午2:45:31
	 * @param ids 用户id集合
	 * @return ResponseData
	 */
	@ResponseBody
	@RequestMapping(value = "/users", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseData delUsers(@RequestBody List<String> ids) {
		return userService.delUsers(ids);
	}

	/**
	 *
	 * @Title queryActiveUser
	 * @author :Stephen
	 * @Description 查询当前用户信息
	 * @date 2018年12月22日 上午11:08:23
	 * @return ResponseData 操作是否成功，data：user对象
	 */
	@ResponseBody
	@RequestMapping(value = "/activeUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseData queryActiveUser() {
		return userService.queryUser(this.getUserAccount());
	}

	/*************************** 以下为登陆和找回密码接口 *********************************/

	/**
	 *
	 * @Title login
	 * @author :Stephen
	 * @Description 用户登录
	 * @date 2018年12月22日 上午11:09:06
	 * @param user 包含用户账号和密码
	 * @return ResponseData 操作是否成功，data：user对象
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseData login(@RequestBody User user) {
		return userService.login(user, this.response());
	}

	/**
	 * @Title editInformation
	 * @author :Stephen
	 * @Description 用户首页修改葛粉信息
	 * @date 2018年12月22日 上午11:09:42
	 * @param user
	 * @return ResponseData 操作是否成功，data：user对象
	 */
	@ResponseBody
	@RequestMapping(value = "/information", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseData editInformation(@RequestBody User user) {
		return userService.editInformation(user);
	}

	/**
	 * @Title sendSecurityCode
	 * @author :技术部-文章
	 * @Description 向用户邮箱发送重置密码的验证码
	 * @date 2018年12月23日 下午7:18:55
	 * @param account 用户账号
	 * @return ResponseData 操作是否成功
	 */
	@ResponseBody
	@RequestMapping(value = "/securityCode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseData sendSecurityCode(@RequestBody User user) {
		return userService.sendSecurityCode(user);
	}

	/**
	 * @Title reset
	 * @author :Stephen
	 * @Description 重置密码，将密码发送至用户邮箱
	 * @date 2018年12月22日 上午11:09:42
	 * @param user 包含用户账号和密码
	 * @return ResponseData 操作是否成功，data：user对象
	 */
	@ResponseBody
	@RequestMapping(value = "/reset", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseData reset(@RequestBody User user) {
		return userService.reset(user);
	}

	/**
	 * @Title reset
	 * @author :Stephen
	 * @Description 重置用户密码
	 * @date 2019年1月11日 下午5:00:06
	 * @param id 用户id
	 * @return ResponseData
	 */
	@ResponseBody
	@RequestMapping(value = "/reset", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseData reset(@RequestParam("id") String id) {
		return userService.reset(id);
	}

	/**
	 * @Title resetSelf
	 * @author :Stephen
	 * @Description 用户首页重置密码
	 * @date 2018年12月22日 上午11:09:42
	 * @param user 包含用户账号和密码
	 * @return ResponseData 操作是否成功，data：user对象
	 */
	@ResponseBody
	@RequestMapping(value = "/resetSelf", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseData resetSelf(@RequestBody User user) {
		user.setAccount(this.getUserAccount());
		return userService.resetSelf(user);
	}

	/**
	 * @Title checkPwd
	 * @author :Stephen
	 * @Description 首页编辑密码，检验原密码是否正确
	 * @date 2018年12月22日 上午11:09:42
	 * @param user 包含用户账号和密码
	 * @return ResponseData 操作是否成功
	 */
	@ResponseBody
	@RequestMapping(value = "/pwd", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseData checkPwd(@RequestBody User user) {
		user.setAccount(this.getUserAccount());
		return userService.checkPwd(user);
	}

	/**
	 * @Title checkEmail
	 * @author :Stephen
	 * @Description 首页编辑邮箱，检验邮箱是否可用
	 * @date 2018年12月22日 上午11:09:42
	 * @param user 包含用户账号和邮箱
	 * @return ResponseData 操作是否成功
	 */
	@ResponseBody
	@RequestMapping(value = "/email", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseData checkEmail(@RequestBody User user) {
		return userService.checkEmail(user);
	}

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
	@ResponseBody
	@RequestMapping(value = "/check", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseData checkOnly(@RequestParam("name") String name, @RequestParam("account") String account,
			@RequestParam("email") String email, @RequestParam("method") Integer method,
			@RequestParam("id") String id) {
		return userService.checkOnly(name, account, email, method, id);
	}

}
