/**
* @描述
* @文件名:IResourceService.java
* @版权:Copyright 2018 版权所有：大庆金桥信息技术工程有限公司成都分公司
* @描述:IResourceService.java
* @修改人:技术部-文章
* @修改时间:2018年12月31日 下午2:02:13
* @修改内容:新增
*/
package com.ratel.auth.service;

import com.ratel.common.response.ResponseData;

/**
 * @文件名:IResourceService.java
 * @版权:Copyright 2018 版权所有：大庆金桥信息技术工程有限公司成都分公司
 * @描述:资源业务逻辑层接口
 * @修改人:技术部-文章
 * @修改时间:2018年12月31日 下午2:02:13
 * @修改内容:新增
 */
public interface IResourceService {

	/**
	 * @Title queryResources
	 * @author :技术部-文章
	 * @Description 查询用户的菜单列表
	 * @date 2018年12月31日 下午2:06:57
	 * @param account
	 *            用户账号
	 * @return ResponseData
	 */
	public ResponseData queryResourcesByAccount(String account);

}
