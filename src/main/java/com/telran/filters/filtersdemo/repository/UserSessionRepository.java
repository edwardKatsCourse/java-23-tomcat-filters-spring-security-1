package com.telran.filters.filtersdemo.repository;

import com.telran.filters.filtersdemo.entity.UserSession;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserSessionRepository extends MongoRepository<UserSession, String> {

    UserSession findBySessionId(String sessionId);
}
