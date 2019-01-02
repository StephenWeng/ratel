/**
* @描述
* @文件名:ResourceController.java
* @版权:Copyright 2018 版权所有：大庆金桥信息技术工程有限公司成都分公司
* @描述:ResourceController.java
* @修改人:技术部-文章
* @修改时间:2018年12月31日 下午1:52:04
* @修改内容:新增
*/
package com.ratel.auth.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ratel.auth.service.IResourceService;
import com.ratel.common.response.ResponseData;
import com.ratel.common.web.BaseController;

/**
 * @文件名:ResourceController.java
 * @版权:Copyright 2018 版权所有：大庆金桥信息技术工程有限公司成都分公司
 * @描述:资源管理相关控制层
 * @修改人:技术部-文章
 * @修改时间:2018年12月31日 下午1:52:04
 * @修改内容:新增
 */
@Controller
@EnableAutoConfiguration
@RestController
@RequestMapping(value = "resources")
public class ResourceController extends BaseController {

	@Autowired
	private IResourceService resourceService;

	/**
	 * @Title queryResources
	 * @author :技术部-文章
	 * @Description 查询当前登陆用户的菜单列表
	 * @date 2018年12月31日 下午2:06:57
	 * @return ResponseData
	 */
	@ResponseBody
	@RequestMapping(value = "/myResources", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseData queryResources() {
		return resourceService.queryResourcesByAccount(this.getUserAccount());
	}

}
