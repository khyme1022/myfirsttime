package com.springboot.myfirsttime.information.controller;

import com.springboot.myfirsttime.board.service.impl.BoardServiceImpl;
import com.springboot.myfirsttime.information.data.dto.InfoResponseDto;
import com.springboot.myfirsttime.information.service.InfoService;
import com.springboot.myfirsttime.information.service.crawling.InfoCrawling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/info")
public class InfoController {
    private final Logger LOGGER = LoggerFactory.getLogger(InfoController.class);

    private final InfoService infoService;


    @Autowired
    public InfoController(InfoService infoService) {
        this.infoService = infoService;

    }

    /**
     * 설정한 URL에서 크롤링을 실행해서 DB에 저장
     * 관리자만 실행 가능
     * @throws InterruptedException
     */
    @PostMapping
    public void crawlData() throws InterruptedException {
        LOGGER.info("[crawlData] 실행시작 ");
        String url = "https://www.moel.go.kr/policy/policyinfo/young/list.do";
        infoService.saveInfo(url);

        LOGGER.info("[crawlData] 실행완료 ");
    }
    /**
     * 글 리스트 출력 메소드
     * @param pageNum 페이지 번호
     * @return  ResponseEntity<List<InfoResponseDto>> 글 목록
     * /info?page={pageNum} 혹은 /info로 GET요청 시 해당 페이지를 보여줌
     */
    @GetMapping
    public ResponseEntity<List<InfoResponseDto>> readInfoList(
            @RequestParam(value="page",required = false,defaultValue = "1") int pageNum){
        return ResponseEntity.ok(infoService.showInfoList(pageNum-1));
    }
    /**
     * 글 출력 메소드
     * @param infoNum 글 번호
     * @return  ResponseEntity<InfoResponseDto> 글 내용
     * /info/2로 GET 요청 시 해당 글번호에 해당하는 글을 보여줌
     */
    @Transactional
    @GetMapping("/{infoNum}")
    public ResponseEntity<InfoResponseDto> readInfo(
            @PathVariable("infoNum") int infoNum
    ){
        return ResponseEntity.ok(infoService.showInfo(infoNum));

    }


}
