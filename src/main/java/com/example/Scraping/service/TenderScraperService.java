package com.example.Scraping.service;

import com.example.Scraping.entity.Tender;
import com.example.Scraping.function.CurrentDate;
import com.example.Scraping.repository.TenderRepository;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.*;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class TenderScraperService {

    private final TenderRepository repository;

    public TenderScraperService(TenderRepository repository) {
        this.repository = repository;
    }

    public void scrape() {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://govtprocurement.delhi.gov.in/nicgep/app");

        try {
            Thread.sleep(5000);
            List<WebElement> allElements = driver.findElements(By.xpath("//*"));
            for (WebElement el : allElements) {
                try {
                    String text = el.getText().toLowerCase();
                    if (text.equals("search")
                            || text.contains("search")) {
                        el.click();
                        System.out.println("Clicked Search");
                        break;
                    }
                } catch (Exception ignored) {
                }
            }

            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("table.list_table")));

            List<WebElement> rows = driver.findElements(By.cssSelector("table.list_table tr"));

            Tender t= new Tender();

            for (WebElement row : rows) {

                if (row.findElements(By.tagName("th")).size() > 0) {
                    continue;
                }
                List<WebElement> cols =row.findElements(By.tagName("td"));

                if (cols.size() == 6) {

                    String col1 = cols.get(0).getText();
                    String col2 = cols.get(1).getText();
                    String col3 = cols.get(2).getText();
                    String col4 = cols.get(3).getText();
                    String col5 = cols.get(4).getText();
                    String col6 = cols.get(5).getText();

                    t.setSno(col1);
                    t.setPublishedDate(col2);
                    t.setClosingDate(col3);
                    t.setOpeningDate(col4);
                    t.setTitle(col5);
                    t.setOrganisation(col6);
                    t.setInsertedDate(CurrentDate.currentDate());

                    repository.save(t);

                }
            }
            System.out.println("Successfully Inserted Data into DB");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }// Scrape class

}
