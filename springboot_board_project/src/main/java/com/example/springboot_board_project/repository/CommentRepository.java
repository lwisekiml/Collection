package com.example.springboot_board_project.repository;

import com.example.springboot_board_project.entity.BoardEntity;
import com.example.springboot_board_project.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    // select * from comment_table where board_id=? order by id desc;
    List<CommentEntity> findByBoardEntityOrderByIdDesc(BoardEntity boardEntity);
}
