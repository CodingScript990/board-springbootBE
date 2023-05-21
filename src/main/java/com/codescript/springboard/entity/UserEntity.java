package com.codescript.springboard.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "User") // 해당 클래스를 Entity 클래스로 사용한다는 의미
@Table(name =  "User") // 데이터베이스에 있는 해당하는 테이블과 현재 클래스를 매핑하겠다는 의미
public class UserEntity {
		// Member Field
		@Id
		private String userEmail;
		private String userPassword;
		private String userNickname;
		private String userPhoneNumber;
		private String userAddress;
		private String userProfile;
}
