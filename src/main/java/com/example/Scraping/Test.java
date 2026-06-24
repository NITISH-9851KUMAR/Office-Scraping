package com.example.Scraping;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        WebDriver driver= new ChromeDriver();

        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.get("https://govtprocurement.delhi.gov.in/nicgep/app");

//        List<WebElement> allElements = driver.findElements(By.xpath("//*"));

    }
}
