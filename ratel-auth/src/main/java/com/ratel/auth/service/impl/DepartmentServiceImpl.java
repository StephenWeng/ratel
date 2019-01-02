/**
* @描述
* @文件名:DepartmentServiceImpl.java
* @版权:Copyright 2019 版权所有：平头哥
* @描述:DepartmentServiceImpl.java
* @修改人:stephen
* @修改时间:2019年1月2日 下午9:19:56
* @修改内容:新增
*/
package com.ratel.auth.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ratel.auth.domain.Department;
import com.ratel.auth.repository.DepartmentRepository;
import com.ratel.auth.repository.DepartmentSpecification;
import com.ratel.auth.service.IDepartmentService;
import com.ratel.common.domain.TreeVo;
import com.ratel.common.response.ResponseData;
import com.ratel.common.response.ResponseMsg;
import com.ratel.common.utils.DateUtils;
import com.ratel.common.utils.StringUtil;

/**
 * @文件名:DepartmentServiceImpl.java
 * @版权:Copyright 2018 版权所有：平头哥
 * @描述:部门相关业务逻辑处理层实现类
 * @修改人:stephen
 * @修改时间:2019年1月2日 下午9:19:56
 * @修改内容:新增
 */
@Service
public class DepartmentServiceImpl implements IDepartmentService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final static String UNDEFINED = "未知";

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public ResponseData queryDepartmentTree() {
		try {
			List<Department> list = this.queryDepartments(null);
			if (null == list || list.size() == 0) {
				return new ResponseData(ResponseMsg.FAILED.getCode(), "查询无数据");
			}
			List<TreeVo> res = new ArrayList<TreeVo>();
			Department topDepartment = departmentRepository.queryTopDepartment().get(0);
			TreeVo vo = new TreeVo();
			vo.setId(topDepartment.getId());
			vo.setLabel(topDepartment.getName());
			// 递归处理为树形结构
			vo.setChildren(recursionDepartments(topDepartment.getId(), list));
			res.add(vo);
			return new ResponseData(ResponseMsg.SUCCESS, res);
		} catch (Exception e) {
			logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "查询部门组织机构树错误：" + e.getMessage());
			return new ResponseData(ResponseMsg.FAILED.getCode(), "系统异常!");
		}
	}

	/**
	 * @Title recursionDepartments
	 * @author :stephen
	 * @Description 递归将部门数据处理为树形结构
	 * @date 2019年1月2日 下午9:41:06
	 * @param pId  上级部门id
	 * @param list 部门数据集合
	 * @return List<TreeVo>
	 */
	private List<TreeVo> recursionDepartments(String pId, List<Department> list) {
		if (null != list && list.size() > 0) {
			List<TreeVo> res = new ArrayList<TreeVo>();
			for (Department department : list) {
				if (pId.equals(department.getpId())) {
					TreeVo vo = new TreeVo();
					vo.setId(department.getId());
					vo.setLabel(department.getName());
					vo.setChildren(recursionDepartments(department.getId(), list));
					res.add(vo);
				}
			}
			return res;
		}
		return null;
	}

	/**
	 * @Title queryDepartments
	 * @author :stephen
	 * @Description 根据部门id查询下级单位，当传入参数为空时，查询全部
	 * @date 2019年1月2日 下午9:32:44
	 * @param departmentId 部门id
	 * @return List<Department>
	 */
	private List<Department> queryDepartments(String departmentId) {
		List<Department> list = null;
		Department department = new Department();
		if (!StringUtil.isEmpty(departmentId)) {
			department.setpId(departmentId);
		}
		DepartmentSpecification sd = new DepartmentSpecification(department);
		list = departmentRepository.findAll(sd);
		return list;

	}

	/**
	 * @Title queryDepartmentPage
	 * @author :Stephen
	 * @Description
	 * @date 2019年1月3日 上午11:25:45
	 * @param currentPage
	 * @param pagesize
	 * @param pId
	 * @return
	 */
	@Override
	public ResponseData queryDepartmentPage(Integer currentPage, Integer pagesize, String pId, String name,
			String code) {
		try {
			Pageable pageAble = new PageRequest(currentPage, pagesize, Direction.DESC, "createTime");
			Department department = new Department();
			// 当传入的pId为空时，传入顶层机构的下级
			department.setpId(pId);
			department.setName(name);
			department.setCode(code);
			DepartmentSpecification pcs = new DepartmentSpecification(department);
			Page<Department> page = departmentRepository.findAll(pcs, pageAble);
			return new ResponseData(ResponseMsg.SUCCESS, page);
		} catch (Exception e) {
			logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "查询部门组织列表错误：" + e.getMessage());
			return new ResponseData(ResponseMsg.FAILED.getCode(), "系统异常");
		}

	}

}
