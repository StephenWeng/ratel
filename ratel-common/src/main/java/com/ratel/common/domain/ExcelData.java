/**
 * @项目名:wikin-common
 * @文件名:ExcelData.java
 * @包名:com.dqgb.common.domain
 * @描述 ExcelData.java
 * @修改人 wenzhang
 * @修改时间:2018年2月13日上午11:26:30
 * @修改内容:新增
 * @版权:Copyright 2009-2017 版权所有：大庆金桥信息技术工程有限公司
 *
*/

package com.ratel.common.domain;

import java.util.Arrays;
import java.util.List;

/**
 * ClassName:ExcelData <br/>
 * Function: 传递excel表格数据，生成excel <br/>
 * Reason: 使用者封装属性，表头与表体列一一对应 <br/>
 * Date: 2018年2月13日 上午11:26:30 <br/>
 * 
 * @author wenzhang
 * @version
 * @since JDK 1.8
 * @see
 */
public class ExcelData {

	private String[] headNameArr;// 表头名称数组

	private List<Object[]> bodyData;// 表体数据数组集合

	public String[] getHeadNameArr() {
		return headNameArr;
	}

	public void setHeadNameArr(String[] headNameArr) {
		this.headNameArr = headNameArr;
	}

	public List<Object[]> getBodyData() {
		return bodyData;
	}

	public void setBodyData(List<Object[]> bodyData) {
		this.bodyData = bodyData;
	}

	@Override
	public String toString() {
		return "ExcelData [headNameArr=" + Arrays.toString(headNameArr) + ", bodyData=" + bodyData + "]";
	}

}
