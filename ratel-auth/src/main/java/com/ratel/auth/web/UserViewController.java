package com.ratel.auth.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ratel.common.web.BaseController;

/**
 * @文件名:FinaceViewController.java
 * @版权:Copyright 2018 版权所有：平头哥
 * @描述:财务项目页面跳转控制类
 * @修改人:Stephen
 * @修改时间:2018年12月13日 下午4:46:57
 * @修改内容:新增
 */
@Controller
@EnableAutoConfiguration
@RestController
@RequestMapping(value = "users")
public class UserViewController extends BaseController {

	/**
	 *
	 * @Title toUserReset
	 * @author :技术部-文章
	 * @Description 跳转用户重置密码页面
	 * @date 2018年12月16日 下午3:58:22
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/reset", method = RequestMethod.GET, name = "重置密码页面")
	public ModelAndView toUserReset() {
		ModelAndView mav = new ModelAndView("user/reset");
		mav.addObject("appName", appName);
		return mav;
	}

	/**
	 * @Title toUserIndex
	 * @author :Stephen
	 * @Description 跳转用户信息管理页面
	 * @date 2018年12月13日 下午4:56:49
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET, name = "用户信息管理页面")
	public ModelAndView toUserIndex() {
		ModelAndView mav = new ModelAndView("user/index");
		mav.addObject("appName", appName);
		return mav;
	}

}
