package com.codescript.springboard.service;

import com.codescript.springboard.dto.ResponseDto;
import com.codescript.springboard.dto.SignInDto;
import com.codescript.springboard.dto.SignInResponseDto;
import com.codescript.springboard.dto.SignUpDto;
import com.codescript.springboard.entity.UserEntity;
import com.codescript.springboard.repository.UserRepository;
import com.codescript.springboard.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // Service 를 사용하겠다는 의미
public class AuthService {

		// UserRepository 사용하겠다는 의미
		@Autowired
		UserRepository userRepository;
		// TokenProvider 사용하겠다는 의미
		@Autowired
		TokenProvider tokenProvider;

		// SignUpDto 에서 password, passwordCheck
		public ResponseDto<?> signUp(SignUpDto dto) {
				// signUp 에서 필요한 email, password, passwordCheck 를 dto 에서 멤버필드의 값을 받아옴
				String userEmail = dto.getUserEmail();
				String userPassword = dto.getUserPassword();
				String userPasswordCheck = dto.getUserPasswordCheck();

				// try - catch 문으로 예외처리
				try {
						// Email 중복 확인
						if(userRepository.existsById(userEmail))
								return ResponseDto.setFailed("Existed Email!");
				} catch (Exception err) {
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
				} catch (Exception err) {
						return ResponseDto.setFailed("DB Error!");
				}

				// 성공시 success response 반환
				return ResponseDto.setSuccess("SignUp Success!", null);
		}

		// SignInDto 에서 userEmail, userPassword
		public ResponseDto<SignInResponseDto> signIn(SignInDto dto) {
				// 로그인시 DB 에 userEmail, userPassword 가 일치한가? Data 가 있는가?를 확인하기 위한 작업!
				String userEmail = dto.getUserEmail();
				String userPassword = dto.getUserPassword();

				// try - catch 예외처리[실패시]
				try {
						// userEmail, userPassword 가 존재한다면 값을 반환해주는 작업[true, false]
						boolean existed = userRepository.existsByUserEmailAndUserPassword(userEmail, userPassword);
						// 실패시 로그인 페이지에서 로그인 성공시까지 로그인 페이지 유지 및 에러메세지 띄우기!
						if (!existed) return ResponseDto.setFailed("로그인 정보가 일치하지 않습니다.");
				} catch (Exception err) {
						return ResponseDto.setFailed("DB Error!");
				}

				// UserEntity 초기화
				UserEntity userEntity = null;

				// try - catch 예외처리
				try {
						// userEmail 값을 얻어오는 작업
						userEntity = userRepository.findById(userEmail).get();
				} catch (Exception err) {
						return ResponseDto.setFailed("DB Error!");
				}
				// userEntity 의 password 값은 빈값으로 받아줌
				userEntity.setUserPassword("");

				// user 이면 token, exprTime 의 권한을 부여해줌
				String token = tokenProvider.create(userEmail); // userEmail 에게 토큰을 부여함
				int exprTime = 3600000;

				// signIn 때 필요한 token, exprTime, user 정보를 불러오는 작업
				SignInResponseDto signInResponseDto = new SignInResponseDto(token, exprTime, userEntity);

				// 성공시 signInResponseDto 의 값을 반환 받음
				return ResponseDto.setSuccess("SignIn Success!", signInResponseDto);
		}
}
