package com.codescript.springboard.controller;

import com.codescript.springboard.dto.ResponseDto;
import com.codescript.springboard.dto.SignInResponseDto;
import com.codescript.springboard.dto.SignUpDto;
import com.codescript.springboard.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth") // Domain => /api/auth
public class AuthController {

		// AuthService
		@Autowired
		AuthService authService;

		// ResponseDto type signUp function
		@PostMapping("/signUp") // post type[Domain(set)] => "signUp"
		public ResponseDto<?> signUp(@RequestBody SignUpDto requestBody) {
				// 결과를 반환해주기 위함
				ResponseDto<?> result = authService.signUp(requestBody);
				// return value;
				System.out.println("requestBody = " + requestBody);
				return result;
		}
}
