package com.springboot.myfirsttime.information.service.crawling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(InfoCrawling.class)
public class InfoCrawlingTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    @DisplayName("크롤링 테스트")
    void getDataListTest(String url){

    }
}
