package com.example.Scraping.service;

import com.example.Scraping.entity.Tender;
import com.example.Scraping.repository.TenderRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class GetAllTenderService {

    private TenderRepository repository;

    public GetAllTenderService(TenderRepository repository){
        this.repository= repository;
    }

    public List<Tender> getTenderList(){
        return repository.findAll();
    }

//    public boolean findByTitleAndOrganisation(){
//        boolean exists= repository.existsByTitleAndOrganisation("Yes", "No");
//        return exists;
//    }



}
