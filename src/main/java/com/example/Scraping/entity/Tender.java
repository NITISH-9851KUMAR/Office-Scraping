package com.example.Scraping.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name= "Tender_Details")
@Data
public class Tender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // It works as serial no

    @Column(length= 300, name="e_publish_date")
    String publishDate;

    @Column(length= 300, name="closing_date")
    String closingDate;

    @Column(length= 300, name="opening_date")
    String openingDate;

    @Column(length= 3000, name="TENDER_ID")
    String tenderId;

    @Column(length= 1000)
    String organizationChain;

    @Column(length= 50)
    String insertedDate;

//    Add column into Existing table for store after click link information
    @Column(length= 1000)
    String tender_reg_number;

    @Column(nullable = true)
    String tender_fee;

    @Column(nullable=true)
    String emd_amount;

    @Column (length= 2000)
    String work_desc;

    @Column(nullable = true)
    String tender_value;

    @Column(length= 2500)
    String tender_inv_auth;

}