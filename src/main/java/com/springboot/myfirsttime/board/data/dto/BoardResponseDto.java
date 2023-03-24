package com.springboot.myfirsttime.board.data.dto;

import com.springboot.myfirsttime.board.data.entity.Board;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BoardResponseDto {
    private int no;
    private String writer;
    private String title;
    private String content;
    private Boolean isDelete;
    private int view;
    private String imgRoute;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public BoardResponseDto(Board board){
        this.no = board.getNo();
        this.writer = board.getWriter();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.isDelete = board.isDelete();
        this.view = board.getView();
        this.imgRoute = board.getImgRoute();
        this.createdDate = board.getCreatedDate();
        this.modifiedDate = board.getModifiedDate();
    }

}
