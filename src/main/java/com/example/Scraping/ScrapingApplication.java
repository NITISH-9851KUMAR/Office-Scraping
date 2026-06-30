package com.example.Scraping;

//import com.example.Scraping.service.TenderScraperService;
import com.example.Scraping.service.TenderScraperServiceGetLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScrapingApplication implements CommandLineRunner {

//	@Autowired
//	private TenderScraperService tService;

	private TenderScraperServiceGetLink tenderScraperServiceGetLink;

	ScrapingApplication(TenderScraperServiceGetLink getLink){
		this.tenderScraperServiceGetLink= getLink;
	}

	public static void main(String[] args) {
		SpringApplication.run(ScrapingApplication.class, args);
	}

	@Override
	public void run(String... args){
//		tService.scrapeAndSave();
		tenderScraperServiceGetLink.scrapeAndSave();
//		TestClass.check();
	}

}
