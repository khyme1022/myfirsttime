package com.springboot.myfirsttime.information.service;

import com.springboot.myfirsttime.information.data.dto.InfoResponseDto;
import com.springboot.myfirsttime.information.data.entity.Info;

import java.util.List;
import java.util.Map;

public interface InfoService {
    List<InfoResponseDto> showInfoList(int pageNum);
    InfoResponseDto showInfo(int boardNum);

    void saveInfo(String url) throws InterruptedException ;
    List<Info> crawlInfo(String url) throws InterruptedException ;
}
