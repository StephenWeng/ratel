package com.ratel.auth.service.impl;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ratel.auth.domain.Email;
import com.ratel.auth.domain.User;
import com.ratel.auth.repository.UserRepository;
import com.ratel.auth.service.IEmailService;
import com.ratel.auth.service.IUserService;
import com.ratel.auth.utils.JwtTokenUtil;
import com.ratel.common.constant.CommonConst;
import com.ratel.common.domain.Encrypt;
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

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final static int MAX_SECURITY_CODE_INDATE = 3;// 重置密码验证码最大有效期3小时

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private IEmailService emailService;

	@Value("${jwt.expiration}")
	private int expiration;// cookie存在时长

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	/**
	 * @Title test
	 * @author :Stephen
	 * @Description
	 * @date 2018年12月13日 下午4:37:36
	 * @return
	 */
	@Override
	public ResponseData test() {
		return null;
	}

	/**
	 * @Title page
	 * @author :Stephen
	 * @Description
	 * @date 2018年12月13日 下午4:37:36
	 * @param current
	 * @param pageSize
	 * @return
	 */
	@Override
	public ResponseData page(Integer current, Integer pageSize) {
		// Pageable pageable = new PageRequest(current, pageSize);
		// UserSpecification pcs = new UserSpecification(new User());
		// Page page = userRepository.findAll(pcs, pageable);
		// return new ResponseData(ResponseMsg.SUCCESS, page);
		User user = new User();
		user.setAccount("1");
		user.setName("撒打算");
		user.setPassword("12312");
		user.setSalt("dasdsa");
		user.setGender(0);
		user.setAge(21);
		userRepository.save(user);
		return new ResponseData(ResponseMsg.SUCCESS);
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
		account = email2Lower(account);
		try {
			User userDo = userRepository.findByUserAccount(account);
			return null == userDo ? new ResponseData(ResponseMsg.FAILED.getCode(), "用户不存在！")
					: new ResponseData(ResponseMsg.SUCCESS);
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
			String email = email2Lower(user.getEmail());
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

	@Transactional
	@Override
	public ResponseData sendSecurityCode(User user) {
		try {
			// 邮箱后半段转小写
			String email = email2Lower(user.getEmail());
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
			String email = email2Lower(user.getEmail());
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
			userDo.setSalt(encrypt.getSalt());
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

	/**
	 * 邮箱后缀统一转小写
	 */
	private String email2Lower(String email) {
		if (!StringUtil.isEmpty(email)) {
			String[] emailArr = email.split("@");
			return emailArr[0] + "@" + emailArr[1].toLowerCase();
		}
		return null;
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
			String account = "stephen";
			User userDo = userRepository.findByUserAccount(account);
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

}
