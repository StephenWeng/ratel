/**
* @描述
* @文件名:RoleSpecification.java
* @版权:Copyright 2019 版权所有：平头哥
* @描述:RoleSpecification.java
* @修改人:stephen
* @修改时间:2019年1月13日 上午12:43:49
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

import com.ratel.auth.domain.Role;
import com.ratel.common.utils.StringUtil;

/**
 * @文件名:RoleSpecification.java
 * @版权:Copyright 2018 版权所有：平头哥
 * @描述:角色信息数据库操作字段过滤类
 * @修改人:stephen
 * @修改时间:2019年1月13日 上午12:43:49
 * @修改内容:新增
 */
public class RoleSpecification implements Specification<Role> {

	private Role role;

	/**
	 *
	 * Creates a new instance of CatalogSpecification.
	 *
	 * @param compensation
	 */
	public RoleSpecification(Role role) {
		this.role = role;
	}

	/**
	 * @Title toPredicate
	 * @author :stephen
	 * @Description
	 * @date 2019年1月13日 上午12:44:25
	 * @param root
	 * @param query
	 * @param cb
	 * @return
	 */
	@Override
	public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<>();
		if (StringUtil.isNotBlank(role.getName())) {
			predicates.add(cb.like(cb.lower(root.get("name")), "%" + role.getName().toLowerCase() + "%"));
		}
		if (StringUtil.isNotBlank(role.getId())) {
			predicates.add(cb.equal(root.get("id"), role.getId()));
		}
		return this.andTogether(predicates, cb);
	}

	private Predicate andTogether(List<Predicate> predicates, CriteriaBuilder cb) {
		return cb.and(predicates.toArray(new Predicate[0]));
	}

}
