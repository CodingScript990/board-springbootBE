package com.codescript.springboard.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenProvider {
		// member field => jwt key setting
		// JWT 생성 및 검증을 위한 키 Setting
		private static final String SECURITY_KEY = "jwtseckey!@";

		// jwt method add => email[사용시간, 만료되면 회수]
		public String create(String userEmail) {
				// 만료날짜를 현재 날짜 + 1시간으로 설정한 것
				Date exprTime = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

				// JWT 를 생성 해줌
				// signWith : 암호화에 사용될 알고리즘과 키를 설정
				// setSubject : JWT 제목, 생성일, 만료일을 기입함
				// compact : 생성을 해줌
				return Jwts.builder()
								.signWith(SignatureAlgorithm.HS512, SECURITY_KEY)
								.setSubject(userEmail).setIssuedAt(new Date()).setExpiration(exprTime)
								.compact();
		}

		// 복호화 하는 작업 => validate method add
		// JWT 를 검증하는 것
		public String validate (String token) {
				// 매개변수로 받은 token 을 키를 사용해서 복호화 해주는 작업(디코딩)
				Claims claims = Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(token).getBody();

				// claims 에 지정해준 security key 를 파싱한 값을 반환시켜줌
				// - 복호화된 token 의 payload 에서 제목을 가져온다는 의미
				return claims.getSubject(); // userEmail 을 받아온다는 소리임
		}
}
