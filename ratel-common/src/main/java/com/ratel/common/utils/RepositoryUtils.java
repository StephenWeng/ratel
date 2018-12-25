package com.ratel.common.utils;

public abstract class RepositoryUtils {
	/**
	 * @Title getPropertyWildcardValue
	 * @author Mr.Wang
	 * @Description: 前后通配
	 * @date 2017年5月4日
	 * @param property
	 * @return
	 */
	public static String getPropertyWildcardValue(String property) {
		return "%" + property + "%";
	}

	/**
	 * @Title getPropertyFrontMatchValue
	 * @author Mr.Wang
	 * @Description: 前匹配
	 * @date 2017年5月4日
	 * @param property
	 * @return
	 */
	public static String getPropertyFrontMatchValue(String property) {
		return "%" + property;
	}

	/**
	 * @Title getPropertyBehandMatchValue
	 * @author Mr.Wang
	 * @Description: 后匹配
	 * @date 2017年5月4日
	 * @param property
	 * @return
	 */
	public static String getPropertyBehandMatchValue(String property) {
		return property + "%";
	}
}
