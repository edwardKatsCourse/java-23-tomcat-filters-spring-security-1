package com.telran.filters.filtersdemo.repository;

import com.telran.filters.filtersdemo.entity.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CarRepository extends MongoRepository<Car, String> {

    List<Car> findAllByIsAvailableTrue();
}
