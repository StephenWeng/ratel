package com.ratel.auth.web;

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
import com.ratel.common.response.ResponseMsg;
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

	@ResponseBody
	@RequestMapping(value = "/a", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseData find(@ModelAttribute User user) {
		return new ResponseData(ResponseMsg.SUCCESS, user.getName());
	}

	@ResponseBody
	@RequestMapping(value = "/page", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseData page(@RequestParam("current") Integer current, @RequestParam("pageSize") Integer pageSize) {
		return userService.page(current, pageSize);
	}

	/**
	 *
	 * @Title queryUser
	 * @author :Stephen
	 * @Description 根据用户账号查询用户,不模糊
	 * @date 2018年12月22日 上午11:08:23
	 * @param account 用户账号
	 * @return ResponseData 操作是否成功，data：user对象
	 */
	@ResponseBody
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseData queryUser(@RequestParam("account") String account) {
		return userService.queryUser(account);
	}

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
	 * @Title sendSecurityCode
	 * @author :技术部-文章
	 * @Description 向用户邮箱发送重置密码的验证码
	 * @date 2018年12月23日 下午7:18:55
	 * @param account 用户账号
	 * @return ResponseData 操作是否成功
	 */
	@ResponseBody
	@RequestMapping(value = "/securityCode", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseData sendSecurityCode(@RequestParam("account") String account) {
		return userService.sendSecurityCode(account);
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

}
