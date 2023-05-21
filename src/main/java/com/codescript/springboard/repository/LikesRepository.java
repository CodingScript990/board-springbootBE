package com.codescript.springboard.repository;

import com.codescript.springboard.entity.LikesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<LikesEntity, Integer> {
}
