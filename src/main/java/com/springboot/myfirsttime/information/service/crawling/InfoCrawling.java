package com.springboot.myfirsttime.information.service.crawling;

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
import java.util.concurrent.TimeUnit;

@Component
public class InfoCrawling {

    private final Logger LOGGER = LoggerFactory.getLogger(InfoCrawling.class);

    public List<String> getDataList(String url) throws InterruptedException {

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

        List<String> list = new ArrayList<>();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);  // 페이지 불러오는 여유시간.


        List<WebElement> elements = driver.findElements(By.cssSelector("th"));
        for(WebElement element : elements){
            System.out.println("---------------");
            System.out.println(element.getText());
            list.add(element.getText());
        }
        driver.close();
        driver.quit();
        return list;
    }
}
