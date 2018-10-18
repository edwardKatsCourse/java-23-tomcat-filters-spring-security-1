package com.telran.filters.filtersdemo.repository;

import com.telran.filters.filtersdemo.entity.Revenue;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RevenueRepository extends MongoRepository<Revenue, String> {
}
