/**
* @描述
* @文件名:RoleVo.java
* @版权:Copyright 2019 版权所有：平头哥
* @描述:RoleVo.java
* @修改人:stephen
* @修改时间:2019年1月13日 下午8:43:46
* @修改内容:新增
*/
package com.ratel.auth.vo;

import java.util.Date;
import java.util.List;

import com.ratel.auth.domain.Resource;

/**
 * @文件名:RoleVo.java
 * @版权:Copyright 2018 版权所有：平头哥
 * @描述:返回前端角色对象
 * @修改人:stephen
 * @修改时间:2019年1月13日 下午8:43:46
 * @修改内容:新增
 */
public class RoleVo {

	private String id;// 主键

	private String name;// 角色名

	private String resourceIds;// 资源id，多个以,相连

	private Date createTime;// 创建时间

	private Date updateTime;// 修改时间

	private List<Resource> resources;// 资源对象集合

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

}
