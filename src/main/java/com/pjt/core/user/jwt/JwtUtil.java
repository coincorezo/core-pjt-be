package com.pjt.core.user.jwt;

import com.pjt.core.common.error.response.ErrorCode;
import com.pjt.core.user.dto.CreateUserToken;
import com.pjt.core.user.exception.UserException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * JWT 관련 메서드를 제공하는 클래스
 */
@Slf4j
@Component
public class JwtUtil {
	private final Key key;
	private final long accessTokenExpTime;

	public JwtUtil(
			@Value("${jwt.secret}") String secretKey,
			@Value("${jwt.expiration_time}") long accessTokenExpTime
	) {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		this.key = Keys.hmacShaKeyFor(keyBytes);
		this.accessTokenExpTime = accessTokenExpTime;
	}

	/**
	 * Access Token 생성
	 * @param member 사용자 정보
	 * @return Access Token
	 */
	public String createAccessToken(CreateUserToken member) {
		return createToken(member, accessTokenExpTime);
	}

	/**
	 * JWT 생성
	 * @param member 사용자 정보
	 * @param expireTime 만료 시간
	 * @return JWT
	 */
	private String createToken(CreateUserToken member, long expireTime) {
		Claims claims = Jwts.claims();
		claims.put("memberId", member.getId());
		claims.put("email", member.getEmail());
		claims.put("role", member.getUserLevel());
		ZonedDateTime now = ZonedDateTime.now();
		ZonedDateTime tokenValidity = now.plusSeconds(expireTime);
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(Date.from(now.toInstant()))
				.setExpiration(Date.from(tokenValidity.toInstant()))
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
	}

	/**
	 * 토큰에서 사용자 ID 추출
	 * @param token JWT
	 * @return 사용자 ID
	 */
	public String getUserId(String token) {
		return parseClaims(token).get("memberId", String.class);
	}

	/**
	 * 토큰 유효성 검사
	 * @param token JWT
	 * @return 유효성 여부
	 */
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (SecurityException | MalformedJwtException e) {
			log.info("Invalid JWT Token", e);
		} catch (ExpiredJwtException e) {
			log.info("Expired JWT Token", e);
		} catch (UnsupportedJwtException e) {
			log.info("Unsupported JWT Token", e);
		} catch (IllegalArgumentException e) {
			log.info("JWT claims string is empty.", e);
		}
		return false;
	}

	/**
	 * JWT Claims 추출
	 * @param accessToken JWT
	 * @return JWT Claims
	 */
	public Claims parseClaims(String accessToken) {
		try {
			return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
		} catch (ExpiredJwtException e) {
			return e.getClaims();
		}
	}

	public String getAccessToken(HttpServletRequest request) {
		String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (authorization == null || !authorization.startsWith("Bearer ")) {
			throw new UserException(ErrorCode.NO_TOKEN);
		}

		return getAccessToken(authorization);
	}

	public String getAccessToken(String authorization) {
		return authorization.substring(7);
	}

}

