package com.ratel.web;

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
@RequestMapping(value = "ratel")
public class ProtalViewController extends BaseController {

	/**
	 *
	 * @Title toLogin
	 * @author :Stephen
	 * @Description 跳转登陆页面
	 * @date 2018年12月13日 下午4:56:35
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET, name = "登录页")
	public ModelAndView toLogin() {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("appName", appName);
		return mav;
	}

	/**
	 *
	 * @Title toLogin
	 * @author :Stephen
	 * @Description 跳转登陆页面
	 * @date 2018年12月13日 下午4:56:35
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/protal", method = RequestMethod.GET, name = "主页面")
	public ModelAndView toProtal() {
		ModelAndView mav = new ModelAndView("protal");
		mav.addObject("appName", appName);
		return mav;
	}
}
