/**
* @描述
* @文件名:DepartmentSpecification.java
* @版权:Copyright 2019 版权所有：平头哥
* @描述:DepartmentSpecification.java
* @修改人:stephen
* @修改时间:2019年1月2日 下午9:17:44
* @修改内容:新增
*/
package com.ratel.auth.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.ratel.auth.domain.Department;
import com.ratel.common.utils.StringUtil;

/**
 * @文件名:DepartmentSpecification.java
 * @版权:Copyright 2018 版权所有：平头哥
 * @描述:部门信息数据操作过滤层
 * @修改人:stephen
 * @修改时间:2019年1月2日 下午9:17:44
 * @修改内容:新增
 */
public class DepartmentSpecification implements Specification<Department> {

	private Department department;

	/**
	 *
	 * Creates a new instance of CatalogSpecification.
	 *
	 * @param compensation
	 */
	public DepartmentSpecification(Department department) {
		this.department = department;
	}

	/**
	 * @Title toPredicate
	 * @author :stephen
	 * @Description
	 * @date 2019年1月2日 下午9:18:23
	 * @param root
	 * @param query
	 * @param cb
	 * @return
	 */
	@Override
	public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<>();
		if (StringUtil.isNotBlank(department.getName())) {
			predicates.add(cb.like(root.get("name"), "%" + department.getName() + "%"));
		}
		if (StringUtil.isNotBlank(department.getCode())) {
			predicates.add(cb.like(root.get("code"), "%" + department.getCode().toLowerCase() + "%"));
		}
		if (StringUtil.isNotBlank(department.getId())) {
			predicates.add(cb.equal(root.get("id"), department.getId()));
		}
		if (StringUtil.isNotBlank(department.getpId())) {
			predicates.add(cb.equal(root.get("pId"), department.getpId()));
		}
		return this.andTogether(predicates, cb);
	}

	private Predicate andTogether(List<Predicate> predicates, CriteriaBuilder cb) {
		return cb.and(predicates.toArray(new Predicate[0]));
	}

}
