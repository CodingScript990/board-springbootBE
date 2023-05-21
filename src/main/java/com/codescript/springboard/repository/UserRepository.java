package com.codescript.springboard.repository;

import com.codescript.springboard.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 해당 클래스를 레포지토릴로 사용하겠다는 의미 [@Component 포함]
public interface UserRepository extends JpaRepository<UserEntity, String> { // UserEntity[매개체], String[기본키의 타입]
}