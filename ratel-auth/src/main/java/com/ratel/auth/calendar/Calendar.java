package com.ratel.auth.calendar;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "CALENDAR")
@ApiModel(value = "Calendar", description = "事件对象")
public class Calendar {

	@ApiModelProperty(value = "主键ID")
	private String id;// 主键

	@ApiModelProperty(value = "事件名称")
	private String title;// 名称

	@ApiModelProperty(value = "开始时间")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date startTime;// 开始时间

	@ApiModelProperty(value = "结束时间")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date endTime;// 结束时间

	@ApiModelProperty(value = "地址")
	private Integer isAllDay;// 是否全体 0 是；1：不是

	@ApiModelProperty(value = "连接地址")
	private String url;// 连接地址

	@ApiModelProperty(value = "状态")
	private Integer status;// 状态

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

	/**
	 * @return title
	 */
	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}

	/**
	 * @param title 要设置的 title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return startTime
	 */
	@Column(name = "START_TIME")
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime 要设置的 startTime
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return endTime
	 */
	@Column(name = "END_TIME")
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime 要设置的 endTime
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return isAllDay
	 */
	@Column(name = "IS_ALLDAT")
	public Integer getIsAllDay() {
		return isAllDay;
	}

	/**
	 * @param isAllDay 要设置的 isAllDay
	 */
	public void setIsAllDay(Integer isAllDay) {
		this.isAllDay = isAllDay;
	}

	/**
	 * @return url
	 */
	@Column(name = "URL")
	public String getUrl() {
		return url;
	}

	/**
	 * @param url 要设置的 url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return status
	 */
	@Column(name = "STATUS")
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status 要设置的 status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

}
