package com.example.springboot_board_project.repository;

import com.example.springboot_board_project.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}
