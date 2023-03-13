package com.springboot.myfirsttime.information.service;

import com.springboot.myfirsttime.information.data.dto.InfoResponseDto;

import java.util.List;
import java.util.Map;

public interface InfoService {
    public void saveInfo();
    public List<String> crawlInfo() throws InterruptedException;
    public List<InfoResponseDto> showBoardList(int pageNum);
    public InfoResponseDto showBoard(int boardNum);

}
