package com.springboot.myfirsttime.board.data.dto;

import com.springboot.myfirsttime.board.data.entity.Board;
import lombok.*;

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

    public BoardResponseDto(Board board){
        this.no = board.getNo();
        this.writer = board.getWriter();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.isDelete = board.isDelete();
        this.view = board.getView();
    }

}
