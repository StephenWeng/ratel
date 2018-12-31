/**
* @描述
* @文件名:Resource.java
* @版权:Copyright 2018 版权所有：大庆金桥信息技术工程有限公司成都分公司
* @描述:Resource.java
* @修改人:技术部-文章
* @修改时间:2018年12月31日 下午1:52:44
* @修改内容:新增
*/
package com.ratel.auth.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @文件名:Resource.java
 * @版权:Copyright 2018 版权所有：大庆金桥信息技术工程有限公司成都分公司
 * @描述:资源实体表
 * @修改人:技术部-文章
 * @修改时间:2018年12月31日 下午1:52:44
 * @修改内容:新增
 */
@Entity
@Table(name = "RESOURCES")
@ApiModel(value = "Resource", description = "资源实体类")
public class Resource {

	@ApiModelProperty(value = "主键ID")
	private String id;

	@NotNull(message = "资源名称不能为空")
	@ApiModelProperty(value = "资源名称")
	private String name;

	@ApiModelProperty(value = "资源链接地址")
	private String url;

	@ApiModelProperty(value = "上级资源id")
	private String pId;

	@ApiModelProperty(value = "排序字段")
	private int sortCol;//

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

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "URL")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "PID")
	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	@Column(name = "SORTCOL")
	public int getSortCol() {
		return sortCol;
	}

	public void setSortCol(int sortCol) {
		this.sortCol = sortCol;
	}

}
