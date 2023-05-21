package com.codescript.springboard.repository;

import com.codescript.springboard.entity.PopularSearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PopularSearchRepository extends JpaRepository<PopularSearchEntity, String> {

}
