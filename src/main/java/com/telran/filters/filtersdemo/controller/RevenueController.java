package com.telran.filters.filtersdemo.controller;

import com.telran.filters.filtersdemo.entity.Revenue;
import com.telran.filters.filtersdemo.repository.RevenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/revenue")
public class RevenueController {

    @Autowired
    private RevenueRepository revenueRepository;


    @GetMapping("/")
    public Double getTotalRevenue() {
        return revenueRepository.findAll()
                .stream()
                .map(Revenue::getTotalRevenue)
                .reduce((a,b) -> a + b)
                .get();

    }
}
