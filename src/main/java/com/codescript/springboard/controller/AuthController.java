package com.codescript.springboard.controller;

import com.codescript.springboard.dto.ResponseDto;
import com.codescript.springboard.dto.SignUpDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth") // Domain => /api/auth
public class AuthController {

		// ResponseDto type signUp function
		@PostMapping("/signUp") // post type[Domain(set)] => "signUp"
		public ResponseDto<?> signUp(@RequestBody SignUpDto requestBody) {
				// test message
				System.out.println("requestBody : " + requestBody.toString());

				// return value;
				return null;
		}
}
