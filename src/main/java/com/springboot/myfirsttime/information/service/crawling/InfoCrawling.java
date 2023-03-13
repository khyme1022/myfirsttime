package com.springboot.myfirsttime.information.service.crawling;

import lombok.extern.java.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class InfoCrawling {

    private final Logger LOGGER = LoggerFactory.getLogger(InfoCrawling.class);


    public List<String> getDataList(String url) throws InterruptedException {
        LOGGER.info("[getDataList] 실행 ");
        LOGGER.info("[WebDriver 경로 설정]");
        System.setProperty("webdriver.chrome.driver", "D:\\Webdriver\\chromedriver.exe");
        LOGGER.info("[WebDriver 옵션 설정]");
        // webDriver 옵션 설정.
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.addArguments("--lang=ko");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.setCapability("ignoreProtectedModeSettings", true);
        LOGGER.info("[WebDriver 초기화]");
        WebDriver driver = new ChromeDriver(options);
        WebDriver driver2 = new ChromeDriver(options);

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);  // 페이지 불러오는 여유시간.
        driver2.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver2.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);  // 페이지 불러오는 여유시간.


        driver.get(url);
        List<WebElement> infoList = driver.findElements(By.partialLinkText("개요"));

        for(WebElement info : infoList){
            String eachPage = info.getAttribute("href");
            driver2.get(eachPage);
            String title = driver2.findElement(By.className("po_tit")).getText();
            LOGGER.info("[해당하는 페이지 크롤링 중] : {}",title);
            WebElement content = driver2.findElement(By.className("po_content"));
            LOGGER.info("[지원대상][지원내용]");
            for (WebElement element : content.findElements(By.cssSelector("ul.list"))) {
                System.out.println(element.getText());
            }
        }

        driver.close();
        driver2.close();
        driver.quit();
        driver2.quit();
        LOGGER.info("[getDataList] 종료");

        return null;
    }
}
