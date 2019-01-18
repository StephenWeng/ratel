/**
* @描述
* @文件名:ReourceVo.java
* @版权:Copyright 2018 版权所有：大庆金桥信息技术工程有限公司成都分公司
* @描述:ReourceVo.java
* @修改人:技术部-文章
* @修改时间:2018年12月31日 下午2:15:52
* @修改内容:新增
*/
package com.ratel.auth.vo;

import java.util.List;

/**
 * @文件名:ReourceVo.java
 * @版权:Copyright 2018 版权所有：大庆金桥信息技术工程有限公司成都分公司
 * @描述:资源返回实体
 * @修改人:技术部-文章
 * @修改时间:2018年12月31日 下午2:15:52
 * @修改内容:新增
 */
public class ResourceVo implements Comparable<ResourceVo> {

	private String id;// 主键

	private String name;// 名称

	private String code;// 编码

	private String url;// 连接地址

	private String pId;// 父级id

	private int sortCol;// 排序字段

	private List<ResourceVo> child;// 下级

	private String icon;// 图标

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 *
	 * @param id
	 * @param name
	 * @param url
	 * @param child
	 */
	public ResourceVo(String id, String name, String code, String url, String pId, int sortCol, List<ResourceVo> child,
			String icon) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.url = url;
		this.pId = pId;
		this.sortCol = sortCol;
		this.child = child;
		this.icon = icon;
	}

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<ResourceVo> getChild() {
		return child;
	}

	public void setChild(List<ResourceVo> child) {
		this.child = child;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public int getSortCol() {
		return sortCol;
	}

	public void setSortCol(int sortCol) {
		this.sortCol = sortCol;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code 要设置的 code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @Title compareTo
	 * @author :技术部-文章
	 * @Description
	 * @date 2018年12月31日 下午3:18:06
	 * @param o
	 * @return
	 */
	@Override
	public int compareTo(ResourceVo o) {
		try {
			if (o.sortCol > sortCol) {
				return -1;
			} else if (o.sortCol < sortCol) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			return 0;
		}
	}

}
