package com.springboot.myfirsttime.board.data.dto;

import com.springboot.myfirsttime.board.data.entity.Board;
import lombok.*;

import javax.servlet.http.HttpServletRequest;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardRequestDto {
    private int no;
    private String writer;
    private String title;
    private String content;
    private Boolean isDelete;
    private int view;
    private String imgRoute;

    public BoardRequestDto(HttpServletRequest request){
        this.writer = request.getParameter("writer");
        this.title = request.getParameter("title");
        this.content = request.getParameter("content");
        if(request.getParameter("imgRoute")!= null) this.imgRoute = request.getParameter("imgRoute");
    }

    public Board toEntity(){
        return Board.builder().no(no)
                .writer(writer)
                .title(title)
                .content(content)
                .isDelete(isDelete)
                .view(view)
                .imgRoute(imgRoute)
                .build();
    }
}
