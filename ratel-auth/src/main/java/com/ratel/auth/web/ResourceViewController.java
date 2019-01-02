/**  
* @描述 
* @文件名:ResourceViewController.java
* @版权:Copyright 2018 版权所有：平头哥
* @描述:ResourceViewController.java
* @修改人:Stephen
* @修改时间:2019年1月2日 上午10:46:38
* @修改内容:新增
*/
package com.ratel.auth.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ratel.common.web.BaseController;

/**
 * @文件名:ResourceViewController.java
 * @版权:Copyright 2018 版权所有：平头哥
 * @描述:资源管理跳转页面controller
 * @修改人:Stephen
 * @修改时间:2019年1月2日 上午10:46:38
 * @修改内容:新增
 */
@Controller
@EnableAutoConfiguration
@RestController
@RequestMapping(value = "resources")
public class ResourceViewController extends BaseController {

	/**
	 * @Title toIndex
	 * @author :Stephen
	 * @Description 跳转资源管理页面
	 * @date 2019年1月2日 上午10:47:56
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET, name = "资源管理页面")
	public ModelAndView toIndex() {
		ModelAndView mav = new ModelAndView("resources/index");
		mav.addObject("appName", appName);
		return mav;
	}

}
