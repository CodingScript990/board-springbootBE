package com.codescript.springboard.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInDto {
		// Member Field
		@NotBlank // NotBlank : 필수로 값을 주도록 만듬!
		private String userEmail;
		@NotBlank
		private String userPassword;
}
