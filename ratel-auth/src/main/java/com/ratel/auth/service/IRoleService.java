/**
* @描述
* @文件名:IRoleService.java
* @版权:Copyright 2019 版权所有：平头哥
* @描述:IRoleService.java
* @修改人:stephen
* @修改时间:2019年1月13日 上午12:46:26
* @修改内容:新增
*/
package com.ratel.auth.service;

import java.util.List;

import com.ratel.auth.domain.Role;
import com.ratel.common.response.ResponseData;

/**
 * @文件名:IRoleService.java
 * @版权:Copyright 2018 版权所有：平头哥
 * @描述:角色业务逻辑层接口
 * @修改人:stephen
 * @修改时间:2019年1月13日 上午12:46:26
 * @修改内容:新增
 */
public interface IRoleService {

	/**
	 * @Title queryDepartmentPage
	 * @author :stephen
	 * @Description
	 * @date 2019年1月13日 下午7:17:56
	 * @param currentPage 每页个数
	 * @param pagesize    当前页
	 * @param name        名称
	 * @return ResponseData
	 */
	public ResponseData queryRolePage(Integer currentPage, Integer pagesize, String name);

	/**
	 * @Title queryAllRoles
	 * @author :stephen
	 * @Description 查询全部角色
	 * @date 2019年1月18日 下午3:39:56
	 * @return ResponseData
	 */
	public ResponseData queryAllRoles();

	/**
	 * @Title queryActiveRoles
	 * @author :stephen
	 * @Description 查询当前登录用户拥有的角色
	 * @date 2019年1月18日 下午3:39:56
	 * @return ResponseData
	 */
	public ResponseData queryRolesByAccount(String account);

	/**
	 * @Title addRole
	 * @author :stephen
	 * @Description 新增角色
	 * @date 2019年1月13日 下午7:17:56
	 * @param role 角色对象
	 * @return ResponseData
	 */
	public ResponseData addRole(Role role);

	/**
	 * @Title updateRole
	 * @author :stephen
	 * @Description 编辑角色
	 * @date 2019年1月13日 下午7:17:56
	 * @param role 角色对象
	 * @return ResponseData
	 */
	public ResponseData updateRole(Role role);

	/**
	 * @Title delRole
	 * @author :stephen
	 * @Description 删除角色
	 * @date 2019年1月13日 下午7:17:56
	 * @param ids 角色id集合
	 * @return ResponseData
	 */
	public ResponseData delRole(List<String> ids);

	/**
	 * @Title checkOnly
	 * @author :stephen
	 * @Description 检测新增或修改时，角色名唯一性
	 * @date 2019年1月13日 下午8:01:37
	 * @param name   角色名
	 * @param method 0:新增 1：修改
	 * @param id     修改时需要传入角色id
	 * @return ResponseData
	 */
	public ResponseData checkOnly(String name, Integer method, String id);

}
