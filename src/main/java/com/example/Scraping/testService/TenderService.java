package com.example.Scraping.testService;

import com.example.Scraping.repository.TenderRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

//@Service
public class TenderService {

//    @Autowired
    private TenderRepository repository;

    public void scraper(){

        WebDriver chromeDriver= new ChromeDriver();

        try{

            chromeDriver.get("https://govtprocurement.delhi.gov.in/nicgep/app");
            WebDriverWait wait= new WebDriverWait(chromeDriver, Duration.ofSeconds(10));

            WebElement searchBox= wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("SearchDescription")));
            searchBox.clear();
            searchBox.sendKeys("delhi");
            chromeDriver.findElement(By.name("Go")).click();


        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        TenderService t= new TenderService();
        t.scraper();
    }

}