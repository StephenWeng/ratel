package com.ratel.auth.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @文件名:User.java
 * @版权:Copyright 2018 版权所有：平头哥
 * @描述:用户信息实体类
 * @修改人:Stephen
 * @修改时间:2018年12月13日 下午4:14:22
 * @修改内容:新增
 */
@Entity
@Table(name = "USER")
@ApiModel(value = "User", description = "人员信息")
public class User {

	@ApiModelProperty(value = "主键ID")
	private String id;

	@NotNull(message = "姓名不能为空")
	@ApiModelProperty(value = "用户名")
	private String name;

	@NotEmpty(message = "密码不能为空")
	@ApiModelProperty(value = "密码")
	private String password;

	@ApiModelProperty(value = "部门id")
	private String departmentId;

	@Value("0")
	@ApiModelProperty(value = "性别标识")
	private Integer gender;// 0:男 1：女

	@ApiModelProperty(value = "年龄")
	private Integer age;

	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	@ApiModelProperty(value = "修改时间")
	private Date updateTime;

	@NotEmpty(message = "账号不能为空")
	@ApiModelProperty(value = "账号")
	private String account;

	@ApiModelProperty(value = "邮箱")
	private String email;

	@ApiModelProperty(value = "电话号码")
	private String telphone;

	@ApiModelProperty(value = "地址")
	private String address;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(value = "生日日期")
	private Date birthDay;

	@Value("0")
	@ApiModelProperty(value = "是否逻辑删除 0：未删除 1：已删除")
	private Integer isDeleted;

	@ApiModelProperty(value = "重置密码验证码")
	private String securityCode;

	@ApiModelProperty(value = "重置密码验证码产生时间，有效期3个小时")
	private Date securityCodeCreateTime;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "ID", unique = true, nullable = false)
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            要设置的 id
	 */
	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            要设置的 name
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            要设置的 password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "DEPARTMENT_ID")
	public String getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId
	 *            要设置的 departmentId
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	@Column(name = "AGE")
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age
	 *            要设置的 age
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "GENDER")
	public Integer getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            要设置的 gender
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}

	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            要设置的 createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "UPDATE_TIME")
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 *            要设置的 updateTime
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "ACCOUNT")
	public String getAccount() {
		return account;
	}

	/**
	 * @param account
	 *            要设置的 account
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "TELPHONE")
	public String getTelphone() {
		return telphone;
	}

	/**
	 * @param telphone
	 *            要设置的 telphone
	 */
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            要设置的 address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "ISDELETED")
	public Integer getIsDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted
	 *            要设置的 isDeleted
	 */
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Column(name = "SECURITY_CODE")
	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	@Column(name = "SECURITY_CODE_CREATE_TIME")
	public Date getSecurityCodeCreateTime() {
		return securityCodeCreateTime;
	}

	public void setSecurityCodeCreateTime(Date securityCodeCreateTime) {
		this.securityCodeCreateTime = securityCodeCreateTime;
	}

	@Column(name = "BIRTHDAY")
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getBirthDay() {
		return birthDay;
	}

	/**
	 * @param birthDay
	 *            要设置的 birthDay
	 */
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            要设置的 email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
