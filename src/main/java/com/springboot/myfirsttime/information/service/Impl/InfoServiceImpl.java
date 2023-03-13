package com.springboot.myfirsttime.information.service.Impl;

import com.springboot.myfirsttime.information.data.dto.InfoResponseDto;
import com.springboot.myfirsttime.information.data.repository.InfoRepository;
import com.springboot.myfirsttime.information.service.InfoService;
import com.springboot.myfirsttime.information.service.crawling.InfoCrawling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InfoServiceImpl implements InfoService {

    public InfoRepository infoRepository;
    public InfoCrawling infoCrawling;

    @Autowired
    public InfoServiceImpl(InfoRepository infoRepository,InfoCrawling infoCrawling) {
        this.infoRepository = infoRepository;
        this.infoCrawling = infoCrawling;

    }

    @Override
    public void saveInfo() {

    }


    @Override
    public List<String> crawlInfo() throws InterruptedException {
        //List<String> urlList = setURL();
        String url = "https://www.moel.go.kr/policy/policyinfo/young/list.do";
        List<String> rawData = null;


        rawData = infoCrawling.getDataList(url);


        return rawData;
    }


    @Override
    public List<InfoResponseDto> showBoardList(int pageNum) {
        return null;
    }

    @Override
    public InfoResponseDto showBoard(int boardNum) {
        return null;
    }

    public List<String> setURL(){
        List<String> urlList = null;
        urlList.add("https://www.moel.go.kr/policy/policyinfo/young/list.do");
        return urlList;
    }

}
