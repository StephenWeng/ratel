package com.ratel.auth.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping(value = "calendar")
public class CalendarController extends BaseController {

	@Autowired
	private ICalendarService calendarService;

	@ResponseBody
	@RequestMapping(value = "/events", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseData queryUserPage(@RequestParam("date") String date) {
		return calendarService.queryEvents(date);
	}

}
