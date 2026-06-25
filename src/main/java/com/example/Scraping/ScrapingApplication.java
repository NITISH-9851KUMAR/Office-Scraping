package com.example.Scraping;

import com.example.Scraping.entity.Tender;
import com.example.Scraping.service.GetAllTenderService;
import com.example.Scraping.service.TenderScraperService;
//import com.example.Scraping.service.TenderScraperService2;
import org.openqa.selenium.remote.CommandCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ScrapingApplication implements CommandLineRunner {

	@Autowired
	private TenderScraperService tService;

	public static void main(String[] args) {
		SpringApplication.run(ScrapingApplication.class, args);
	}

	@Override
	public void run(String... args){

		tService.scrapeAndSave();

	}

}
