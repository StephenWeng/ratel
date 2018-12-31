/**
* @描述
* @文件名:ResourceSpecification.java
* @版权:Copyright 2018 版权所有：大庆金桥信息技术工程有限公司成都分公司
* @描述:ResourceSpecification.java
* @修改人:技术部-文章
* @修改时间:2018年12月31日 下午1:59:31
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

import com.ratel.auth.domain.Resource;
import com.ratel.common.utils.StringUtil;

/**
 * @文件名:ResourceSpecification.java
 * @版权:Copyright 2018 版权所有：大庆金桥信息技术工程有限公司成都分公司
 * @描述:资源信息数据库操作字段过滤类
 * @修改人:技术部-文章
 * @修改时间:2018年12月31日 下午1:59:31
 * @修改内容:新增
 */
public class ResourceSpecification implements Specification<Resource> {

	private Resource resource;

	/**
	 *
	 * Creates a new instance of CatalogSpecification.
	 *
	 * @param compensation
	 */
	public ResourceSpecification(Resource resource) {
		this.resource = resource;
	}

	/**
	 * @Title toPredicate
	 * @author :技术部-文章
	 * @Description
	 * @date 2018年12月31日 下午2:00:35
	 * @param root
	 * @param query
	 * @param cb
	 * @return
	 */
	@Override
	public Predicate toPredicate(Root<Resource> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<>();
		if (StringUtil.isNotBlank(resource.getName())) {
			predicates.add(cb.like(cb.lower(root.get("name")), "%" + resource.getName().toLowerCase() + "%"));
		}
		if (StringUtil.isNotBlank(resource.getId())) {
			predicates.add(cb.equal(root.get("id"), resource.getId()));
		}
		return this.andTogether(predicates, cb);
	}

	private Predicate andTogether(List<Predicate> predicates, CriteriaBuilder cb) {
		return cb.and(predicates.toArray(new Predicate[0]));
	}

}
