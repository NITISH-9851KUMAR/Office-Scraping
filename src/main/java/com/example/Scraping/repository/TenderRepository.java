package com.example.Scraping.repository;

import com.example.Scraping.entity.Tender;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TenderRepository extends JpaRepository<Tender, Long> {

    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO Tender_Details
            (e_publish_date, closing_date, opening_date, TENDER_ID,
             organization_chain, inserted_date)
            VALUES
            (:ePublishDate, :closingDate, :openingDate, :tenderId,
             :organizationChain, :insertedDate)
            """, nativeQuery = true)
    int insertTender(
            @Param("ePublishDate") String ePublishDate,
            @Param("closingDate") String closingDate,
            @Param("openingDate") String openingDate,
            @Param("tenderId") String tenderId,
            @Param("organizationChain") String organizationChain,
            @Param("insertedDate") String insertedDate
    );

    @Query("""
            SELECT COUNT(t) > 0
            FROM Tender t
            WHERE t.tenderId = :tenderId
            """)


    boolean existsByTenderIdAndClosingDate(
            String tenderId,
            String closingDate
    );

    @Modifying
    @Transactional
    @Query("""
            UPDATE Tender t
            SET t.tender_reg_number = :tenderRegNo,
                t.tender_fee = :tenderFee,
                t.emd_amount = :emdAmount,
                t.work_desc = :workDescription,
                t.tender_value = :tenderValue,
                t.tender_inv_auth = :authority
            WHERE t.tenderId = :tenderId
            """)
    int updateTenderDetails(
            @Param("tenderRegNo") String tenderRegNo,
            @Param("tenderFee") String tenderFee,
            @Param("emdAmount") String emdAmount,
            @Param("workDescription") String workDescription,
            @Param("tenderValue") String tenderValue,
            @Param("authority") String authority,
            @Param("tenderId") String tenderId
    );
}