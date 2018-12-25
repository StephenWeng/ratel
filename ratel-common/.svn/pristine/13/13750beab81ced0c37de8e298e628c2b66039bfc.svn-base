package com.dqgb.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * ClassName: ResponseData <br/>
 * Function: T数据返回封装类. <br/>
 * date: 2018年2月11日 上午10:49:51 <br/>
 *
 * @author wenzhang
 * @version
 * @see [相关类/方法]
 * @Description:
 * @since JDK 1.8
 */
@ApiModel(value = "ResponseData", description = "数据返回封装类，继承自Response")
public class ResponseData extends Response {
	@ApiModelProperty(value = "返回的数据(Object)")
	private Object data;

	/**
	 * 
	 * Creates a new instance of ResponseData.
	 *
	 */
	public ResponseData() {
	}

	/**
	 * 
	 * Creates a new instance of ResponseData.
	 *
	 * @param data
	 */
	public ResponseData(Object data) {
		this.data = data;
	}

	/**
	 * 
	 * Creates a new instance of ResponseData.
	 *
	 * @param msg
	 */
	public ResponseData(ResponseMsg msg) {
		super(msg);
	}

	/**
	 * 
	 * Creates a new instance of ResponseData.
	 *
	 * @param rspCode
	 * @param rspMsg
	 */
	public ResponseData(String rspCode, String rspMsg) {
		super(rspCode, rspMsg);
	}

	/**
	 * 
	 * Creates a new instance of ResponseData.
	 *
	 * @param rspCode
	 * @param rspMsg
	 * @param data
	 */
	public ResponseData(String rspCode, String rspMsg, Object data) {
		super(rspCode, rspMsg);
		this.data = data;
	}

	/**
	 * 
	 * Creates a new instance of ResponseData.
	 *
	 * @param msg
	 * @param data
	 */
	public ResponseData(ResponseMsg msg, Object data) {
		super(msg);
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseData{" + "data=" + data + "} " + super.toString();
	}
}
