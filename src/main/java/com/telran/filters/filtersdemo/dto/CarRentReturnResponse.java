package com.telran.filters.filtersdemo.dto;

import com.telran.filters.filtersdemo.entity.Car;
import com.telran.filters.filtersdemo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CarRentReturnResponse {

    private Car details;
    private Double total;
    private String firstName;
    private String lastName;


}
