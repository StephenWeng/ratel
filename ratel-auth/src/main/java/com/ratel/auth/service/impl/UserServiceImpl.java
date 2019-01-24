package com.ratel.auth.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ratel.auth.domain.Email;
import com.ratel.auth.domain.User;
import com.ratel.auth.repository.DepartmentRepository;
import com.ratel.auth.repository.UserRepository;
import com.ratel.auth.repository.UserSpecification;
import com.ratel.auth.service.IDepartmentService;
import com.ratel.auth.service.IEmailService;
import com.ratel.auth.service.IUserService;
import com.ratel.auth.utils.JwtTokenUtil;
import com.ratel.common.constant.CommonConst;
import com.ratel.common.domain.Encrypt;
import com.ratel.common.domain.TreeVo;
import com.ratel.common.response.ResponseData;
import com.ratel.common.response.ResponseMsg;
import com.ratel.common.utils.CookieUtil;
import com.ratel.common.utils.DateUtils;
import com.ratel.common.utils.MD5Util;
import com.ratel.common.utils.StringUtil;

/**
 * @文件名:UserServiceImpl.java
 * @版权:Copyright 2018 版权所有：平头哥
 * @描述:用户相关操作业务层实现类
 * @修改人:Stephen
 * @修改时间:2018年12月13日 下午4:35:04
 * @修改内容:新增
 */
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	EntityManager entityManager;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final static int MAX_SECURITY_CODE_INDATE = 3;// 重置密码验证码最大有效期3小时

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private IDepartmentService departmentService;

	@Autowired
	private IEmailService emailService;

	@Value("${jwt.expiration}")
	private int expiration;// cookie存在时长

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@SuppressWarnings("unchecked")
	@Override
	public ResponseData queryUserTree() {
		try {
			ResponseData departmentRes = departmentService.queryDepartmentTree();
			if (!ResponseMsg.SUCCESS.getCode().equals(departmentRes.getRspCode())) {
				return departmentRes;
			}
			List<TreeVo> tree = (List<TreeVo>) departmentRes.getData();
			List<TreeVo> list = tree.get(0).getChildren();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("treeData", list);// 树数据
			String sql = "select d.name name,count(u.id) value from user u, departments d where u.DEPARTMENT_ID = d.ID and d.pId IS NOT NULL and u.ISDELETED=0 GROUP BY d.name";

			List<Map<String, Object>> numData = entityManager.createNativeQuery(sql).unwrap(org.hibernate.Query.class)
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
			map.put("numData", numData);// 人数数据
			return new ResponseData(ResponseMsg.SUCCESS, map);
		} catch (Exception e) {
			logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "查询部门组织机构树错误：" + e.getMessage());
			return new ResponseData(ResponseMsg.FAILED.getCode(), "系统异常!");
		}
	}

	@Override
	public ResponseData queryUserPage(Integer currentPage, Integer pagesize, User userDo) {
		try {
			Pageable pageAble = new PageRequest(currentPage, pagesize, Direction.DESC, "createTime");
			// 当传入的departmentId为空时，查询最顶层组织的id
			String departmentId = userDo.getDepartmentId();
			userDo.setDepartmentId(StringUtil.isNotBlank(departmentId) ? departmentId
					: departmentRepository.queryTopDepartment().get(0).getId());
			userDo.setIsDeleted(0);
			UserSpecification pcs = new UserSpecification(userDo);
			Page<User> page = userRepository.findAll(pcs, pageAble);
			return new ResponseData(ResponseMsg.SUCCESS, page);
		} catch (Exception e) {
			logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "查询部门组织列表错误：" + e.getMessage());
			return new ResponseData(ResponseMsg.FAILED.getCode(), "系统异常");
		}
	}

	@Transactional
	@Override
	public ResponseData addUser(User user) {
		try {
			user.setEmail(StringUtil.email2Lower(user.getEmail()));
			user.setCreateTime(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS));
			user.setIsDeleted(0);
			// 生成随机密码，并发送至邮箱
			String randomPwd = StringUtil.randomStr(8);
			Encrypt encrypt = MD5Util.generate(randomPwd);
			// 将新密码发送至邮件
			boolean hasSend = emailService.sendSimpleEmail(new Email(user.getEmail(), "", "新增用户",
					user.getName() + "，您好！您的初始密码是：" + randomPwd + "，请及时上线进行修改。"));
			if (!hasSend) {
				logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "新增用户，账号为：" + user.getAccount()
						+ "发送初始密码邮件时失败");
				return new ResponseData(ResponseMsg.FAILED.getCode(), "邮件发送失败");
			}
			user.setPassword(encrypt.getMd5Passwd());
			User userVo = userRepository.save(user);
			logger.info(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "新增用户，账号为：" + user.getAccount());
			return new ResponseData(ResponseMsg.SUCCESS, userVo);
		} catch (Exception e) {
			logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "新增用户时错误：" + e.getMessage());
			return new ResponseData(ResponseMsg.FAILED.getCode(), "系统异常");
		}
	}

	@Transactional
	@Override
	public ResponseData updateUser(User user) {
		try {
			User userDo = userRepository.findByUserAccount(user.getAccount());
			if (null == userDo) {
				return new ResponseData(ResponseMsg.FAILED.getCode(), "该用户已不存在");
			}
			userDo.setName(user.getName());
			userDo.setDepartmentId(user.getDepartmentId());
			userDo.setGender(user.getGender());
			userDo.setEmail(user.getEmail());
			userDo.setTelphone(user.getTelphone());
			userDo.setAddress(user.getAddress());
			userDo.setBirthDay(user.getBirthDay());
			userDo.setUpdateTime(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS));
			User userVo = userRepository.save(userDo);
			logger.info(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "更新用户信息成功，账号为：" + user.getAccount());
			return new ResponseData(ResponseMsg.SUCCESS, userVo);
		} catch (Exception e) {
			logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "更新用户时错误，用户账号：" + user.getAccount() + "错误信息："
					+ e.getMessage());
			return new ResponseData(ResponseMsg.FAILED.getCode(), "系统异常");
		}
	}

	@Transactional
	@Override
	public ResponseData delUsers(List<String> ids) {
		try {
			userRepository.delInBatch(ids);
			return new ResponseData(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "批量删除用户时错误：" + e.getMessage());
			return new ResponseData(ResponseMsg.FAILED.getCode(), "系统异常");
		}
	}

	/**
	 * @Title queryUser
	 * @author :Stephen
	 * @Description 根据用户账号查询用户，不模糊查询
	 * @date 2018年12月22日 上午10:57:08
	 * @param account 用户账号
	 * @return 操作是否成功
	 */
	@Override
	public ResponseData queryUser(String account) {
		try {
			User userDo = userRepository.findByUserAccount(account);
			return null == userDo ? new ResponseData(ResponseMsg.FAILED.getCode(), "用户不存在！")
					: new ResponseData(ResponseMsg.SUCCESS, userDo);
		} catch (Exception e) {
			return new ResponseData(ResponseMsg.FAILED.getCode(), "系统异常!");
		}
	}

	/**
	 * @Title login
	 * @author :Stephen
	 * @Description
	 * @date 2018年12月22日 上午9:42:27
	 * @param user
	 * @return
	 */
	@Override
	public ResponseData login(User user, HttpServletResponse response) {
		try {
			// 邮箱后半段转小写
			String email = StringUtil.email2Lower(user.getEmail());
			String account = user.getAccount();
			User userDo = !StringUtil.isEmpty(account) ? userRepository.findByUserAccount(account)
					: userRepository.findByEmail(email);
			// 验证用户
			if (null == userDo) {
				logger.error(
						DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "使用账号：" + user.getAccount() + "登录时，找不到账号");
				return new ResponseData(ResponseMsg.FAILED.getCode(), "账号错误！");
			}
			email = userDo.getAccount();
			account = userDo.getEmail();
			// 验证密码
			if (!MD5Util.verify(user.getPassword(), userDo.getPassword())) {
				logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "使用账号：" + user.getAccount() + "登录时，密码错误");
				return new ResponseData(ResponseMsg.FAILED.getCode(), "密码错误！");
			}
			// 生成jwt令牌，存入cookie
			String token = jwtTokenUtil.generateToken(userDo);
			CookieUtil.addCookie(response, CommonConst.COOKIE_KEY_JWT_TOKEN, token, expiration * 1000);
			logger.info(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "使用账号：" + user.getAccount() + "登录成功");
			return new ResponseData(ResponseMsg.SUCCESS, userDo);
		} catch (Exception e) {
			logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "使用账号：" + user.getAccount() + e.getMessage());
			return new ResponseData(ResponseMsg.FAILED.getCode(), "系统异常!");
		}

	}

	@Override
	public ResponseData editInformation(User user) {
		try {
			User userDo = userRepository.findByUserAccount(user.getAccount());
			if (null == userDo) {
				logger.error(
						DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "修改账号：" + user.getAccount() + "个人信息时找不到账号");
				return new ResponseData(ResponseMsg.FAILED.getCode(), "账号不存在，请联系管理员！");
			}
			userDo.setName(user.getName());
			userDo.setEmail(user.getEmail());
			userDo.setAge(user.getAge());
			userDo.setGender(user.getGender());
			userDo.setTelphone(user.getTelphone());
			userDo.setAddress(user.getAddress());
			userDo.setBirthDay(user.getBirthDay());
			userDo.setUpdateTime(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS));
			userRepository.save(userDo);
			logger.info(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "修改账号：" + user.getAccount() + "个人信息成功");
			return new ResponseData(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "修改账号：" + user.getAccount() + "个人信息时系统异常："
					+ e.getMessage());
			return new ResponseData(ResponseMsg.FAILED.getCode(), "系统异常!");
		}
	}

	@Transactional
	@Override
	public ResponseData sendSecurityCode(User user) {
		try {
			// 邮箱后半段转小写
			String email = StringUtil.email2Lower(user.getEmail());
			String account = user.getAccount();
			// 1.查询用户
			User userDo = !StringUtil.isEmpty(account) ? userRepository.findByUserAccount(account)
					: userRepository.findByEmail(email);
			if (null == userDo) {
				logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "获取账号：" + account + "重置密码验证码时，账号错误");
				return new ResponseData(ResponseMsg.FAILED.getCode(), "账号错误！");
			}
			email = userDo.getAccount();
			account = userDo.getEmail();
			// 1.生成验证码
			String randomCode = StringUtil.randomStr(6);
			// 2.发送验证码到邮箱
			boolean hasSend = emailService.sendSimpleEmail(new Email(userDo.getEmail(), "", "重置密码验证码", userDo.getName()
					+ "您好！您重置密码的验证码是：" + randomCode + "。验证码有效时间为：" + MAX_SECURITY_CODE_INDATE + "小时；若非本人操作，请忽略本邮件!"));
			if (!hasSend) {
				// 如果没有发送成功，则操作失败
				logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "获取账号：" + account + "重置密码验证码时邮件发送失败");
				return new ResponseData(ResponseMsg.FAILED.getCode(), "邮件未正常发送，请稍后再试!");
			}
			// 4.存入数据库
			userDo.setSecurityCode(randomCode);
			userDo.setSecurityCodeCreateTime(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS));
			userRepository.save(userDo);
			logger.info(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "获取账号：" + account + "重置密码验证码成功");
			return new ResponseData(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			String account = StringUtil.isEmpty(user.getAccount()) ? user.getAccount() : user.getEmail();
			logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "获取账号：" + account + "重置密码验证码时系统异常："
					+ e.getMessage());
			return new ResponseData(ResponseMsg.FAILED.getCode(), "系统异常!");
		}
	}

	/**
	 * @Title reset
	 * @author :Stephen
	 * @Description 重置用户密码，并将密码发送至邮箱
	 * @date 2018年12月22日 上午11:17:53
	 * @param user 用户信息，包含用户账号
	 * @return 操作是否成功
	 */
	@Transactional
	@Override
	public ResponseData reset(User user) {
		try {
			// 邮箱后半段转小写
			String email = StringUtil.email2Lower(user.getEmail());
			String account = user.getAccount();
			User userDo = !StringUtil.isEmpty(account) ? userRepository.findByUserAccount(account)
					: userRepository.findByEmail(email);
			if (null == userDo) {
				logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "重置账号：" + user.getAccount() + "密码时找不到账号");
				return new ResponseData(ResponseMsg.FAILED.getCode(), "账号错误！");
			}
			email = userDo.getAccount();
			account = userDo.getEmail();
			if (null == userDo.getSecurityCodeCreateTime()) {
				logger.error(
						DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "重置账号：" + user.getAccount() + "密码时没有先获取验证码");
				return new ResponseData(ResponseMsg.FAILED.getCode(), "请先获取验证码！");
			}
			// 判断验证码是否过期
			Date now = new Date();// 此时时间
			Date indate = DateUtils.getFutureTime(userDo.getSecurityCodeCreateTime(), MAX_SECURITY_CODE_INDATE);
			if (now.getTime() > indate.getTime()) {
				return new ResponseData(ResponseMsg.FAILED.getCode(), "验证码已失效，请重新获取");
			}
			// 判断验证码是否正确
			if (!userDo.getSecurityCode().equals(user.getSecurityCode())) {
				return new ResponseData(ResponseMsg.FAILED.getCode(), "验证码错误");
			}
			/*
			 * 1.生成随机8位数的新密码
			 */
			String randomPwd = StringUtil.randomStr(8);
			/*
			 * 2.密码加密
			 */
			Encrypt encrypt = MD5Util.generate(randomPwd);
			/*
			 * 3.将新密码发送至邮件
			 */
			boolean hasSend = emailService.sendSimpleEmail(new Email(userDo.getEmail(), "", "重置密码",
					userDo.getName() + "，您好！您的密码已重置，密码是：" + randomPwd + "，请及时上线进行修改。"));
			if (!hasSend) {
				// 如果没有发送成功，则操作失败
				logger.error(
						DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "重置账号：" + user.getAccount() + "密码时邮件发送失败");
				return new ResponseData(ResponseMsg.FAILED.getCode(), "邮件发送失败，请稍后重试!");
			}
			logger.info(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "重置账号：" + user.getAccount() + "密码，发送至邮件成功");
			/*
			 * 4.新密码存入数据库
			 */
			userDo.setPassword(encrypt.getMd5Passwd());
			userDo.setUpdateTime(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS));
			userDo.setSecurityCode(null);
			userDo.setSecurityCodeCreateTime(null);
			userRepository.save(userDo);
			logger.info(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "重置账号：" + user.getAccount() + "密码成功");
			return new ResponseData(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "重置账号：" + user.getAccount() + "密码时系统异常："
					+ e.getMessage());
			return new ResponseData(ResponseMsg.FAILED.getCode(), "系统异常!");
		}
	}

	@Transactional
	@Override
	public ResponseData reset(String id) {
		try {
			// 邮箱后半段转小写
			User userDo = userRepository.findOne(id);
			if (null == userDo) {
				logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "重置账号id：" + id + "密码时找不到账号");
				return new ResponseData(ResponseMsg.FAILED.getCode(), "账号错误！");
			}
			// 1.生成随机8位数的新密码
			String randomPwd = StringUtil.randomStr(8);
			// 2.密码加密
			Encrypt encrypt = MD5Util.generate(randomPwd);
			// 3.将新密码发送至邮件
			boolean hasSend = emailService.sendSimpleEmail(new Email(userDo.getEmail(), "", "重置密码",
					userDo.getName() + "，您好！您的密码已重置，密码是：" + randomPwd + "，请及时上线进行修改。"));
			if (!hasSend) {
				// 如果没有发送成功，则操作失败
				logger.error(
						DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "重置账号：" + userDo.getAccount() + "密码时邮件发送失败");
				return new ResponseData(ResponseMsg.FAILED.getCode(), "邮件发送失败，请稍后重试!");
			}
			logger.info(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "重置账号：" + userDo.getAccount() + "密码，发送至邮件成功");
			/*
			 * 4.新密码存入数据库
			 */
			userDo.setPassword(encrypt.getMd5Passwd());
			userDo.setUpdateTime(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS));
			userRepository.save(userDo);
			logger.info(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "重置账号：" + userDo.getAccount() + "密码成功");
			return new ResponseData(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "重置账号id：" + id + "密码时系统异常：" + e.getMessage());
			return new ResponseData(ResponseMsg.FAILED.getCode(), "系统异常!");
		}
	}

	@Override
	public ResponseData resetSelf(User user) {
		try {
			User userDo = userRepository.findByUserAccount(user.getAccount());
			if (null == userDo) {
				logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "重置账号：" + user.getAccount() + "密码时找不到账号");
				return new ResponseData(ResponseMsg.FAILED.getCode(), "账号不存在，请联系管理员！");
			}
			Encrypt encrypt = MD5Util.generate(user.getPassword());
			/*
			 * 4.新密码存入数据库
			 */
			userDo.setPassword(encrypt.getMd5Passwd());
			userDo.setUpdateTime(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS));
			userDo.setSecurityCode(null);
			userDo.setSecurityCodeCreateTime(null);
			userRepository.save(userDo);
			logger.info(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "重置账号：" + user.getAccount() + "成功");
			return new ResponseData(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "重置账号：" + user.getAccount() + "密码时系统异常："
					+ e.getMessage());
			return new ResponseData(ResponseMsg.FAILED.getCode(), "系统异常!");
		}
	}

	/**
	 * @Title checkPwd
	 * @author :Stephen
	 * @Description
	 * @date 2018年12月29日 下午5:35:41
	 * @param user
	 * @return
	 */
	@Override
	public ResponseData checkPwd(User user) {
		try {
			User userDo = userRepository.findByUserAccount(user.getAccount());
			if (null == userDo) {
				logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "检验账号：" + user.getAccount() + "密码时找不到账号");
				return new ResponseData(ResponseMsg.FAILED.getCode(), "账号不存在，请联系管理员！");
			}
			// 验证密码
			if (!MD5Util.verify(user.getPassword(), userDo.getPassword())) {
				logger.error(
						DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "检验账号：" + user.getAccount() + "密码时输入密码错误");
				return new ResponseData(ResponseMsg.FAILED.getCode(), "密码错误！");
			}
			return new ResponseData(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "重置账号：" + user.getAccount() + "密码时系统异常："
					+ e.getMessage());
			return new ResponseData(ResponseMsg.FAILED.getCode(), "系统异常!");
		}
	}

	@Override
	public ResponseData checkEmail(User user) {
		try {
			User userDo = userRepository.findByUserAccount(user.getAccount());
			if (null == userDo) {
				logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "检验账号：" + user.getAccount() + "邮箱时找不到账号");
				return new ResponseData(ResponseMsg.FAILED.getCode(), "账号不存在，请联系管理员！");
			}
			List<User> otherEmail = userRepository.findEmailAnyotherAccount(userDo.getAccount(),
					StringUtil.email2Lower(user.getEmail()));
			return otherEmail.size() == 0 ? new ResponseData(ResponseMsg.SUCCESS)
					: new ResponseData(ResponseMsg.FAILED.getCode(), "该邮箱已被使用");
		} catch (Exception e) {
			logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "重置账号：" + user.getAccount() + "邮箱时系统异常："
					+ e.getMessage());
			return new ResponseData(ResponseMsg.FAILED.getCode(), "系统异常!");
		}
	}

	/**
	 * @Title checkOnly
	 * @author :Stephen
	 * @Description
	 * @date 2019年1月11日 下午4:08:35
	 * @param name
	 * @param account
	 * @param email
	 * @param method
	 * @param id
	 * @return
	 */
	@Override
	public ResponseData checkOnly(String name, String account, String email, Integer method, String id) {
		try {
			ResponseData res = null;
			switch (method) {
			case 0:
				// 新增
				if (StringUtil.isNotBlank(name)) {
					// 昵称验证
					res = checkName(name);
				} else if (StringUtil.isNotBlank(account)) {
					// 账号验证
					res = checkAccount(account);
				} else if (StringUtil.isNotBlank(email)) {
					// 邮箱验证
					res = checkEmail(StringUtil.email2Lower(email));
				}
				break;
			case 1:
				// 修改
				if (StringUtil.isNotBlank(name)) {
					// 昵称验证
					res = checkName(name, id);
				} else if (StringUtil.isNotBlank(account)) {
					// 账号验证
					res = checkAccount(account, id);
				} else if (StringUtil.isNotBlank(email)) {
					// 邮箱验证
					res = checkEmail(StringUtil.email2Lower(email), id);
				}
				break;
			default:
				res = new ResponseData(ResponseMsg.FAILED.getCode(), "操作方式异常");
				break;
			}
			return res;
		} catch (Exception e) {
			return new ResponseData(ResponseMsg.FAILED.getCode(), "系统异常");
		}
	}

	/**
	 * @Title checkName
	 * @author :Stephen
	 * @Description 新增用户时，昵称是否唯一
	 * @date 2019年1月11日 下午4:15:41
	 * @param name 昵称
	 * @return ResponseData
	 */
	private ResponseData checkName(String name) {
		List<User> list = userRepository.checkName(name);
		return list.size() == 0 ? new ResponseData(ResponseMsg.SUCCESS)
				: new ResponseData(ResponseMsg.FAILED.getCode(), "该名称已被使用");
	}

	/**
	 * @Title checkName
	 * @author :Stephen
	 * @Description 编辑用户时，昵称是否唯一
	 * @date 2019年1月11日 下午4:15:38
	 * @param name 昵称
	 * @param id   用户id
	 * @return ResponseData
	 */
	private ResponseData checkName(String name, String id) {
		List<User> list = userRepository.checkName(name, id);
		return list.size() == 0 ? new ResponseData(ResponseMsg.SUCCESS)
				: new ResponseData(ResponseMsg.FAILED.getCode(), "该名称已被使用");
	}

	/**
	 * @Title checkAccount
	 * @author :Stephen
	 * @Description 新增用户时，账号是否唯一
	 * @date 2019年1月11日 下午4:15:36
	 * @param account 账号
	 * @return ResponseData
	 */
	private ResponseData checkAccount(String account) {
		List<User> list = userRepository.checkAccount(account);
		return list.size() == 0 ? new ResponseData(ResponseMsg.SUCCESS)
				: new ResponseData(ResponseMsg.FAILED.getCode(), "该账号已被使用");
	}

	/**
	 * @Title checkAccount
	 * @author :Stephen
	 * @Description 编辑用户时，账号是否唯一
	 * @date 2019年1月11日 下午4:15:34
	 * @param account 账号
	 * @param id      用户id
	 * @return ResponseData
	 */
	private ResponseData checkAccount(String account, String id) {
		List<User> list = userRepository.checkAccount(account, id);
		return list.size() == 0 ? new ResponseData(ResponseMsg.SUCCESS)
				: new ResponseData(ResponseMsg.FAILED.getCode(), "该账号已被使用");
	}

	/**
	 * @Title checkEmail
	 * @author :Stephen
	 * @Description 新增用户时，邮箱是否唯一
	 * @date 2019年1月11日 下午4:15:31
	 * @param email 邮箱
	 * @return ResponseData
	 */
	private ResponseData checkEmail(String email) {
		List<User> list = userRepository.checkEmail(email);
		return list.size() == 0 ? new ResponseData(ResponseMsg.SUCCESS)
				: new ResponseData(ResponseMsg.FAILED.getCode(), "该邮箱已被使用");
	}

	/**
	 * @Title checkEmail
	 * @author :Stephen
	 * @Description 编辑用户时，邮箱是否唯一
	 * @date 2019年1月11日 下午4:15:22
	 * @param email 邮箱
	 * @param id    用户id
	 * @return ResponseData
	 */
	private ResponseData checkEmail(String email, String id) {
		List<User> list = userRepository.checkEmail(email, id);
		return list.size() == 0 ? new ResponseData(ResponseMsg.SUCCESS)
				: new ResponseData(ResponseMsg.FAILED.getCode(), "该邮箱已被使用");
	}

	/**
	 * @Title updateRole
	 * @author :Stephen
	 * @Description
	 * @date 2019年1月18日 下午4:57:29
	 * @param user
	 * @return
	 */
	@Override
	public ResponseData updateRole(User user) {
		try {
			User userDo = userRepository.findByUserAccount(user.getAccount());
			if (null == userDo) {
				return new ResponseData(ResponseMsg.FAILED.getCode(), "该用户已不存在");
			}
			userDo.setUpdateTime(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS));
			userDo.setRoleIds(user.getRoleIds());
			User userVo = userRepository.save(userDo);
			logger.info(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "更新用户角色信息成功，账号为：" + user.getAccount());
			return new ResponseData(ResponseMsg.SUCCESS, userVo);
		} catch (Exception e) {
			logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "更新用户角色时错误，用户账号：" + user.getAccount()
					+ "错误信息：" + e.getMessage());
			return new ResponseData(ResponseMsg.FAILED.getCode(), "系统异常");
		}
	}

	/**
	 * @Title uploadIcon
	 * @author :Stephen
	 * @Description
	 * @date 2019年1月24日 上午11:32:39
	 * @param account
	 * @return
	 */
	@Override
	public ResponseData uploadIcon(HttpServletRequest request, String account, String fileBathPath) {
		File fileBak = null;// 之前的头像在删除前备份
		User userDo = userRepository.findByUserAccount(account);
		if (null == userDo) {
			logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "使用账号：" + account + "修改头像时，找不到账号");
			return new ResponseData(ResponseMsg.FAILED.getCode(), "账号错误！");
		}
		// 旧头像备份
		String filePath = fileBathPath + userDo.getId() + ".jpg";
		try {
			File fileOld = new File(filePath);
			fileBak = new File(fileBathPath + userDo.getId() + "_bak.jpg");
			if (null != fileOld && fileOld.exists()) {
				fileOld.renameTo(fileBak);
			}
			List<MultipartFile> files = getFiles(request);
			if (files.size() == 0) {
				return new ResponseData(ResponseMsg.FAILED.getCode(), "请上传文件");
			}
			if (files.size() > 1) {
				return new ResponseData(ResponseMsg.FAILED.getCode(), "只能上传一个头像");
			}
			File file = new File(filePath);
			MultipartFile multipartFile = files.get(0);
			InputStream is = multipartFile.getInputStream();
			OutputStream outStream = new FileOutputStream(file);
			byte[] buffer = new byte[8 * 1024];
			int bytesRead;
			while ((bytesRead = is.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			fileBak.delete();
			return new ResponseData(ResponseMsg.SUCCESS);
		} catch (FileNotFoundException e) {
			logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "使用账号：" + account + "修改头像时，找不到文件");
			File file = new File(filePath);
			if (null != fileBak && fileBak.exists()) {
				fileBak.renameTo(file);
			}
			return new ResponseData(ResponseMsg.FAILED.getCode(), "找不到文件异常");
		} catch (IOException e) {
			logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "使用账号：" + account + "修改头像时，文件流读写");
			File file = new File(filePath);
			if (null != fileBak && fileBak.exists()) {
				fileBak.renameTo(file);
			}
			return new ResponseData(ResponseMsg.FAILED.getCode(), "读写异常");
		} catch (Exception e) {
			logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "使用账号：" + account + "修改头像时，系统异常");
			File file = new File(filePath);
			if (null != fileBak && fileBak.exists()) {
				fileBak.renameTo(file);
			}
			return new ResponseData(ResponseMsg.FAILED.getCode(), "系统异常");
		}
	}

	private List<MultipartFile> getFiles(HttpServletRequest request) {
		List<MultipartFile> files = new ArrayList<MultipartFile>();
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		Iterator<String> iter = multiRequest.getFileNames();
		while (iter.hasNext()) {
			// 取得上传文件
			MultipartFile file = multiRequest.getFile(iter.next());
			files.add(file);
		}
		return files;
	}

	/**
	 * @Title queryIcon
	 * @author :Stephen
	 * @Description
	 * @date 2019年1月24日 上午11:49:03
	 * @param account
	 * @param fileBathPath
	 * @return
	 */
	@Override
	public byte[] queryIcon(String account, String fileBathPath) {
		byte[] buffer = null;
		try {
			User userDo = userRepository.findByUserAccount(account);
			String userId = userDo.getId();// 文件名与用户id一致
			String filePath = fileBathPath + userId + ".jpg";
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
			return buffer;
		} catch (FileNotFoundException e) {
			logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "获取当前登录用户头像文件流时未找到对应头像");
			return buffer;
		} catch (IOException e) {
			logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "获取当前登录用户头像文件流读写异常");
			return buffer;
		} catch (Exception e) {
			logger.error(DateUtils.nowDate(DateUtils.YYYY_MM_DD_HHMMSS) + "获取当前登录用户头像文件流系统异常");
			return buffer;
		}
	}

	/**
	 * @Title logout
	 * @author :Stephen
	 * @Description
	 * @date 2019年1月24日 下午3:32:19
	 * @param account
	 * @return
	 */
	@Override
	public ResponseData logout(String account, HttpServletResponse res, HttpServletRequest req) {
		try {
			CookieUtil.removeCookie(res, CommonConst.COOKIE_KEY_JWT_TOKEN);
			return new ResponseData(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			return new ResponseData(ResponseMsg.FAILED);
		}
	}

}
