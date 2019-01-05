package com.ratel.auth.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @className Department
 * @author :stephen
 * @Description 部门实体类
 * @date 2019年1月2日 下午9:03:47
 */
@Entity
@Table(name = "DEPARTMENTS")
@ApiModel(value = "Departments", description = "部门信息")
public class Department {

	@ApiModelProperty(value = "主键ID")
	private String id;// 主键

	@NotNull(message = "部门名称不能为空")
	@ApiModelProperty(value = "部门名称")
	private String name;// 名称

	@ApiModelProperty(value = "上级部门id")
	private String pId;// 上级部门id

	@ApiModelProperty(value = "编码或公司机构代码")
	private String code;// 编码或公司机构代码

	@ApiModelProperty(value = "地址")
	private String address;// 地址

	@ApiModelProperty(value = "法人代表、部门负责人")
	private String lealPerson;// 法人代表、部门负责人

	@ApiModelProperty(value = "负责人姓名")
	private String lealPersonName;// 临时属性

	@ApiModelProperty(value = "座机")
	private String plane;// 座机

	@ApiModelProperty(value = "电话")
	private String telphone;// 电话

	@ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;// 创建时间

	@ApiModelProperty(value = "更新时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;// 更新时间

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

	@Column(name = "PID")
	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	@Column(name = "CODE")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "LEAL_PERSON")
	public String getLealPerson() {
		return lealPerson;
	}

	public void setLealPerson(String lealPerson) {
		this.lealPerson = lealPerson;
	}

	@Column(name = "PLANE")
	public String getPlane() {
		return plane;
	}

	public void setPlane(String plane) {
		this.plane = plane;
	}

	@Column(name = "TELPHONE")
	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	@Column(name = "CREATE_TIME")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "UPDATE_TIME")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Transient
	public String getLealPersonName() {
		return lealPersonName;
	}

	public void setLealPersonName(String lealPersonName) {
		this.lealPersonName = lealPersonName;
	}

}
