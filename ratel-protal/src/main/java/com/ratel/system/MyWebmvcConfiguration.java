/**  
* @描述 
* @文件名:MyWebmvcConfiguration.java
* @版权:Copyright 2018 版权所有：平头哥
* @描述:MyWebmvcConfiguration.java
* @修改人:Stephen
* @修改时间:2018年12月20日 下午5:25:54
* @修改内容:新增
*/
package com.ratel.system;

import java.util.List;

import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

/**  
* @描述 
* @文件名:MyWebmvcConfiguration.java
* @版权:Copyright 2018 版权所有：平头哥 
* @描述:MyWebmvcConfiguration.java
* @修改人:Stephen
* @修改时间:2018年12月20日 下午5:25:54
* @修改内容:新增
*/
/**
 * @className MyWebmvcConfiguration
 * @author :Stephen
 * @Description
 * @date 2018年12月20日 下午5:25:54
 */
public class MyWebmvcConfiguration implements WebMvcConfigurer {

	/**
	 * @Title configurePathMatch
	 * @author :Stephen
	 * @Description
	 * @date 2018年12月20日 下午5:26:17
	 * @param configurer
	 */
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		// TODO Auto-generated method stub

	}

	/**
	 * @Title configureContentNegotiation
	 * @author :Stephen
	 * @Description
	 * @date 2018年12月20日 下午5:26:17
	 * @param configurer
	 */
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		// TODO Auto-generated method stub

	}

	/**
	 * @Title configureAsyncSupport
	 * @author :Stephen
	 * @Description
	 * @date 2018年12月20日 下午5:26:17
	 * @param configurer
	 */
	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		// TODO Auto-generated method stub

	}

	/**
	 * @Title configureDefaultServletHandling
	 * @author :Stephen
	 * @Description
	 * @date 2018年12月20日 下午5:26:17
	 * @param configurer
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// TODO Auto-generated method stub

	}

	/**
	 * @Title addFormatters
	 * @author :Stephen
	 * @Description
	 * @date 2018年12月20日 下午5:26:17
	 * @param registry
	 */
	@Override
	public void addFormatters(FormatterRegistry registry) {
		// TODO Auto-generated method stub

	}

	/**
	 * @Title addInterceptors
	 * @author :Stephen
	 * @Description
	 * @date 2018年12月20日 下午5:26:17
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub

	}

	/**
	 * @Title addResourceHandlers
	 * @author :Stephen
	 * @Description
	 * @date 2018年12月20日 下午5:26:17
	 * @param registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub

	}

	/**
	 * @Title addCorsMappings
	 * @author :Stephen
	 * @Description
	 * @date 2018年12月20日 下午5:26:17
	 * @param registry
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// TODO Auto-generated method stub

	}

	/**
	 * @Title addViewControllers
	 * @author :Stephen
	 * @Description
	 * @date 2018年12月20日 下午5:26:17
	 * @param registry
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// TODO Auto-generated method stub

	}

	/**
	 * @Title configureViewResolvers
	 * @author :Stephen
	 * @Description
	 * @date 2018年12月20日 下午5:26:17
	 * @param registry
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// TODO Auto-generated method stub

	}

	/**
	 * @Title addArgumentResolvers
	 * @author :Stephen
	 * @Description
	 * @date 2018年12月20日 下午5:26:17
	 * @param argumentResolvers
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		// TODO Auto-generated method stub

	}

	/**
	 * @Title addReturnValueHandlers
	 * @author :Stephen
	 * @Description
	 * @date 2018年12月20日 下午5:26:17
	 * @param returnValueHandlers
	 */
	@Override
	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
		// TODO Auto-generated method stub

	}

	/**
	 * @Title configureMessageConverters
	 * @author :Stephen
	 * @Description
	 * @date 2018年12月20日 下午5:26:17
	 * @param converters
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// TODO Auto-generated method stub

	}

	/**
	 * @Title extendMessageConverters
	 * @author :Stephen
	 * @Description
	 * @date 2018年12月20日 下午5:26:17
	 * @param converters
	 */
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		FastJsonHttpMessageConverter fjc = new FastJsonHttpMessageConverter();
		FastJsonConfig fj = new FastJsonConfig();
		fj.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect);
		fjc.setFastJsonConfig(fj);
		converters.add(fjc);

	}

	/**
	 * @Title configureHandlerExceptionResolvers
	 * @author :Stephen
	 * @Description
	 * @date 2018年12月20日 下午5:26:17
	 * @param exceptionResolvers
	 */
	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		// TODO Auto-generated method stub

	}

	/**
	 * @Title extendHandlerExceptionResolvers
	 * @author :Stephen
	 * @Description
	 * @date 2018年12月20日 下午5:26:17
	 * @param exceptionResolvers
	 */
	@Override
	public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		// TODO Auto-generated method stub

	}

	/**
	 * @Title getValidator
	 * @author :Stephen
	 * @Description
	 * @date 2018年12月20日 下午5:26:17
	 * @return
	 */
	@Override
	public Validator getValidator() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @Title getMessageCodesResolver
	 * @author :Stephen
	 * @Description
	 * @date 2018年12月20日 下午5:26:17
	 * @return
	 */
	@Override
	public MessageCodesResolver getMessageCodesResolver() {
		// TODO Auto-generated method stub
		return null;
	}

}
