package com.example.Scraping.repository;

import com.example.Scraping.entity.Tender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TenderRepository extends JpaRepository<Tender, Long> {

    @Query("SELECT t.tenderId FROM Tender t")
    List<String> findAllTenderIds();

}
