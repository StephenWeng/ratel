/**
* @描述
* @文件名:IDepartmentService.java
* @版权:Copyright 2019 版权所有：平头哥
* @描述:IDepartmentService.java
* @修改人:stephen
* @修改时间:2019年1月2日 下午9:19:30
* @修改内容:新增
*/
package com.ratel.auth.service;

import java.util.List;

import com.ratel.auth.domain.Department;
import com.ratel.common.response.ResponseData;

/**
 * @文件名:IDepartmentService.java
 * @版权:Copyright 2018 版权所有：平头哥
 * @描述:部门业务逻辑处理层接口
 * @修改人:stephen
 * @修改时间:2019年1月2日 下午9:19:30
 * @修改内容:新增
 */
public interface IDepartmentService {

	/**
	 * @Title addDepartment
	 * @author :stephen
	 * @Description 新增部门
	 * @date 2019年1月5日 下午8:55:12
	 * @param department 部门对象
	 * @return ResponseData
	 */
	public ResponseData addDepartment(Department department);

	/**
	 * @Title updateDepartment
	 * @author :stephen
	 * @Description 编辑部门
	 * @date 2019年1月5日 下午8:56:41
	 * @param department 部门对象
	 * @return ResponseData
	 */
	public ResponseData updateDepartment(Department department);

	/**
	 * @Title delDepartment
	 * @author :stephen
	 * @Description 删除部门
	 * @date 2019年1月5日 下午8:57:11
	 * @param ids 部门id集合
	 * @return ResponseData
	 */
	public ResponseData delDepartment(List<String> ids);

	/**
	 *
	 * @Title queryDepartmentTree
	 * @author :stephen
	 * @Description 查询部门机构树形数据
	 * @date 2019年1月2日 下午9:27:50
	 * @return ResponseData
	 */
	public ResponseData queryDepartmentTree();

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
	public ResponseData queryDepartmentPage(Integer currentPage, Integer pagesize, String pId, String name,
			String code);

	/**
	 *
	 * @Title checkNameOrCode
	 * @author :stephen
	 * @Description 新增或修改时检测名称或代码在本级的唯一性
	 * @date 2019年1月5日 下午10:30:00
	 * @param pId    上级单位id
	 * @param name   名称
	 * @param code   代码
	 * @param method 0:新增 1：修改
	 * @return ResponseData
	 */
	public ResponseData checkNameOrCode(String pId, String name, String code, Integer method, String id);

	/**
	 * 
	 * @Title queryDepartments
	 * @author :Stephen
	 * @Description 根据部门id查询下级单位，当传入参数为空时，查询全部
	 * @date 2019年1月11日 上午10:32:04
	 * @param departmentId 部门id
	 * @return List<Department>
	 */
	public List<Department> queryDepartments(String departmentId);

}
