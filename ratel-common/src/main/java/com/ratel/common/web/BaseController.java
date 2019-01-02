package com.ratel.common.web;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ratel.common.constant.CommonConst;
import com.ratel.common.domain.User;
import com.ratel.common.response.Response;
import com.ratel.common.response.ResponseMsg;
import com.ratel.common.utils.CookieUtil;
import com.ratel.common.utils.StringUtil;
import com.ratel.common.utils.TokenUtil;

@Controller
public class BaseController {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	private static ThreadLocal<ServletRequest> currentRequest = new ThreadLocal<ServletRequest>();

	private static ThreadLocal<ServletResponse> currentResponse = new ThreadLocal<ServletResponse>();

	@Autowired
	private TokenUtil tokenUtil;

	@Value("${spring.application.name}")
	protected String appName;

	/**
	 * @author wenzhang
	 * @date:2018年2月9日 上午11:07:35
	 * @Title:initReqAndRep
	 * @param request
	 * @param response
	 * @since JDK 1.8
	 */
	@ModelAttribute
	public void initReqAndRep(HttpServletRequest request, HttpServletResponse response) {
		currentRequest.set(request);
		currentResponse.set(response);
	}

	protected Response result(ResponseMsg msg) {
		return new Response(msg);
	}

	protected Response result() {
		return new Response();
	}

	/**
	 * 
	 * @Description:公共方法，获取request
	 *
	 * @author wenzhang
	 * @date:2018年2月9日 上午11:07:13
	 * @Title:request
	 * @return HttpServletRequest
	 * @since JDK 1.8
	 */
	public HttpServletRequest request() {
		return (HttpServletRequest) currentRequest.get();
	}

	/**
	 * 
	 * @Description:公共方法，获取response
	 *
	 * @author wenzhang
	 * @date:2018年2月9日 上午11:06:56
	 * @Title:response
	 * @return HttpServletResponse
	 * @since JDK 1.8
	 */
	public HttpServletResponse response() {
		return (HttpServletResponse) currentResponse.get();
	}

	protected String getUserIp() {
		String value = request().getHeader("x-forwarded-for");
		if (StringUtil.isNotBlank(value) && !"unknown".equalsIgnoreCase(value)) {
			return value;
		} else {
			return request().getRemoteAddr();
		}
	}

	protected User getActiveUser() {
		HttpServletRequest req = request();
		String authToken = CookieUtil.getCookie(req, CommonConst.COOKIE_KEY_JWT_TOKEN);
		authToken = StringUtil.isBlank(authToken) ? req.getHeader(CommonConst.COOKIE_KEY_JWT_TOKEN) : authToken;
		User user = tokenUtil.getUserFromToken(authToken);
		return user;
	}

	protected String getUserAccount() {
		HttpServletRequest req = request();
		String authToken = CookieUtil.getCookie(req, CommonConst.COOKIE_KEY_JWT_TOKEN);
		authToken = StringUtil.isBlank(authToken) ? req.getHeader(CommonConst.COOKIE_KEY_JWT_TOKEN) : authToken;
		String username = tokenUtil.getUsernameFromToken(authToken);
		return username;
	}
}
