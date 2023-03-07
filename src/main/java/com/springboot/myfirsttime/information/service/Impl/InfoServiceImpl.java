package com.springboot.myfirsttime.information.service.Impl;

import com.springboot.myfirsttime.information.data.dto.InfoResponseDto;
import com.springboot.myfirsttime.information.data.repository.InfoRepository;
import com.springboot.myfirsttime.information.service.InfoService;
import com.springboot.myfirsttime.information.service.crawling.InfoCrawling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InfoServiceImpl implements InfoService {

    public InfoRepository infoRepository;


    @Autowired
    public InfoServiceImpl(InfoRepository infoRepository) {
        this.infoRepository = infoRepository;

    }

    @Override
    public void saveInfo() {

    }

    @Override
    public List<InfoResponseDto> showBoardList(int pageNum) {
        return null;
    }

    @Override
    public InfoResponseDto showBoard(int boardNum) {
        return null;
    }



}
