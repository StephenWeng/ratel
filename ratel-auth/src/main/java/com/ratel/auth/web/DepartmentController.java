package com.ratel.auth.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ratel.auth.service.IDepartmentService;
import com.ratel.common.response.ResponseData;
import com.ratel.common.web.BaseController;

/**
 * @描述
 * @文件名:DepartmentController.java
 * @版权:Copyright 2018 版权所有：平头哥
 * @描述:部门信息控制层
 * @修改人:stephen
 * @修改时间:2019年1月2日 下午9:21:04
 * @修改内容:新增
 */
@Controller
@EnableAutoConfiguration
@RestController
@RequestMapping(value = "departments")
public class DepartmentController extends BaseController {

	@Autowired
	private IDepartmentService departmentService;

	/**
	 *
	 * @Title queryDepartmentTree
	 * @author :stephen
	 * @Description 查询部门机构树形数据
	 * @date 2019年1月2日 下午9:27:04
	 * @return ResponseData
	 */
	@ResponseBody
	@RequestMapping(value = "/tree", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseData queryDepartmentTree() {
		return departmentService.queryDepartmentTree();
	}

	/**
	 * @Title queryDepartmentPage
	 * @author :Stephen
	 * @Description 分页查询部门数据
	 * @date 2019年1月3日 上午11:06:18
	 * @param currentPage 当前页面
	 * @param pagesize    当前页个数
	 * @param pId         上级部门id
	 * @param name        部门名称
	 * @param code        部门编码
	 * @return ResponseData
	 */
	@ResponseBody
	@RequestMapping(value = "/departments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseData queryDepartmentPage(@RequestParam("currentPage") Integer currentPage,
			@RequestParam("pagesize") Integer pagesize, @RequestParam("pId") String pId,
			@RequestParam("name") String name, @RequestParam("code") String code) {
		return departmentService.queryDepartmentPage(currentPage - 1, pagesize, pId, name, code);
	}

}
