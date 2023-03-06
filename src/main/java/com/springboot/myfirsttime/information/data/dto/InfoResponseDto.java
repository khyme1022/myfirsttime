package com.springboot.myfirsttime.information.data.dto;

import com.springboot.myfirsttime.information.data.entity.Info;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InfoResponseDto {
    private int no;
    private String writer;
    private String title;
    private String content;
    private boolean isDelete;
    private int view;
    private String applyAge;
    private String imgRoute;
    private LocalDateTime createdDate;

    public InfoResponseDto(Info info){
        this.no = info.getNo();
        this.writer = info.getWriter();
        this.title = info.getTitle();
        this.content = info.getContent();
        this.isDelete = info.isDelete();
        this.view = info.getView();
        this.imgRoute = info.getImgRoute();
        this.createdDate = info.getCreatedDate();
    }
}
