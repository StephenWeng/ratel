/**
* @描述
* @文件名:RoleController.java
* @版权:Copyright 2019 版权所有：平头哥
* @描述:RoleController.java
* @修改人:stephen
* @修改时间:2019年1月13日 上午12:48:04
* @修改内容:新增
*/
package com.ratel.auth.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ratel.auth.domain.Role;
import com.ratel.auth.service.IRoleService;
import com.ratel.common.response.ResponseData;
import com.ratel.common.web.BaseController;

/**
 * @文件名:RoleController.java
 * @版权:Copyright 2018 版权所有：平头哥
 * @描述:角色信息控制层
 * @修改人:stephen
 * @修改时间:2019年1月13日 上午12:48:04
 * @修改内容:新增
 */
@Controller
@EnableAutoConfiguration
@RestController
@RequestMapping(value = "roles")
public class RoleController extends BaseController {

	@Autowired
	private IRoleService roleService;

	/**
	 * @Title queryDepartmentPage
	 * @author :stephen
	 * @Description
	 * @date 2019年1月13日 下午7:17:56
	 * @param currentPage
	 *            每页个数
	 * @param pagesize
	 *            当前页
	 * @param name
	 *            名称
	 * @return ResponseData
	 */
	@ResponseBody
	@RequestMapping(value = "/roles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseData queryDepartmentPage(@RequestParam("currentPage") Integer currentPage,
			@RequestParam("pagesize") Integer pagesize, @RequestParam("name") String name) {
		return roleService.queryRolePage(currentPage - 1, pagesize, name);
	}

	/**
	 * @Title addRole
	 * @author :stephen
	 * @Description 新增角色
	 * @date 2019年1月13日 下午7:17:56
	 * @param role
	 *            角色对象
	 * @return ResponseData
	 */
	@ResponseBody
	@RequestMapping(value = "/roles", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseData addRole(@RequestBody Role role) {
		return roleService.addRole(role);
	}

	/**
	 * @Title updateRole
	 * @author :stephen
	 * @Description 编辑角色
	 * @date 2019年1月13日 下午7:17:56
	 * @param role
	 *            角色对象
	 * @return ResponseData
	 */
	@ResponseBody
	@RequestMapping(value = "/roles", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseData updateRole(@RequestBody Role role) {
		return roleService.updateRole(role);
	}

	/**
	 * @Title delRole
	 * @author :stephen
	 * @Description 删除角色
	 * @date 2019年1月13日 下午7:17:56
	 * @param ids
	 *            角色id集合
	 * @return ResponseData
	 */
	@ResponseBody
	@RequestMapping(value = "/roles", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseData delRole(@RequestBody List<String> ids) {
		return roleService.delRole(ids);
	}

	/**
	 * @Title checkOnly
	 * @author :stephen
	 * @Description 检测新增或修改时，角色名唯一性
	 * @date 2019年1月13日 下午8:01:37
	 * @param name
	 *            角色名
	 * @param method
	 *            0:新增 1：修改
	 * @param id
	 *            修改时需要传入角色id
	 * @return ResponseData
	 */
	@ResponseBody
	@RequestMapping(value = "/check", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseData checkOnly(@RequestParam("name") String name, @RequestParam("method") Integer method,
			@RequestParam("id") String id) {
		return roleService.checkOnly(name, method, id);
	}

}
