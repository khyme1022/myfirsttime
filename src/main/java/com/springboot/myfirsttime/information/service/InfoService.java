package com.springboot.myfirsttime.information.service;

import com.springboot.myfirsttime.information.data.dto.InfoResponseDto;

import java.util.List;

public interface InfoService {
    public void saveInfo();
    public List<InfoResponseDto> showBoardList(int pageNum);
    public InfoResponseDto showBoard(int boardNum);
}
