package com.example.springboot_board_project.entity;

import com.example.springboot_board_project.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "board_table")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardEntity extends BaseEntity {
    @Id // pk 컬럼 지정. 필수
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column(length = 20, nullable = false) // 크기 20, not null
    private String boardWriter;

    @Column // 크기 255, null 가능
    private String boardPass;

    @Column
    private String boardTitle;

    @Column(length = 500)
    private String boardContents;

    @Column
    private int boardHits;

    @Column
    private int fileAttached; // 1 or 0

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardFileEntity> boardFileEntityList = new ArrayList<>();

    public static BoardEntity toSaveEntity(BoardDTO boardDTO) {
        return BoardEntity.builder()
                .boardWriter(boardDTO.getBoardWriter())
                .boardPass(boardDTO.getBoardPass())
                .boardTitle(boardDTO.getBoardTitle())
                .boardContents(boardDTO.getBoardContents())
                .boardHits(0)
                .fileAttached(0) // 파일 없음
                .build();
    }

    public static BoardEntity toUpdateEntity(BoardDTO boardDTO) {
        return BoardEntity.builder()
                .id(boardDTO.getId())
                .boardWriter(boardDTO.getBoardWriter())
                .boardPass(boardDTO.getBoardPass())
                .boardTitle(boardDTO.getBoardTitle())
                .boardContents(boardDTO.getBoardContents())
                .boardHits(boardDTO.getBoardHits())
                .build();
    }

    public static BoardEntity toSaveFileEntity(BoardDTO boardDTO) {
        return BoardEntity.builder()
                .boardWriter(boardDTO.getBoardWriter())
                .boardPass(boardDTO.getBoardPass())
                .boardTitle(boardDTO.getBoardTitle())
                .boardContents(boardDTO.getBoardContents())
                .boardHits(0)
                .fileAttached(1) // 파일 없음
                .build();
    }

    // DataInit 사용
    public BoardEntity(Long id, String boardWriter, String boardPass, String boardTitle, String boardContents, int boardHits) {
        this.id = id;
        this.boardWriter = boardWriter;
        this.boardPass = boardPass;
        this.boardTitle = boardTitle;
        this.boardContents = boardContents;
        this.boardHits = boardHits;
    }


}