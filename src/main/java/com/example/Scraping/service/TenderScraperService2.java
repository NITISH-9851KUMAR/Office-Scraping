//import com.example.Scraping.entity.Tender;
//import com.example.Scraping.function.CurrentDate;
//import com.example.Scraping.repository.TenderRepository;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.springframework.stereotype.Service;
//
//import java.time.Duration;
//import java.util.List;
//
//@Service
//public class TenderScraperService2 {
//    private final TenderRepository repository;
//
//    public TenderScraperService2(TenderRepository repository) {
//        this.repository = repository;
//    }
//
//    public void scrape() {
//
//        WebDriver driver = new ChromeDriver();
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//
//        try {
//
//            // Open Search Page
//            driver.get("https://govtprocurement.delhi.gov.in/nicgep/app?component=%24DirectLink&page=FrontEndAdvancedSearchResult&service=direct");
//
//            // Enter Delhi in search box
//            WebElement searchBox = wait.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            By.id("SearchDescription"))
//            );
//
//            searchBox.clear();
//            searchBox.sendKeys("delhi");
//
//            // Click Go button
//            WebElement goButton = wait.until(
//                    ExpectedConditions.elementToBeClickable(
//                            By.id("Go"))
//            );
//
//            driver.get("https://govtprocurement.delhi.gov.in/nicgep/app?component=%24DirectLink&page=FrontEndAdvancedSearchResult&service=direct");
//
//            System.out.println(driver.getCurrentUrl());
//            System.out.println(driver.getTitle());
//
//            List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
//            System.out.println("Total iframes = " + iframes.size());
//
//            goButton.click();
//
//            // Wait for result table
//            wait.until(
//                    ExpectedConditions.presenceOfElementLocated(
//                            By.cssSelector("table.list_table"))
//            );
//
//            // Read all pages
//            while (true) {
//
//                System.out.println("Reading Current Page...");
//
//                List<WebElement> rows =
//                        driver.findElements(
//                                By.cssSelector("table.list_table tr"));
//
//                for (WebElement row : rows) {
//
//                    List<WebElement> cols =
//                            row.findElements(By.tagName("td"));
//
//                    if (cols.size() < 6) {
//                        continue;
//                    }
//
//                    String sno = cols.get(0).getText().trim();
//
//                    if ("s.no".equalsIgnoreCase(sno)) {
//                        continue;
//                    }
//
//                    String publishDate =
//                            cols.get(1).getText().trim();
//
//                    String closingDate =
//                            cols.get(2).getText().trim();
//
//                    String openingDate =
//                            cols.get(3).getText().trim();
//
//                    String title =
//                            cols.get(4).getText().trim();
//
//                    String organisation =
//                            cols.get(5).getText().trim();
//
//                    boolean exists =
//                            repository.existsByTitleAndOrganisation(
//                                    title,
//                                    organisation
//                            );
//
//                    if (exists) {
//                        System.out.println(
//                                "Duplicate Data Skipped");
//                        continue;
//                    }
//
//                    Tender tender = new Tender();
//
//                    tender.setSno(sno);
//                    tender.setPublishedDate(publishDate);
//                    tender.setClosingDate(closingDate);
//                    tender.setOpeningDate(openingDate);
//                    tender.setTitle(title);
//                    tender.setOrganisation(organisation);
//                    tender.setInsertedDate(
//                            CurrentDate.currentDate());
//
//                    try {
//
//                        repository.save(tender);
//
//                        System.out.println(
//                                "Inserted : " + title);
//
//                    } catch (Exception e) {
//
//                        System.out.println(
//                                "Failed Record : " + title);
//
//                        e.printStackTrace();
//                    }
//                }
//
//                // Move to next page
//                try {
//
//                    WebElement next = wait.until(
//                            ExpectedConditions.elementToBeClickable(
//                                    By.id("linkFwd"))
//                    );
//
//                    next.click();
//
//                    Thread.sleep(3000);
//
//                } catch (Exception e) {
//
//                    System.out.println("No More Pages");
//                    break;
//                }
//            }
//
//            System.out.println(
//                    "All Pages Processed Successfully");
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//        } finally {
//
//            driver.quit();
//        }
//    }
//
//}
