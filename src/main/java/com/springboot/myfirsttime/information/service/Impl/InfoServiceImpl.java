package com.springboot.myfirsttime.information.service.Impl;

import com.springboot.myfirsttime.information.data.dto.InfoResponseDto;
import com.springboot.myfirsttime.information.data.entity.Info;
import com.springboot.myfirsttime.information.data.repository.InfoRepository;
import com.springboot.myfirsttime.information.service.InfoService;
import com.springboot.myfirsttime.information.service.crawling.InfoCrawling;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class InfoServiceImpl implements InfoService {
    private final Logger LOGGER = LoggerFactory.getLogger(InfoService.class);
    public InfoRepository infoRepository;
    public InfoCrawling infoCrawling;

    @Autowired
    public InfoServiceImpl(InfoRepository infoRepository,InfoCrawling infoCrawling) {
        this.infoRepository = infoRepository;
        this.infoCrawling = infoCrawling;

    }

    @Override
    public List<InfoResponseDto> showInfoList(int pageNum) {
        return null;
    }

    
    @Override
    public InfoResponseDto showInfo(int boardNum) {
        return null;
    }

    // infoList에 담긴 정보 저장하는 메소드
    public void saveInfo(String url) throws InterruptedException{
        LOGGER.info("[saveInfo] 실행 ");
        List<Info> infoList = crawlInfo(url);
        infoRepository.saveAll(infoList);
        LOGGER.info("[saveInfo] 실행 완료 ");
    }
    // 크롤링 후 문자열을 InfoList에 저장해 return하는 메소드
    public List<Info> crawlInfo(String url) throws InterruptedException {
        LOGGER.info("[crawlInfo] 실행 ");
        LOGGER.info("[WebDriver 경로 설정]");
        System.setProperty("webdriver.chrome.driver", "D:\\Webdriver\\chromedriver.exe");
        LOGGER.info("[WebDriver 경로 완료]");
        LOGGER.info("[WebDriver 옵션 설정]");
        // webDriver 옵션 설정.
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.addArguments("--lang=ko");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");
        options.setCapability("ignoreProtectedModeSettings", true);
        LOGGER.info("[WebDriver] 옵션 설정 완료");
        WebDriver driver = new ChromeDriver(options);
        WebDriver driver2 = new ChromeDriver(options);
        LOGGER.info("[WebDriver] 초기화 완료");
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);  // 페이지 불러오는 여유시간.
        driver2.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver2.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);  // 페이지 불러오는 여유시간.
        LOGGER.info("[WebDriver 옵션 설정 완료]");
        LOGGER.info("[WebDriver] URL 설정");
        driver.get(url);
        LOGGER.info("[WebDriver] URL 설정 완료 URL : {}",url);

        List<Info> infoList = new ArrayList<>();
        LOGGER.info("크롤링 시작");
        List<WebElement> infoElementList = driver.findElements(By.partialLinkText("개요"));
        for(WebElement infoElement : infoElementList){
            String eachPage = infoElement.getAttribute("href");
            driver2.get(eachPage);

            String infoTitle = driver2.findElement(By.className("po_tit")).getText();


            String infoContent = "";
            WebElement webContent = driver2.findElement(By.className("po_content"));
            for (WebElement element : webContent.findElements(By.cssSelector("ul.list"))) {
                infoContent += element.getText() + System.lineSeparator();

            }

            Info info = Info.builder()
                    .writer("admin")
                    .title(infoTitle)
                    .content(infoContent)
                    .build();
            infoList.add(info);
        }
        LOGGER.info("크롤링 완료");
        driver.close();
        driver2.close();
        driver.quit();
        driver2.quit();
        LOGGER.info("[crawlInfo] 종료");
        return infoList;
    }
    //크롬드라이버 비정상 종료 시 taskkill /f /im chromedriver.exe /t

}
