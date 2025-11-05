package com.stark.duckapi.repository;

import com.stark.duckapi.model.DuckModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DuckRepository extends JpaRepository<DuckModel, Long> {
    Optional<DuckModel> findByNickname(String nickname);
    boolean deleteByNickname(String nickname);
}
