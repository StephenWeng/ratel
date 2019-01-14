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
import com.ratel.common.domain.TreeVo;
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
	 * @param account 用户账号
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
	 * @param list 资源菜单集合
	 * @return List<ResourceVo>
	 */
	private List<ResourceVo> sortResources(List<Resource> resources) {
		List<ResourceVo> list = new ArrayList<ResourceVo>();
		for (Resource resource : resources) {
			if (StringUtil.isEmpty(resource.getpId())) {
				ResourceVo vo = new ResourceVo(resource.getId(), resource.getName(), resource.getUrl(),
						resource.getpId(), resource.getSortCol(), getChildList(resource, resources),
						resource.getIcon());
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
	 * @param pResource 当前资源菜单
	 * @param resources 资源菜单集合
	 * @return List<ResourceVo>
	 */
	private List<ResourceVo> getChildList(Resource pResource, List<Resource> resources) {
		List<ResourceVo> list = new ArrayList<ResourceVo>();
		if (null != resources && resources.size() >= 0) {
			for (Resource resource : resources) {
				if (pResource.getId().equals(resource.getpId())) {
					ResourceVo vo = new ResourceVo(resource.getId(), resource.getName(), resource.getUrl(),
							resource.getpId(), resource.getSortCol(), getChildList(resource, resources),
							resource.getIcon());
					list.add(vo);
				}
			}
		}
		return list;
	}

	/**
	 * @Title queryResourceTree
	 * @author :stephen
	 * @Description
	 * @date 2019年1月13日 下午8:26:28
	 * @param ids
	 * @return
	 */
	@Override
	public List<TreeVo> queryResourceTree(String ids) {
		try {
			List<TreeVo> list = new ArrayList<TreeVo>();
			if (StringUtil.isEmpty(ids) || ids.split(",").length == 0) {
				// 传入id为空，需要查询全部资源
				List<Resource> topResources = resourceRepository.queryTopResource();
				for (Resource resource : topResources) {
					TreeVo vo = new TreeVo();
					vo.setId(resource.getId());
					vo.setLabel(resource.getName());
					vo.setClassName(resource.getIcon());
					// 递归查询下级
					vo.setChildren(recursionResource(resource.getId()));
					list.add(vo);
				}
			} else {
				// 需要过滤查询资源
			}
			return list;
		} catch (Exception e) {
			logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "获取资源树失败：" + e.getMessage());
			return null;
		}
	}

	/**
	 * @Title recursionResource
	 * @author :stephen
	 * @Description 根据上级资源id递归查询全部下级
	 * @date 2019年1月13日 下午9:02:49
	 * @param pId 上级资源id
	 * @return List<TreeVo>
	 */
	private List<TreeVo> recursionResource(String pId) {
		List<TreeVo> list = new ArrayList<TreeVo>();
		List<Resource> children = resourceRepository.queryResourcesByPid(pId);
		if (children.size() > 0) {
			for (Resource resource : children) {
				TreeVo vo = new TreeVo();
				vo.setId(resource.getId());
				vo.setLabel(resource.getName());
				vo.setClassName(resource.getIcon());
				// 递归查询下级
				vo.setChildren(recursionResource(resource.getId()));
				list.add(vo);
			}
		}
		return list;
	}

	/**
	 * @Title queryResource
	 * @author :Stephen
	 * @Description
	 * @date 2019年1月14日 上午11:30:05
	 * @param pId
	 * @return
	 */
	@Override
	public ResponseData queryResource(String pId) {
		try {
			List<Resource> children = resourceRepository.queryResourcesByPid(pId);
			return new ResponseData(ResponseMsg.SUCCESS, children);
		} catch (Exception e) {
			logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "查询" + pId + "的下级资源时系统异常：" + e.getMessage());
			return new ResponseData(ResponseMsg.FAILED.getCode(), "系统异常");
		}
	}

}
