/**  
* @描述 
* @文件名:LoginInterceptor.java
* @版权:Copyright 2018 版权所有：平头哥
* @描述:LoginInterceptor.java
* @修改人:Stephen
* @修改时间:2019年1月2日 下午3:32:14
* @修改内容:新增
*/
package com.ratel.handlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ratel.common.constant.CommonConst;
import com.ratel.common.response.ResponseData;
import com.ratel.common.utils.CookieUtil;
import com.ratel.common.utils.StringUtil;
import com.ratel.common.utils.TokenUtil;

/**
 * @文件名:LoginInterceptor.java
 * @版权:Copyright 2018 版权所有：平头哥
 * @描述:项目请求拦截器，除了登陆界面和登陆请求外，其他都拦截，需要验证是否有jwt令牌
 * @修改人:Stephen
 * @修改时间:2019年1月2日 下午3:32:14
 * @修改内容:新增
 */
public class RequestInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

	@Autowired
	private TokenUtil tokenUtil;

	/**
	 * @Title preHandle
	 * @author :Stephen
	 * @Description
	 * @date 2019年1月2日 下午3:33:10
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		ResponseData rsp = new ResponseData();
		String token = CookieUtil.getCookie(request, CommonConst.COOKIE_KEY_JWT_TOKEN);
		token = StringUtil.isBlank(token) ? request.getHeader(CommonConst.COOKIE_KEY_JWT_TOKEN) : token;
		if (StringUtil.isEmpty(token)) {
			// 当jwt令牌为空时，跳转至登陆界面
			rsp.setRspMsg("登录已过期！");
			rsp.setRspCode("401");
			sendRedirect(request, response, rsp);
			return false;
		} else {
			// jwt令牌存在，刷新jwt时间
			// tokenUtil.refreshToken(token);
			return super.preHandle(request, response, handler);
		}

	}

	private void sendRedirect(HttpServletRequest req, HttpServletResponse response, ResponseData rsp) {
		String contentType = req.getContentType();
		contentType = StringUtil.isNotBlank(contentType) ? req.getContentType().replaceAll(" ", "") : "";
		try {
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<script type=\"text/javascript\">");
			out.println("window.open ('/ratel/login','_top')");
			out.println("</script>");
			out.println("</html>");
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
