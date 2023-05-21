package com.codescript.springboard.repository;

import com.codescript.springboard.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
}
