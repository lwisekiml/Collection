package com.example.springboot_board_project;

import com.example.springboot_board_project.entity.BoardEntity;
import com.example.springboot_board_project.repository.BoardRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final BoardRepository boardRepository;

    @PostConstruct
    public void init() {
        boardRepository.save(new BoardEntity(1L, "test", "q", "test", "test", 1));
        boardRepository.save(new BoardEntity(2L, "2222", "q", "2222", "test", 2));
        boardRepository.save(new BoardEntity(3L, "3333", "q", "3333", "test", 3));
        boardRepository.save(new BoardEntity(4L, "4444", "q", "4444", "test", 4));
        boardRepository.save(new BoardEntity(5L, "5555", "q", "5555", "test", 5));
        boardRepository.save(new BoardEntity(6L, "6666", "q", "6666", "test", 6));
        boardRepository.save(new BoardEntity(7L, "7777", "q", "7777", "test", 7));
        boardRepository.save(new BoardEntity(8L, "8888", "q", "8888", "test", 8));
    }
}
