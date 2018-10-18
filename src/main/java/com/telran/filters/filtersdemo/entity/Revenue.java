package com.telran.filters.filtersdemo.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document
public class Revenue {

    private String id;
    private Double totalRevenue;

}
