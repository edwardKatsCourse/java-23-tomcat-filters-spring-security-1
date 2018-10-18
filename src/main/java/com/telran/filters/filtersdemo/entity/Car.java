package com.telran.filters.filtersdemo.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document
public class Car {

    @Id
    private String id;

    private Boolean isAvailable;

    @DBRef
    private User currentlyRentBy;

    private String brand;
    private String model;
    private Double pricePerDay;
}
