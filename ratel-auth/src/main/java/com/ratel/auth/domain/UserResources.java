/**
* @描述
* @文件名:UserResources.java
* @版权:Copyright 2018 版权所有：大庆金桥信息技术工程有限公司成都分公司
* @描述:UserResources.java
* @修改人:技术部-文章
* @修改时间:2018年12月31日 下午2:40:00
* @修改内容:新增
*/
package com.ratel.auth.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import io.swagger.annotations.ApiModel;

/**
 * @文件名:UserResources.java
 * @版权:Copyright 2018 版权所有：大庆金桥信息技术工程有限公司成都分公司
 * @描述:用户和资源关联表
 * @修改人:技术部-文章
 * @修改时间:2018年12月31日 下午2:40:00
 * @修改内容:新增
 */
@Entity
@Table(name = "USER_RESOURCES")
@ApiModel(value = "UserResources", description = "用户和资源关联类")
public class UserResources {

	private String id;// 主键

	private String userId;// 用户主键

	private String resourceId;// 资源主键

	public UserResources() {
		super();
	}

	public UserResources(String id, String userId, String resourceId) {
		super();
		this.id = id;
		this.userId = userId;
		this.resourceId = resourceId;
	}

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "ID", unique = true, nullable = false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "USER_ID")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "RESOURCES_ID")
	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

}
