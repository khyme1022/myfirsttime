package com.springboot.myfirsttime.information.controller;

import com.springboot.myfirsttime.board.service.impl.BoardServiceImpl;
import com.springboot.myfirsttime.information.service.InfoService;
import com.springboot.myfirsttime.information.service.crawling.InfoCrawling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/info")
public class InfoController {
    private final Logger LOGGER = LoggerFactory.getLogger(InfoController.class);

    private final InfoService infoService;
    private final InfoCrawling infoCrawling;

    @Autowired
    public InfoController(InfoService infoService,InfoCrawling infoCrawling) {
        this.infoService = infoService;
        this.infoCrawling = infoCrawling;
    }


    @GetMapping("/get-Data")
    public List<String> showData(String url) throws InterruptedException {
        LOGGER.info("[크롤링 시작] url : {}",url);

        return infoCrawling.getDataList(url) ;
    }
}
