package com.example.Scraping.function;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrentDate {

    public static String currentDate(){
        LocalDateTime localDateTime= LocalDateTime.now();
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
        return localDateTime.format(formatter);
    }

}
