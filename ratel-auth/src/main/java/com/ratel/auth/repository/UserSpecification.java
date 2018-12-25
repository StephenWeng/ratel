package com.ratel.auth.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.ratel.auth.domain.User;
import com.ratel.common.utils.StringUtil;

/**
 * @文件名:UserSpecification.java
 * @版权:Copyright 2018 版权所有：平头哥
 * @描述:用户信息数据库操作字段过滤类
 * @修改人:Stephen
 * @修改时间:2018年12月13日 下午4:32:06
 * @修改内容:新增
 */
public class UserSpecification implements Specification<User> {

	private User user;

	/**
	 *
	 * Creates a new instance of CatalogSpecification.
	 *
	 * @param compensation
	 */
	public UserSpecification(User user) {
		this.user = user;
	}

	/**
	 * @Title toPredicate
	 * @author :Stephen
	 * @Description
	 * @date 2018年12月13日 下午4:32:49
	 * @param root
	 * @param query
	 * @param cb
	 * @return
	 */
	@Override
	public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<>();
		if (StringUtil.isNotBlank(user.getName())) {
			predicates.add(cb.like(cb.lower(root.get("name")), "%" + user.getName().toLowerCase() + "%"));
		}
		if (StringUtil.isNotBlank(user.getId())) {
			predicates.add(cb.equal(root.get("id"), user.getId()));
		}
		return this.andTogether(predicates, cb);
	}

	private Predicate andTogether(List<Predicate> predicates, CriteriaBuilder cb) {
		return cb.and(predicates.toArray(new Predicate[0]));
	}

}
