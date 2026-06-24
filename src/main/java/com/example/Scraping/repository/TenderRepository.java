package com.example.Scraping.repository;

import com.example.Scraping.entity.Tender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenderRepository extends JpaRepository<Tender, Long> {

    boolean existsByTitleAndOrganisation(
            String title,
            String organisation
    );

}
