/**  
* @描述 
* @文件名:MVCConfiguration.java
* @版权:Copyright 2018 版权所有：平头哥
* @描述:MVCConfiguration.java
* @修改人:Stephen
* @修改时间:2018年12月24日 下午5:29:22
* @修改内容:新增
*/
package com.ratel.system;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ratel.handlerInterceptor.RequestInterceptor;

/**
 * @文件名:MVCConfiguration.java
 * @版权:Copyright 2018 版权所有：平头哥
 * @描述:配置默认欢迎页面
 * @修改人:Stephen
 * @修改时间:2018年12月24日 下午5:29:22
 * @修改内容:新增
 */
@Configuration
public class MVCConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:login.html");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		super.addViewControllers(registry);
	}

	/**
	 * @Title addInterceptors
	 * @author :Stephen
	 * @Description
	 * @date 2019年1月2日 下午3:36:10
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 这里可以添加多个拦截器
		registry.addInterceptor(new RequestInterceptor()).addPathPatterns("/**").excludePathPatterns("/ratel/login",
				"/users/login", "/users/logout");
		super.addInterceptors(registry);
	}

}
