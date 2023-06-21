package com.codescript.springboard.filter;

import com.codescript.springboard.security.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthencationFilter extends OncePerRequestFilter {

    // Request 가 들어왔을 때 Request Header 의 Authorization 필드의 Bearer Token 을 가져옴
    // 가져온 Token 을 검증하고 결과를 SecurityContext 에 추가함

    @Autowired private TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {

        // 예외처리
        try {
            String token = parseBearerToken(req);

            // equalsIgnoreCase : 대소문자 구분없이 비교를 하겠다는 의미
            if (token != null && !token.equalsIgnoreCase("null")) {
                // Token 검증해서 payload 의 userEmail 을 가져온다는 의미
                String userEmail = tokenProvider.validate(token);

                // SecurityContext 에 추가할 객체
                AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userEmail, null, AuthorityUtils.NO_AUTHORITIES);
                // SecurityContext 의 객체를 사용하여 details 속성에서 인증 요청에 대한 추가 정보를 저장할 것인데, 현재 웹 요청에 대한 세부 정보를 추출하는 class 를 이용하여 buildDetails 메서드는 req 에 대한 세부 정보를 요청한 것에 대한 사용자 인증여부를 묻는다는 의미임
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));

                // SecurityContext 에 AbstractAuthenticationToken 객체를 추가해서 해당 Thread 가 지속적으로 인증 정보를 가질 수 있도록 해줌
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                securityContext.setAuthentication(authenticationToken);
                SecurityContextHolder.setContext(securityContext);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        filterChain.doFilter(req, res);
    }

    // Request Header 의 Authorization Field 의 Bearer Token 을 가져오는 method
    private String parseBearerToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");

        // hasText : StringUtils 의 text 값이 null, false, 빈값 등이 오면 false 로 처리를 해준다는 의미
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            // Bearer 다음 자릿수의 값을 반환하겠다는 의미임
            return bearerToken.substring(7);
        }
        return null;
    }
}
