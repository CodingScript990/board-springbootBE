package com.codescript.springboard.dto;

import com.codescript.springboard.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponseDto {
		// Member Field
		private String token;
		private int exprTime;
		private UserEntity user;
}
