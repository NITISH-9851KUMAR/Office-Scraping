package com.example.Scraping.service;

import com.example.Scraping.entity.Tender;
import com.example.Scraping.function.CurrentDate;
import com.example.Scraping.repository.TenderRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class TenderScraperServiceGetLink {

    @Autowired
    private TenderRepository repository;


    public void scrapeAndSave() {

        WebDriver driver = new ChromeDriver();
        int count = 0;
        int skipped = 0;
        int inserted = 0;


        try {
            driver.get("https://govtprocurement.delhi.gov.in/nicgep/app");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("SearchDescription")));

            searchBox.clear();
            searchBox.sendKeys("delhi");

            driver.findElement(By.name("Go")).click();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("table")));


            while (true) {
                Tender tender = new Tender();
                List<String> tenderUrls= new ArrayList<>();
                WebElement table = driver.findElement(By.id("table"));

                List<WebElement> rows = table.findElements(By.xpath("./tbody/tr"));

                for (WebElement row : rows) {

                    List<WebElement> cols = row.findElements(By.tagName("td"));

                    if (row.getAttribute("class").contains("list_header")) {
                        continue;
                    }

//                 Skip pagination/footer row
                    if (row.findElements(By.className("list_footer")).size() > 0) {
                        continue;
                    }

                    if (cols.size() < 6) {
                        continue;
                    }

                    String e_publishing_date = cols.get(1).getText().trim();
                    String closing_date = cols.get(2).getText().trim();
                    String opening_date = cols.get(3).getText().trim();
                    String tenderId = cols.get(4).getText().trim();
                    String organization_chain = cols.get(5).getText().trim();


//                    System.out.println(e_publishing_date);
//                    System.out.println(closing_date);
//                    System.out.println(opening_date);
//                    System.out.println(tenderId);
//                    System.out.println(organization_chain+"\n");

//                    tender.setE_publish_date(e_publishing_date);
//                    tender.setClosing_date(closing_date);
//                    tender.setOpening_date(opening_date);
//                    tender.setTenderId(tenderId);
//                    tender.setOrganization_chain(organization_chain);
//                    tender.setInsertedDate(CurrentDate.currentDate());

                    count++;

//                     Duplicate check using Method
                    if (repository.existsByTenderId(tenderId)) {
                        String tenderUrl = cols.get(4).findElement(By.tagName("a")).getAttribute("href");
                        tenderUrls.add(tenderUrl);
                        skipped++;
//                        System.out.println("\u001B[31mDuplicate Skipped\u001B[0m");
                    }
//                    } else {
//                        System.out.println("\nInserted New Data\n");
//                        repository.save(tender);
//                        inserted++;
//                    }
                }

//                Print Link value
                for (String url : tenderUrls) {
                    driver.get(url);
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(.,'Organisation Chain')]")));
                    String organisationChain_link = driver.findElement(By.xpath("//td[contains(.,'Organisation Chain')]/following-sibling::td[1]")).getText().trim();
                    String tenderRegNo = driver.findElement(By.xpath("//td[contains(.,'Tender Reference Number')]/following-sibling::td[1]")).getText().trim();
                    String tenderFee = driver.findElement(By.xpath("//td[contains(.,'Tender Fee in')]/following-sibling::td[1]")).getText().trim();
                    String emdAmount = driver.findElement(By.xpath("//td[contains(.,'EMD Amount')]/following-sibling::td[1]")).getText().trim();
                    String workDescription = driver.findElement(By.xpath("//td[contains(.,'Work Description')]/following-sibling::td[1]")).getText().trim();
                    String tenderValue = driver.findElement(By.xpath("//td[contains(.,'Tender Value')]/following-sibling::td[1]")).getText().trim();
                    String name = driver.findElement(By.xpath("//td[text()='Tender Inviting Authority']/ancestor::tr/following-sibling::tr[1]//td[b[text()='Name']]/following-sibling::td")).getText().trim();
                    String address = driver.findElement(By.xpath("//td[text()='Tender Inviting Authority']/ancestor::tr/following-sibling::tr[1]//td[b[text()='Address']]/following-sibling::td")).getText().trim();

                    tender.setOrganization_chain_link(organisationChain_link);
                    tender.setTender_reg_number(tenderRegNo);
                    tender.setTender_fee(tenderFee);
                    tender.setEmd_amount(emdAmount);
                    tender.setWork_desc(workDescription);
                    tender.setTender_value(tenderValue);
                    tender.setTender_inv_auth(name+address);
                    repository.save(tender);
//                    System.out.println("\n\n Print Link value \n");
//                    System.out.println(organisationChain);
//                    System.out.println(tenderRegNo);
//                    System.out.println(tenderFee);
//                    System.out.println(emdAmount);
//                    System.out.println(workDescription);
//                    System.out.println(tenderValue);
//                    System.out.println(name + " " + address);
                }

                try {
                    driver.get("https://govtprocurement.delhi.gov.in/nicgep/app");

                    WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(20));

                    WebElement searchBox2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("SearchDescription")));

                    searchBox2.clear();
                    searchBox2.sendKeys("delhi");

                    driver.findElement(By.name("Go")).click();

                    wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("table")));
//                    WebElement oldTable = driver.findElement(By.id("table"));
                    WebElement next = wait.until(ExpectedConditions.elementToBeClickable(By.id("linkFwd")));
                    next.click();
//                    wait.until(ExpectedConditions.stalenessOf(oldTable));
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("table")));

                } catch (Exception e) {
                    System.out.println("No More Pages");
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("\n\n\n*------*  Important Notice  *-----*\n\n\n");
            System.out.println("Total Rows on the Web: " + count);
            System.out.println("Skipped Rows: " + skipped);
            System.out.println("\u001B[31mDuplicate Skipped\u001B[0m");
            System.out.println("Total Posted Rows: " + inserted);
            System.out.println();
            driver.quit();
        }
    }


}