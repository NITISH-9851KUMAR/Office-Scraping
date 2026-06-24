package com.example.Scraping;


import com.example.Scraping.service.TenderScraperService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class TestRunner implements CommandLineRunner {

    private final TenderScraperService service;

    public TestRunner(TenderScraperService service){
        this.service = service;
    }

    @Override
    public void run(String... args)throws Exception {
        service.scrape();
    }
}