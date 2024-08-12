package com.example.springboot_board_project.entity;

import com.example.springboot_board_project.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.*;

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

    public static BoardEntity toSaveEntity(BoardDTO boardDTO) {
        return BoardEntity.builder()
                .boardWriter(boardDTO.getBoardWriter())
                .boardPass(boardDTO.getBoardPass())
                .boardTitle(boardDTO.getBoardTitle())
                .boardContents(boardDTO.getBoardContents())
                .boardHits(0)
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
}