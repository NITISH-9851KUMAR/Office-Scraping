package com.example.Scraping;

import com.example.Scraping.repository.TenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestClass {
    @Autowired
    static TenderRepository repository;

    TestClass(TenderRepository r){
        repository= r;
    }

    static void check(){
        String tenderId= "[Request for Proposal from PSU Agencies for the Construction of New Pucca School Buildings on 27 Vacant Plots under the Directorate Of Education, Govt. of NCT of Delhi on Deposit Work Basis] [F.16(64)Cabinate Note Agencies/L and E][2026_DE_294607_1]";
        String closingDate= "29-Jul-2026 03:00 PM";
        boolean flag= repository.existsByTenderIdAndClosingDate(tenderId ,closingDate);
        System.out.println(flag);
    }

//
//    public static void main(String[] args) {
//
//
//
//
////        System.out.println("\u001B[31mDuplicate Skipped\u001B[0m"+" Value: 30");
////        System.out.println("hello world");
////
////        String val= "65,673";
////        double val1= Double.parseDouble(val);
////        System.out.println(val1);
//
//    }
}
