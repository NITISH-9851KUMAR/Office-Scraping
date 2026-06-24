package com.example.Scraping.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="DELHI_GOV_TENDERS")
public class Tender {

    @Id
    private String sno;

    @Column(length = 200)
    private String publishedDate;

    @Column(length= 300)
    private String closingDate;

    @Column(length= 300)
    private String openingDate;

    @Column(length= 3000)
    private String title;

    @Column(length= 2000)
    private String organisation;

    @Column(length= 200)
    private String InsertedDate;

}