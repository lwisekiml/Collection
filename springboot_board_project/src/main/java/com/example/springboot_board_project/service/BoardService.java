package com.example.springboot_board_project.service;

import com.example.springboot_board_project.dto.BoardDTO;
import com.example.springboot_board_project.entity.BoardEntity;
import com.example.springboot_board_project.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);
    }

    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        ArrayList<BoardDTO> boardDTOList = new ArrayList<>();
        for (BoardEntity boardEntity : boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }

    @Transactional
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public BoardDTO findById(Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            return BoardDTO.toBoardDTO(boardEntity);
        } else {
            return null;
        }
    }

    // 아래 메소드로 하면 date 값이 안나온다.
    // 유튜브에서는 date 값이 나오지만
    // 영속성 컨텍스트 캐시값을 가져오는 것으로 인해 date 값이 안 나오는것이 맞는것 같다.
//    public BoardDTO update(BoardDTO boardDTO) {
//        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO);
//        boardRepository.save(boardEntity);
//        return findById(boardDTO.getId());
//    }

    // 아래와 같이 하면 값이 제대로 나온다.
    public BoardDTO update(BoardDTO boardDTO) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(boardDTO.getId());
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            boardEntity.updateBoard(boardDTO);
        }
        return findById(boardDTO.getId());
    }
}
