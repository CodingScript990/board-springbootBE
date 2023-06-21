package com.codescript.springboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class SpringboardApplication {

		public static void main(String[] args) {
			SpringApplication.run(SpringboardApplication.class, args);
		}

		// Bean => FE 와의 HTTP 통신이 원활하도록 하기 위함
		@Bean
		public WebMvcConfigurer corsConfigurer() {
				// return 생성자(WebMvcConfigurer)
				return new WebMvcConfigurer() {
						// addCorsMappings function add
						@Override
						public void addCorsMappings(CorsRegistry registry) {
								// addMapping : 모든 경로를 불러옴
								// allowedOriginPatterns : 허용을 해준다는 의미
								registry.addMapping("/**").allowedOriginPatterns();
						}
				};
		}

}
