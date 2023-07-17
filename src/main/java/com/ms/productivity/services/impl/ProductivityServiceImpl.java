package com.ms.productivity.services.impl;

import com.ms.productivity.models.Productivity;
import com.ms.productivity.repositories.ProductivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductivityServiceImpl {

    @Autowired
    private ProductivityRepository repository;

    public void save(Productivity productivity){
        repository.save(productivity);
    }

    public Productivity createProductivityModel(Integer valueProductivity) {
        Productivity productivity = new Productivity();
        productivity.setProductivity(valueProductivity);
        productivity.setSaveDate(LocalDateTime.now());
        return productivity;
    }
}
