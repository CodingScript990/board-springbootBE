package com.codescript.springboard.controller;

import com.codescript.springboard.dto.ResponseDto;
import com.codescript.springboard.dto.SignUpDto;
import com.codescript.springboard.dto.SignUpResponseDto;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(originPatterns = "http://localhost:3000") // 3000 port 통신을 허용하겠다는 의미
@RestController
@RequestMapping("/api/auth") // Domain => /api/auth
public class AuthController {

		// ResponseDto type signUp function
		@PostMapping("/signUp") // post type[Domain(set)] => "signUp"
		public ResponseDto<SignUpResponseDto> signUp(@RequestBody SignUpDto requestBody) {
				// test message
				System.out.println("requestBody : " + requestBody.toString());

				// return value;
				return null;
		}
}
