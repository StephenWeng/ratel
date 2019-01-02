package com.ratel.common.utils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ratel.common.domain.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Jwt Token 操作类
 * 
 * @author lzc
 * @version V1.0,2017年6月14日 下午3:07:21
 * @see [相关类/方法]
 * @since V1.0
 *
 */
@Component
public class TokenUtil implements Serializable {

	private static final long serialVersionUID = -3301605591108950415L;

	private static final String CLAIM_KEY_TENANT = "tenant";
	private static final String CLAIM_KEY_CREATED = "created";
	private static final String CLAIM_KEY_USER = "userVo";

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	/**
	 * 
	 * @Description:从token中获取用户账号.
	 *
	 * @author wenzhang
	 * @date:2018年2月9日 上午9:21:44
	 * @Title:getUsernameFromToken
	 * @param token
	 * @return 用户账号
	 * @since JDK 1.8
	 */
	public String getUsernameFromToken(String token) {
		if (StringUtil.isBlank(token)) {
			return null;
		}
		String username;
		try {
			final Claims claims = getClaimsFromToken(token);
			username = claims.getSubject();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}

	/**
	 * 
	 * @Description:从token中获取用户账号
	 *
	 * @author wenzhang
	 * @date:2018年2月9日 上午9:23:09
	 * @Title:getUserFromToken
	 * @param token
	 * @return 用户账号
	 * @since JDK 1.8
	 */
	public User getUserFromToken(String token) {
		if (StringUtil.isBlank(token)) {
			return null;
		}
		User user = null;
		try {
			final Claims claims = getClaimsFromToken(token);
			String userJson = (String) claims.get(CLAIM_KEY_USER);
			user = JSONUtil.toBean(userJson, User.class);
		} catch (Exception e) {
			user = null;
		}
		return user;
	}

	/**
	 * 
	 * @Description:解析token，获取租户编码
	 *
	 * @author wenzhang
	 * @date:2018年2月9日 上午9:23:33
	 * @Title:getTenantFromToken
	 * @param token
	 * @return 租户编码
	 * @since JDK 1.8
	 */
	public String getTenantFromToken(String token) {
		if (StringUtil.isBlank(token)) {
			return null;
		}
		String tenant;
		try {
			final Claims claims = getClaimsFromToken(token);
			tenant = (String) claims.get(CLAIM_KEY_TENANT);
		} catch (Exception e) {
			tenant = null;
		}
		return tenant;
	}

	/**
	 * 
	 * @Description:解析token，获取结束时间
	 *
	 * @author wenzhang
	 * @date:2018年2月9日 上午9:24:37
	 * @Title:getExpirationDateFromToken
	 * @param token
	 * @return Date
	 * @since JDK 1.8
	 */
	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			final Claims claims = getClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (Exception e) {
			expiration = null;
		}
		return expiration;
	}

	/**
	 * 
	 * @Description:解析token，获取Claims
	 *
	 * @author wenzhang
	 * @date:2018年2月9日 上午9:25:25
	 * @Title:getClaimsFromToken
	 * @param token
	 * @return Claims
	 * @since JDK 1.8
	 */
	public Claims getClaimsFromToken(String token) {
		try {
			Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
			return claims;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @Description:解析token，判断token是否已过期
	 *
	 * @author wenzhang
	 * @date:2018年2月9日 上午9:26:51
	 * @Title:isTokenExpired
	 * @param token
	 * @return boolean 过期：true；未过期：false
	 * @since JDK 1.8
	 */
	public Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		if (expiration != null) {
			return expiration.before(new Date());
		} else {
			return true;
		}
	}

	/**
	 * 
	 * @Description:刷新token，重置token过期时间
	 *
	 * @author wenzhang
	 * @date:2018年2月9日 上午9:28:08
	 * @Title:refreshToken
	 * @param token
	 * @return Map<刷新后的token，过期时间>
	 * @since JDK 1.8
	 */
	public Map<String, Object> refreshToken(String token) {
		if (StringUtil.isBlank(token)) {
			return null;
		}

		String refreshedToken = token;
		Map<String, Object> map;
		try {
			Claims claims = getClaimsFromToken(token);
			if (claims == null) {
				return null;
			}
			Date expirationDate = claims.getExpiration();
			map = new HashMap<String, Object>();
			map.put("userJson", claims.get(CLAIM_KEY_USER));
			if (System.currentTimeMillis() + expiration * 1000 / 2 > expirationDate.getTime()) {
				claims.put(CLAIM_KEY_CREATED, new Date());
				refreshedToken = generateToken(claims);
				map.put("refreshedToken", refreshedToken);
				map.put("expiration", expiration);
			}
			return map;
		} catch (Exception e) {
			// e.printStackTrace();
			return null;
		}

	}

	String generateToken(Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate())
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	private Date generateExpirationDate() {
		return new Date(System.currentTimeMillis() + expiration * 1000);
	}

}
