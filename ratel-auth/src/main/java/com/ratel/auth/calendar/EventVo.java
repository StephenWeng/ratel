/**  
* @描述 
* @文件名:EventVo.java
* @版权:Copyright 2018 版权所有：平头哥
* @描述:EventVo.java
* @修改人:Stephen
* @修改时间:2019年5月23日 下午3:55:09
* @修改内容:新增
*/
package com.ratel.auth.calendar;

/**  
* @描述 
* @文件名:EventVo.java
* @版权:Copyright 2018 版权所有：平头哥
* @描述:EventVo.java
* @修改人:Stephen
* @修改时间:2019年5月23日 下午3:55:09
* @修改内容:新增
*/
/**
 * @className EventVo
 * @author :Stephen
 * @Description
 * @date 2019年5月23日 下午3:55:09
 */
public class EventVo {

	private String id;

	private String title;

	private String start;

	private String end;

	private boolean allDay;

	private String url;

	private String color;// 背景和边框颜色

	private String backgroundColor;// 背景颜色

	private String borderColor;// 边框颜色

	private String textColor;// 文本颜色

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 */
	public EventVo() {

	}

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param id
	 * @param title
	 * @param startTime
	 * @param endTime
	 * @param isAllDay
	 * @param url
	 * @param color
	 * @param backgroundColor
	 * @param borderColor
	 * @param textColor
	 */
	public EventVo(String id, String title, String start, String end, boolean allDay, String url, String color,
			String backgroundColor, String borderColor, String textColor) {
		super();
		this.id = id;
		this.title = title;
		this.start = start;
		this.end = end;
		this.allDay = allDay;
		this.url = url;
		this.color = color;
		this.backgroundColor = backgroundColor;
		this.borderColor = borderColor;
		this.textColor = textColor;
	}

	public EventVo(String id, String title, String start, String end, boolean allDay, String url, Integer status) {
		super();
		this.id = id;
		this.title = title;
		this.start = start;
		this.end = end;
		this.allDay = allDay;
		this.url = url;
		switch (status) {
		case 0:
			this.color = "#2C3E50";
			this.backgroundColor = "#2C3E50";
			this.borderColor = "white";
			this.textColor = "white";
			break;
		case 1:
			this.color = "green";
			this.backgroundColor = "green";
			this.borderColor = "green";
			this.textColor = "white";
			break;
		case 2:
			this.color = "blue";
			this.backgroundColor = "blue";
			this.borderColor = "blue";
			this.textColor = "white";
			break;
		default:
			this.color = "#2C3E50";
			this.backgroundColor = "#2C3E50";
			this.borderColor = "white";
			this.textColor = "white";
			break;
		}

	}

	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id 要设置的 id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return title
	 */
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
	public String getStart() {
		return start;
	}

	/**
	 * @param startTime 要设置的 startTime
	 */
	public void setStart(String start) {
		this.start = start;
	}

	/**
	 * @return endTime
	 */
	public String getEnd() {
		return end;
	}

	/**
	 * @param endTime 要设置的 endTime
	 */
	public void setEnd(String end) {
		this.end = end;
	}

	/**
	 * @return allDay
	 */
	public boolean isAllDay() {
		return allDay;
	}

	/**
	 * @param allDay 要设置的 allDay
	 */
	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}

	/**
	 * @return url
	 */
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
	 * @return color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color 要设置的 color
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return backgroundColor
	 */
	public String getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * @param backgroundColor 要设置的 backgroundColor
	 */
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	/**
	 * @return borderColor
	 */
	public String getBorderColor() {
		return borderColor;
	}

	/**
	 * @param borderColor 要设置的 borderColor
	 */
	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

	/**
	 * @return textColor
	 */
	public String getTextColor() {
		return textColor;
	}

	/**
	 * @param textColor 要设置的 textColor
	 */
	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

}
