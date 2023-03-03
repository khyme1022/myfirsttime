package com.springboot.myfirsttime.information.service;

import com.springboot.myfirsttime.information.data.dto.InformationResponseDto;

import java.util.List;

public interface InfoService {
    public void saveInfo();
    public List<InformationResponseDto> showBoardList(int pageNum);
    public InformationResponseDto showBoard(int boardNum);
}
