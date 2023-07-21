package com.ms.productivity.services.impl;

import com.ms.productivity.models.Productivity;
import com.ms.productivity.repositories.ProductivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class ProductivityServiceImpl {

    @Autowired
    private ProductivityRepository repository;

    public void save(Productivity productivity){
        repository.save(productivity);
    }

    public Productivity createProductivityModel(Map<String, Integer> points, Integer completedItems, Integer totalItems) {
        Productivity productivity = new Productivity();
        productivity.setProductivity(points.get("Completed Items"));
        productivity.setTotal(points.get("Total Items"));
        productivity.setCompletedItems(completedItems);
        productivity.setTotalItems(totalItems);
        productivity.setSaveDate(LocalDateTime.now());
        return productivity;
    }
}
