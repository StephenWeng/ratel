/**
* @描述
* @文件名:ResourceServiceImpl.java
* @版权:Copyright 2018 版权所有：大庆金桥信息技术工程有限公司成都分公司
* @描述:ResourceServiceImpl.java
* @修改人:技术部-文章
* @修改时间:2018年12月31日 下午2:02:39
* @修改内容:新增
*/
package com.ratel.auth.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratel.auth.domain.Resource;
import com.ratel.auth.domain.User;
import com.ratel.auth.domain.UserResources;
import com.ratel.auth.repository.ResourceRepository;
import com.ratel.auth.repository.UserRepository;
import com.ratel.auth.repository.UserResourceRepository;
import com.ratel.auth.service.IResourceService;
import com.ratel.auth.vo.ResourceVo;
import com.ratel.common.response.ResponseData;
import com.ratel.common.response.ResponseMsg;
import com.ratel.common.utils.DateUtils;
import com.ratel.common.utils.StringUtil;

/**
 * @文件名:ResourceServiceImpl.java
 * @版权:Copyright 2018 版权所有：大庆金桥信息技术工程有限公司成都分公司
 * @描述:资源业务逻辑层实现类
 * @修改人:技术部-文章
 * @修改时间:2018年12月31日 下午2:02:39
 * @修改内容:新增
 */
@Service
public class ResourceServiceImpl implements IResourceService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ResourceRepository resourceRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserResourceRepository userResourceRepository;

	/**
	 * @Title queryResources
	 * @author :技术部-文章
	 * @Description 查询用户的菜单列表
	 * @date 2018年12月31日 下午2:06:57
	 * @param account
	 *            用户账号
	 * @return ResponseData
	 */
	@Override
	public ResponseData queryResourcesByAccount(String account) {
		try {
			User userDo = userRepository.findByUserAccount(account);
			if (null == userDo) {
				logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "查询账号：" + account + "菜单时找不到账号");
				return new ResponseData(ResponseMsg.FAILED.getCode(), "账号不存在，请联系管理员！");
			}
			String userId = userDo.getId();
			List<UserResources> userResources = userResourceRepository.findByUserid(userId);
			List<String> ids = new ArrayList<String>();
			for (UserResources userResource : userResources) {
				ids.add(userResource.getResourceId());
			}
			List<Resource> list = resourceRepository.queryResources(ids);
			if (null == list || list.size() == 0) {
				return new ResponseData(ResponseMsg.FAILED.getCode(), "用户无资源菜单");
			}
			List<ResourceVo> res = sortResources(list);
			return new ResponseData(ResponseMsg.SUCCESS, res);
		} catch (Exception e) {
			logger.error(
					DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "查询账号：" + account + "菜单时系统异常：" + e.getMessage());
			return new ResponseData(ResponseMsg.FAILED.getCode(), "系统异常!");
		}
	}

	/**
	 * @Title sortResources
	 * @author :技术部-文章
	 * @Description 资源菜单按层级关系排序
	 * @date 2018年12月31日 下午2:25:42
	 * @param list
	 *            资源菜单集合
	 * @return List<ResourceVo>
	 */
	private List<ResourceVo> sortResources(List<Resource> resources) {
		List<ResourceVo> list = new ArrayList<ResourceVo>();
		for (Resource resource : resources) {
			if (StringUtil.isEmpty(resource.getpId())) {
				ResourceVo vo = new ResourceVo(resource.getId(), resource.getName(), resource.getUrl(),
						resource.getpId(), resource.getSortCol(), getChildList(resource, resources));
				list.add(vo);
			}
		}
		Collections.sort(list);
		return list;
	}

	/**
	 * @Title getChildList
	 * @author :技术部-文章
	 * @Description 递归获取下级资源菜单
	 * @date 2018年12月31日 下午2:32:46
	 * @param pResource
	 *            当前资源菜单
	 * @param resources
	 *            资源菜单集合
	 * @return List<ResourceVo>
	 */
	private List<ResourceVo> getChildList(Resource pResource, List<Resource> resources) {
		List<ResourceVo> list = new ArrayList<ResourceVo>();
		if (null != resources && resources.size() >= 0) {
			for (Resource resource : resources) {
				if (pResource.getId().equals(resource.getpId())) {
					ResourceVo vo = new ResourceVo(resource.getId(), resource.getName(), resource.getUrl(),
							resource.getpId(), resource.getSortCol(), getChildList(resource, resources));
					list.add(vo);
				}
			}
		}
		return list;
	}

	// var a={};
	// a.id='a';
	// a.name="基础管理";
	// a.childList=[];
	// var a1={};
	// var a11={};
	// a11.id='a11';
	// a11.name='用户管理';
	// var a12={};
	// a12.id='a12';
	// a12.name='部门管理';
	// a.childList.push(a11);
	// a.childList.push(a12);
	//
	// var a21={};
	// a21.id='a21';
	// a21.name='资源管理';
	// var a22={};
	// a22.id='a22';
	// a22.name='资源分配';
	// a.childList.push(a21);
	// a.childList.push(a22);
	// vm.resourcesList.push(a);
	// console.log(vm.resourcesList);

}
