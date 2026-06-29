package com.example.Scraping.testService;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service
@Controller
public class SeleniumTest1 {

    static void webDriverMethod() {

//        It will open chrome website, generally controlled by program
        WebDriver chromeDriver = new ChromeDriver();
//        WebDriver edgeDriver = new EdgeDriver();
//        WebDriver firefoxDriver = new FirefoxDriver();

        try {
//
////        get the link of any website
            chromeDriver.get("https://www.tpointtech.com/java-tutorial");
            Thread.sleep(5000);
            chromeDriver.quit();

//                    get the link of any website
//            edgeDriver.get("https://www.tpointtech.com/java-tutorial");
//            Thread.sleep(2000);
//            edgeDriver.quit();
//
//            //        get the link of any website
//            firefoxDriver.get("https://www.tpointtech.com/java-tutorial");
//            Thread.sleep(2000);
//            firefoxDriver.quit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }// Main Method

    public static void main(String[] args) {
        SeleniumTest1.webDriverMethod();
    }

} // Main Class
