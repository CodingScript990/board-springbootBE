package com.codescript.springboard.config;

import com.codescript.springboard.filter.JwtAuthencationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // 해당 클래스를 Spring Context 의 구성 클래스로 표시해준다는 의미임
@EnableWebSecurity // Spring Security 의 WebSecurityConfigurerAdapter Class 를 확장하는 Class 를 표시하기 위함임, Spring Security 의 Web 보안을 구성하는 기본 Class 임
public class WebSecurityConfig {
    // Jwt 의존성 주입
    @Autowired
    JwtAuthencationFilter jwtAuthencationFilter;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // cors 정책 (현재 Application 에서 작업을 해뒀으므로 기본 설정을 사용한다는 의미)
                .cors().and()
                // csrf 대책 (현재는 csrf 에 대한 대책을 비활성화 한다는 의미)
                .csrf().disable()
                // Basic 인증 (현재 Bearer token 인증방법을 사용하기 때문에 비활성화 한다는 의미)
                .httpBasic().disable()
                // Session 기반 인증 (현재 Session 기반 인증을 사용하지 않기 때문에 상태를 없앤다는 의미)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // '/', '/api/auth' 모듈에 대해서는 모두 허용 (인증을 하지 않고 사용 가능하게 만든다는 의미)
                .authorizeHttpRequests().requestMatchers("/", "/api/auth/**").permitAll()
                // 나머지 Request 에 대해서는 모두 인증된 사용자만 사용가능하게 만든다는 의미
                .anyRequest().authenticated();

        httpSecurity.addFilterBefore(jwtAuthencationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
