package com.ratel.auth.utils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ratel.auth.domain.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.sf.json.JSONObject;

/**
 * Token工具类 <一句话功能简述>
 * 
 * @author lzc
 * @version V1.0,2017年6月14日 下午3:27:33
 * @see [相关类/方法]
 * @since V1.0
 *
 */
@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = -3301605591108950415L;

	private static final String CLAIM_KEY_USERNAME = "sub";
	private static final String CLAIM_KEY_CREATED = "created";
	private static final String CLAIM_KEY_USER = "userVo";

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	/**
	 * 
	 * @Description:将用户信息转换为token
	 *
	 * @author wenzhang
	 * @date:2018年2月9日 上午9:08:57
	 * @Title:generateToken
	 * @param userDetails 用户对象
	 * @return token token
	 * @since JDK 1.8
	 */
	public String generateToken(User userDetails) {
		userDetails.setPassword("");
		JSONObject jsonObject = JSONObject.fromObject(userDetails);
		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIM_KEY_USER, jsonObject.toString());
		claims.put(CLAIM_KEY_USERNAME, userDetails.getName());
		claims.put(CLAIM_KEY_CREATED, new Date());
		return generateToken(claims);
	}

	String generateToken(Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate())
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	private Date generateExpirationDate() {
		return new Date(System.currentTimeMillis() + expiration * 1000);
	}

}
