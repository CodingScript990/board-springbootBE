package com.codescript.springboard.service;

import com.codescript.springboard.dto.ResponseDto;
import com.codescript.springboard.dto.SignUpDto;
import com.codescript.springboard.entity.UserEntity;
import com.codescript.springboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // Service 를 사용하겠다는 의미
public class AuthService {

		//
		@Autowired
		UserRepository userRepository;

		// Dto 에서 password, passwordCheck
		public ResponseDto<?> signUp(SignUpDto dto) {
				String userEmail = dto.getUserEmail();
				String userPassword = dto.getUserPassword();
				String userPasswordCheck = dto.getUserPasswordCheck();

				// try - catch 문으로 예외처리
				try {
						// Email 중복 확인
						if(userRepository.existsById(userEmail))
								return ResponseDto.setFailed("Existed Email!");
				} catch (Exception e) {
						// DB Error
						return ResponseDto.setFailed("DB Error!");
				}

				// userPassword != userPasswordCheck 이면, fail message
				if (!userPassword.equals(userPasswordCheck))
						return ResponseDto.setFailed("Password don't matched!");

				// UserEntity 생성
				UserEntity userEntity = new UserEntity(dto);

				// try - catch 예외처리
				try {
						// UserRepository 를 이용해서 데이터베이스에 Entity 를 저장해줌
						userRepository.save(userEntity);
				} catch (Exception e) {
						return ResponseDto.setFailed("DB Error!");
				}

				// 성공시 success response 반환
				return ResponseDto.setSuccess("SignUp Success!", null);
		}
}
