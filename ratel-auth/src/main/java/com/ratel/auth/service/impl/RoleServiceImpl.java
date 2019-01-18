/**
* @描述
* @文件名:RoleServiceImpl.java
* @版权:Copyright 2019 版权所有：平头哥
* @描述:RoleServiceImpl.java
* @修改人:stephen
* @修改时间:2019年1月13日 上午12:46:56
* @修改内容:新增
*/
package com.ratel.auth.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ratel.auth.domain.Role;
import com.ratel.auth.domain.User;
import com.ratel.auth.repository.ResourceRepository;
import com.ratel.auth.repository.RoleRepository;
import com.ratel.auth.repository.RoleSpecification;
import com.ratel.auth.repository.UserRepository;
import com.ratel.auth.service.IRoleService;
import com.ratel.auth.vo.RoleVo;
import com.ratel.common.response.ResponseData;
import com.ratel.common.response.ResponseMsg;
import com.ratel.common.utils.DateUtils;
import com.ratel.common.utils.StringUtil;

/**
 * @文件名:RoleServiceImpl.java
 * @版权:Copyright 2018 版权所有：平头哥
 * @描述:角色业务逻辑处理层实现类
 * @修改人:stephen
 * @修改时间:2019年1月13日 上午12:46:56
 * @修改内容:新增
 */
@Service
public class RoleServiceImpl implements IRoleService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ResourceRepository resourceRepository;

	@Override
	public ResponseData queryRolePage(Integer currentPage, Integer pagesize, String name) {
		try {
			Pageable pageAble = new PageRequest(currentPage, pagesize, Direction.DESC, "createTime");
			Role role = new Role();
			// 当传入的pId为空时，传入顶层机构的下级
			role.setName(name);
			RoleSpecification pcs = new RoleSpecification(role);
			Page<Role> page = roleRepository.findAll(pcs, pageAble);
			// 将资源对象集合塞入返回数据中
			List<Role> list = page.getContent();
			List<RoleVo> content = new ArrayList<RoleVo>();
			for (Role roleDo : list) {
				RoleVo vo = new RoleVo();
				vo.setId(roleDo.getId());
				vo.setName(roleDo.getName());
				vo.setCreateTime(roleDo.getCreateTime());
				vo.setUpdateTime(roleDo.getUpdateTime());
				vo.setResourceIds(roleDo.getResourceIds());
				String[] resourceIdArr = roleDo.getResourceIds().split(",");// 角色拥有的资源id数组
				if (null != resourceIdArr && resourceIdArr.length > 0) {
					vo.setResources(resourceRepository.queryResources(Arrays.asList(resourceIdArr)));
				}
				content.add(vo);
			}
			// 返回对象重新封装
			Page<RoleVo> pageVo = new PageImpl<>(content, pageAble, page.getTotalElements());
			return new ResponseData(ResponseMsg.SUCCESS, pageVo);
		} catch (Exception e) {
			logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "查询角色列表错误：" + e.getMessage());
			return new ResponseData(ResponseMsg.FAILED.getCode(), "系统异常");
		}
	}

	@Transactional
	@Override
	public ResponseData addRole(Role role) {
		try {
			role.setCreateTime(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS));
			Role roleVo = roleRepository.save(role);
			return new ResponseData(ResponseMsg.SUCCESS, roleVo);
		} catch (Exception e) {
			logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "新增角色时错误：" + e.getMessage());
			return new ResponseData(ResponseMsg.FAILED.getCode(), "系统异常");
		}
	}

	@Transactional
	@Override
	public ResponseData updateRole(Role role) {
		try {
			Role roleVo = roleRepository.findOne(role.getId());
			if (null == roleVo) {
				logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "编辑角色id：" + role.getId() + "时已不存在");
				return new ResponseData(ResponseMsg.FAILED.getCode(), "该角色已不存在");
			}
			roleVo.setName(role.getName());
			roleVo.setResourceIds(role.getResourceIds());
			roleVo.setUpdateTime(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS));
			Role roleDo = roleRepository.save(roleVo);
			return new ResponseData(ResponseMsg.SUCCESS, roleDo);
		} catch (Exception e) {
			logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "编辑角色时错误：" + e.getMessage());
			return new ResponseData(ResponseMsg.FAILED.getCode(), "系统异常");
		}
	}

	@Transactional
	@Override
	public ResponseData delRole(List<String> ids) {
		try {
			roleRepository.delInBatch(ids);
			return new ResponseData(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "删除角色时错误：" + e.getMessage());
			return new ResponseData(ResponseMsg.FAILED.getCode(), "系统异常");
		}
	}

	@Override
	public ResponseData checkOnly(String name, Integer method, String id) {
		try {
			ResponseData res = null;
			List<Role> list = null;
			switch (method) {
			case 0:
				// 新增
				list = roleRepository.checkName(name);
				;
				break;
			case 1:
				// 编辑
				list = roleRepository.checkName(name, id);
				break;
			default:
				res = new ResponseData(ResponseMsg.FAILED.getCode(), "操作方式异常");
				break;
			}
			if (null != list) {
				res = list.size() == 0 ? new ResponseData(ResponseMsg.SUCCESS)
						: new ResponseData(ResponseMsg.FAILED.getCode(), "该名称已被使用");
			}
			return res;
		} catch (Exception e) {
			return new ResponseData(ResponseMsg.FAILED.getCode(), "系统异常");
		}
	}

	/**
	 * @Title queryAllRoles
	 * @author :Stephen
	 * @Description
	 * @date 2019年1月18日 下午3:40:45
	 * @return
	 */
	@Override
	public ResponseData queryAllRoles() {
		try {
			List<Role> list = (List<Role>) roleRepository.findAll();
			return new ResponseData(ResponseMsg.SUCCESS, list);
		} catch (Exception e) {
			logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "查询全部角色时：" + e.getMessage());
			return new ResponseData(ResponseMsg.FAILED.getCode(), "系统异常");

		}
	}

	/**
	 * @Title queryRolesByAccount
	 * @author :Stephen
	 * @Description
	 * @date 2019年1月18日 下午4:17:43
	 * @param account
	 * @return
	 */
	@Override
	public ResponseData queryRolesByAccount(String account) {
		try {
			List<Role> list = new ArrayList<Role>();
			User userDo = userRepository.findByUserAccount(account);
			String roleIds = userDo.getRoleIds();// 角色id
			if (StringUtil.isEmpty(roleIds)) {
				return new ResponseData(ResponseMsg.SUCCESS, list);
			}
			list = roleRepository.queryRoles(Arrays.asList(roleIds.split(",")));
			return new ResponseData(ResponseMsg.SUCCESS, list);
		} catch (Exception e) {
			logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "查询角色时：" + e.getMessage());
			return new ResponseData(ResponseMsg.FAILED.getCode(), "系统异常");

		}
	}

}
