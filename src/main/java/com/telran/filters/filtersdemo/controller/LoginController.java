package com.telran.filters.filtersdemo.controller;

import com.telran.filters.filtersdemo.dto.LoginRequest;
import com.telran.filters.filtersdemo.dto.LoginResponse;
import com.telran.filters.filtersdemo.entity.User;
import com.telran.filters.filtersdemo.entity.UserSession;
import com.telran.filters.filtersdemo.exception.AuthenticationException;
import com.telran.filters.filtersdemo.repository.UserRepository;
import com.telran.filters.filtersdemo.repository.UserSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSessionRepository userSessionRepository;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        User user = userRepository.findByEmailAndPassword(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );

        if (user == null) {
            String errorMessage = String.format("User with email [%s] or password [***]" +
                    " does not exist", loginRequest.getEmail());

            throw new AuthenticationException(errorMessage);
        }

        UserSession userSession = UserSession
                .builder()
                .sessionId(UUID.randomUUID().toString())
                .user(user)
                .isValid(true)
                .build();

        userSessionRepository.save(userSession);

        return new LoginResponse(userSession.getSessionId());
    }

    @PutMapping("/logout")
    public void logout(@RequestHeader("telran-authorization") String sessionId) {
        System.out.println("logout");
        UserSession userSession = userSessionRepository.findBySessionId(sessionId);

        userSession.setValid(false);

        userSessionRepository.save(userSession);
    }
}
