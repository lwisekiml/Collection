package com.example.springboot_board_project.service;

import com.example.springboot_board_project.dto.BoardDTO;
import com.example.springboot_board_project.entity.BoardEntity;
import com.example.springboot_board_project.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);
    }
}
