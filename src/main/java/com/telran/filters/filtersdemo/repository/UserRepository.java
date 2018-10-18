package com.telran.filters.filtersdemo.repository;

import com.telran.filters.filtersdemo.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByEmailAndPassword(String email, String password);
}
