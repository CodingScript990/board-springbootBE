package com.codescript.springboard.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(originPatterns = "http://localhost:3000") // 3000 port 통신을 허용하겠다는 의미
@RestController // 해당 클래스를 Rest 형태 Controller 레이어로 인식하도록 함[@Controller + @ResponseBody]
@RequestMapping("/") // Request 의 URL 의 패턴을 보고 해당하는 패턴이 왔을 때 해당 클래스를 실행함
public class MainController {
		// Get method add
		@GetMapping("")
		public String hello() {
				// 성공시 메세지 => View
				return "Connection Successful!";
		}
}
