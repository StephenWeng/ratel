/**  
* @描述 
* @文件名:TreeVo.java
* @版权:Copyright 2018 版权所有：平头哥
* @描述:TreeVo.java
* @修改人:Stephen
* @修改时间:2019年1月2日 下午5:48:58
* @修改内容:新增
*/
package com.ratel.common.domain;

import java.util.List;

/**
 * @文件名:TreeVo.java
 * @版权:Copyright 2018 版权所有：平头哥
 * @描述:树形数据统一返回对象
 * @修改人:Stephen
 * @修改时间:2019年1月2日 下午5:48:58
 * @修改内容:新增
 */
public class TreeVo {

	private String id;// 节点唯一标识

	private String label;// 节点名

	private List<TreeVo> children;// 下级集合

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
	 * @return label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label 要设置的 label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return children
	 */
	public List<TreeVo> getChildren() {
		return children;
	}

	/**
	 * @param children 要设置的 children
	 */
	public void setChildren(List<TreeVo> children) {
		this.children = children;
	}

}
