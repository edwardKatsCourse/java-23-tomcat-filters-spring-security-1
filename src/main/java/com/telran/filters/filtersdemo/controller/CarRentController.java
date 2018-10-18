package com.telran.filters.filtersdemo.controller;

import com.telran.filters.filtersdemo.dto.CarRentRequest;
import com.telran.filters.filtersdemo.dto.CarRentReturnRequest;
import com.telran.filters.filtersdemo.dto.CarRentReturnResponse;
import com.telran.filters.filtersdemo.entity.Car;
import com.telran.filters.filtersdemo.entity.Revenue;
import com.telran.filters.filtersdemo.entity.User;
import com.telran.filters.filtersdemo.exception.CarException;
import com.telran.filters.filtersdemo.repository.CarRepository;
import com.telran.filters.filtersdemo.repository.RevenueRepository;
import com.telran.filters.filtersdemo.repository.UserSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarRentController {

    @Autowired
    private UserSessionRepository userSessionRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private RevenueRepository revenueRepository;

    @PostMapping("/rent")
    public void rentCar(@RequestBody CarRentRequest carRentRequest,
                        @RequestHeader("telran-authorization") String sessionId) {

        Car car = carRepository.findById(carRentRequest.getCarId()).orElse(null);
        if (car == null) {
            throw new CarException("No such car with ID " + carRentRequest.getCarId());
        }

        if (!car.getIsAvailable()) {
            throw new CarException(String.format("Car with ID [%s] is not available",
                    carRentRequest.getCarId()));
        }

        User user = userSessionRepository.findBySessionId(sessionId).getUser();

        car.setCurrentlyRentBy(user);
        car.setIsAvailable(false);

        carRepository.save(car);
    }

    @PutMapping("/return")
    public CarRentReturnResponse returnCar(@RequestBody CarRentReturnRequest request) {

        Car car = carRepository.findById(request.getCarId()).orElse(null);

        if (car == null) {
            throw new CarException("No such car with ID " + request.getCarId());
        }

        if (car.getIsAvailable()) {
            throw new CarException("Cannot return car, if you had not rented it");
        }

        User user = car.getCurrentlyRentBy();

        car.setIsAvailable(true);
        car.setCurrentlyRentBy(null);

        carRepository.save(car);

        Revenue revenue = Revenue.builder()
                .totalRevenue(request.getDaysInUse() * car.getPricePerDay())
                .build();

        revenueRepository.save(revenue);

        return CarRentReturnResponse.builder()
                .details(car)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .total(revenue.getTotalRevenue())
                .build();

    }

    @GetMapping("/all")
    public List<Car> getAvailableCars() {
        return carRepository.findAllByIsAvailableTrue();
    }
}
