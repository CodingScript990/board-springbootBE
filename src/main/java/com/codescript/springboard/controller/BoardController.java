package com.codescript.springboard.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/board")
public class BoardController {

    // getBoard method
    @GetMapping
    public String getBoard(@AuthenticationPrincipal String userEmail) {
        // @AuthenticationPrincipal : 매개변수를 인증된 사용자의 인증 주체로 바인딩 한다는 의미임,
        // 즉, 인증주체는 사용자의 이름, 권한, 그룹 등과 같은 사용자 정보를 포함하는 객체를 말함
        return "Login User : " + userEmail;
    }
}
