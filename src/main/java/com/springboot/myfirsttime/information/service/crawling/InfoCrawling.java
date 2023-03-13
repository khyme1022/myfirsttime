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
        System.setProperty("webdriver.chrome.driver","D:\\Webdriver\\chromedriver.exe");
        // webDriver 옵션 설정.
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.addArguments("--lang=ko");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.setCapability("ignoreProtectedModeSettings", true);

        WebDriver driver = new ChromeDriver(options);

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        List<String> dataList = null;
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);  // 페이지 불러오는 여유시간.

        System.out.println("elements");
        System.out.println("---------------");

        System.out.println("element1");
        List<WebElement> elements1 = driver.findElements(By.cssSelector(".youth_line twrap"));
        LOGGER.info("[반복문 실행]");

        for(WebElement element : elements1){
            System.out.println(element.getText());
            List<WebElement> youth_bt = element.findElements(By.className("youth_bt"));
            List<WebElement> mt_10 = element.findElements(By.className("mt_10"));
            for(WebElement youths : youth_bt){
                System.out.println(youths.getText());
            }
            for(WebElement mts : mt_10){
                System.out.println(mts.getText());
            }
            System.out.println("----------------");

        }
        LOGGER.info("[반복문 종료]");

        driver.close();
        driver.quit();
        LOGGER.info("[getDataList] 종료");
        return dataList;
    }
}
