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

import java.util.List;

import com.ratel.common.domain.TreeVo;
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

	/**
	 * @Title queryResourceTree
	 * @author :stephen
	 * @Description 获取资源树
	 * @date 2019年1月13日 下午8:23:16
	 * @param ids
	 *            资源id以,相连。若为空，则查询全部资源。需要注意，如若用户有下级资源id，那么上级资源id必须存在
	 * @return List<TreeVo>
	 */
	public List<TreeVo> queryResourceTree(String ids);

}
