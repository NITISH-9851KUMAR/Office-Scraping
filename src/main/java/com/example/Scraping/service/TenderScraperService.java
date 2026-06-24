//package com.example.Scraping.service;
//
//import com.example.Scraping.entity.Tender;
//import com.example.Scraping.function.CurrentDate;
//import com.example.Scraping.repository.TenderRepository;
//
//import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import org.openqa.selenium.support.ui.*;
//
//import org.springframework.stereotype.Service;
//
//import java.time.Duration;
//import java.util.List;
//
//@Service
//public class TenderScraperService {
//
//    private final TenderRepository repository;
//
//    public TenderScraperService(TenderRepository repository) {
//        this.repository = repository;
//    }
//
//    public void scrape() {
////        Open Chrome Browser, Below line open browser automatically
//        WebDriver driver = new ChromeDriver();
//
////        This WebDriverWait Class wait maximum 30 seconds after that program will automatically terminated
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//
////        WebDriver Class open this website
//        driver.get("https://govtprocurement.delhi.gov.in/nicgep/app");
//
//        try {
//            Thread.sleep(5000);
//
////            Get All Html Elements from the web
//            List<WebElement> allElements = driver.findElements(By.xpath("//*"));
//
////            Traverse every element
//            for (WebElement el : allElements) {
//                try {
//                    String text = el.getText().toLowerCase();
//                    if (text.equals("search")
//                            || text.contains("search")) {
//                        el.click();
//                        System.out.println("Clicked Search");
//                        break;
//                    }
//                } catch (Exception ignored) {
//                }
//            }
//
//            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("table.list_table")));
//
//            List<WebElement> rows = driver.findElements(By.cssSelector("table.list_table tr"));
//
//            Tender t= new Tender();
//
//            for (WebElement row : rows) {
//
//                List<WebElement> cols =row.findElements(By.tagName("td"));
//
//                if (cols.size() == 6) {
//
//                    if(cols.get(0).getText().toLowerCase().equals("s.no")) continue;
//
//                    String col1 = cols.get(0).getText();
//                    String col2 = cols.get(1).getText();
//                    String col3 = cols.get(2).getText();
//                    String col4 = cols.get(3).getText();
//                    String col5 = cols.get(4).getText();
//                    String col6 = cols.get(5).getText();
//
//                    t.setSno(col1); t.setPublishedDate(col2); t.setClosingDate(col3); t.setOpeningDate(col4);
//                    t.setTitle(col5); t.setOrganisation(col6); t.setInsertedDate(CurrentDate.currentDate());
//
//                    repository.save(t);
//
//                }
//            }
//            System.out.println("Successfully Inserted Data into DB");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }// Scrape class
//}
//

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

        try {
            driver.get("https://govtprocurement.delhi.gov.in/nicgep/app");
            Thread.sleep(5000);

            // Click Search automatically
//            It store all web data
            List<WebElement> elements = driver.findElements(By.xpath("//*"));


            for(WebElement el : elements){

                try{
                    if(el.getText().toLowerCase().contains("search")){
                        el.click();
                        System.out.println("Search clicked");
                        break;
                    }
                }catch(Exception ignored){}
            }
            // Wait for table

            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("table.list_table")));

            // Loop all pages
            while(true){
                System.out.println("Reading current page...");

                List<WebElement> rows =driver.findElements(By.cssSelector("table.list_table tr"));

                for(WebElement row : rows){
                    // skip heading row

                    if(row.findElements(By.tagName("th")).size() > 1){
                        continue;
                    }
                    List<WebElement> cols = row.findElements(By.tagName("td"));

                    if(cols.size()==6){
                        Tender t =new Tender();

                        if(cols.get(0).getText().toLowerCase().contains("s.no")) continue;

                        t.setSno(cols.get(0).getText());
                        t.setPublishedDate(cols.get(1).getText());
                        t.setClosingDate(cols.get(2).getText());
                        t.setOpeningDate(cols.get(3).getText());
                        t.setTitle(cols.get(4).getText());
                        t.setOrganisation(cols.get(5).getText());
                        t.setInsertedDate(CurrentDate.currentDate());

                        boolean exists= repository.existsByTitleAndOrganisation(cols.get(4).getText(), cols.get(5).getText());

                        if(!exists){
                            repository.save(t);
                            System.out.println("Inserted new tender");
                        }else{
                            System.out.println("Duplicate skipped");
                        }
                    }
                }

                // NEXT PAGE CLICK It is pagination it automatically goes to next page
                try{
                    WebElement next = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='linkFwd']")));
                    next.click();
                    Thread.sleep(3000);
                }catch(Exception e){
                    System.out.println("No more pages");
                    break;
                }
            }
            System.out.println(
                    "All pages data inserted successfully"
            );
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            driver.quit();
        }

    }
}