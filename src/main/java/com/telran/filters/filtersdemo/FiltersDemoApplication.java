package com.telran.filters.filtersdemo;

import com.telran.filters.filtersdemo.entity.Car;
import com.telran.filters.filtersdemo.entity.User;
import com.telran.filters.filtersdemo.repository.CarRepository;
import com.telran.filters.filtersdemo.repository.UserRepository;
import com.telran.filters.filtersdemo.repository.UserSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class FiltersDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FiltersDemoApplication.class, args);
    }
}

@Component
class ConsoleRunner implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSessionRepository userSessionRepository;

    @Autowired
    private CarRepository carRepository;


    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        userSessionRepository.deleteAll();
        carRepository.deleteAll();

        User user_1 = User.builder()
                .email("user1@site.com")
                .firstName("User")
                .lastName("The First")
                .password("123456")
                .build();

        User user_2 = User.builder()
                .email("user2@site.com")
                .firstName("User")
                .lastName("The Second")
                .password("123456")
                .build();

        User user_3 = User.builder()
                .email("user3@site.com")
                .firstName("User")
                .lastName("The Third")
                .password("123456")
                .build();

        userRepository.save(user_1);
        userRepository.save(user_2);
        userRepository.save(user_3);


        Car car_1 = Car.builder()
                .brand("Opel")
                .model("Astra")
                .isAvailable(true)
                .pricePerDay(200.0)
                .build();

        Car car_2 = Car.builder()
                .brand("Nissan")
                .model("Juke")
                .isAvailable(true)
                .pricePerDay(500.0)
                .build();

        carRepository.save(car_1);
        carRepository.save(car_2);
    }
}
