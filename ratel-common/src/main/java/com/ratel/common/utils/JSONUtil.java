package com.ratel.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * redis配套的一些json操作
 * 
 * @author lzc
 * @version V1.0,2017年6月14日 下午3:19:57
 * @see [相关类/方法]
 * @since V1.0
 *
 */
public class JSONUtil {

	private static Gson gson = null;

	static {
		gson = new Gson();// todo yyyy-MM-dd HH:mm:ss
	}

	/**
	 * @author wenzhang
	 * @date:2018年2月9日 上午9:02:11
	 * @Title:newInstance
	 * @return
	 * @since JDK 1.8
	 */
	public static synchronized Gson newInstance() {
		if (gson == null) {
			gson = new Gson();
		}
		return gson;
	}

	/**
	 * 
	 * @Description:对象转json
	 *
	 * @author wenzhang
	 * @date:2018年2月9日 上午9:02:23
	 * @Title:toJson
	 * @param obj 对象
	 * @return json字符串
	 * @since JDK 1.8
	 */
	public static String toJson(Object obj) {
		return gson.toJson(obj);
	}

	/**
	 * 
	 * @Description:json转bean.
	 *
	 * @author wenzhang
	 * @date:2018年2月9日 上午9:03:18
	 * @Title:toBean
	 * @param json json字符串
	 * @param clz  泛型类
	 * @return 指定对象
	 * @since JDK 1.8
	 */
	public static <T> T toBean(String json, Class<T> clz) {

		return gson.fromJson(json, clz);
	}

	/**
	 * 
	 * @Description:json转map
	 *
	 * @author wenzhang
	 * @date:2018年2月9日 上午9:04:23
	 * @Title:toMap
	 * @param json json字符串
	 * @param clz  泛型类
	 * @return Map<json字符串，泛型对象>
	 * @since JDK 1.8
	 */
	public static <T> Map<String, T> toMap(String json, Class<T> clz) {
		Map<String, JsonObject> map = gson.fromJson(json, new TypeToken<Map<String, JsonObject>>() {
		}.getType());
		Map<String, T> result = new HashMap<String, T>();
		for (String key : map.keySet()) {
			result.put(key, gson.fromJson(map.get(key), clz));
		}
		return result;
	}

	/**
	 * 
	 * @Description:json转map，单个属性作为key-value
	 *
	 * @author wenzhang
	 * @date:2018年2月9日 上午9:05:51
	 * @Title:toMap
	 * @param json json字符串
	 * @return Map<属性，值>
	 * @since JDK 1.8
	 */
	public static Map<String, Object> toMap(String json) {
		Map<String, Object> map = gson.fromJson(json, new TypeToken<Map<String, Object>>() {
		}.getType());
		return map;
	}

	/**
	 * 
	 * @Description:json转泛型集合.
	 *
	 * @author wenzhang
	 * @date:2018年2月9日 上午9:06:38
	 * @Title:toList
	 * @param json json字符串
	 * @param clz  泛型类
	 * @return 泛型对象集合
	 * @since JDK 1.8
	 */
	public static <T> List<T> toList(String json, Class<T> clz) {
		JsonArray array = new JsonParser().parse(json).getAsJsonArray();
		List<T> list = new ArrayList<T>();
		for (final JsonElement elem : array) {
			list.add(gson.fromJson(elem, clz));
		}
		return list;
	}

}
