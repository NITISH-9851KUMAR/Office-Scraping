package com.example.Scraping.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(
        name= "Tender_Details",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "TENDER_ID")
        }
)
@Data
public class Tender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // It works as serial no

    @Column(length= 300)
    String e_publish_date;

    @Column(length= 300)
    String closing_date;

    @Column(length= 300)
    String opening_date;

    @Column(length= 3000, unique = true, name="TENDER_ID")
    String tenderId;

    @Column(length= 1000)
    String organization_chain;

    @Column(length= 50)
    String insertedDate;

//    Add column into Existing table
    @Column(length= 1000)
    String organization_chain_link;

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