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

}
