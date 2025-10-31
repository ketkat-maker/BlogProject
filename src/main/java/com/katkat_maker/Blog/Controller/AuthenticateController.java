package com.katkat_maker.Blog.Controller;

import com.katkat_maker.Blog.Domain.Dtos.LoginRequest;
import com.katkat_maker.Blog.Domain.Dtos.authRequest;
import com.katkat_maker.Blog.Services.AuthenticateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth/login")
public class AuthenticateController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticateController.class);
    private final AuthenticateService authenticateService;

    @PostMapping
    public ResponseEntity<authRequest> login(@RequestBody LoginRequest request) {
        logger.info("Received login request for email: {}", request.getEmail());
        try {
            UserDetails userDetails = authenticateService.authenticate(request.getEmail(), request.getPassword());
            String token = authenticateService.generateToken(userDetails);
            authRequest valued = authRequest.builder()
                    .token(token)
                    .expiredIn(86400)
                    .build();
            return ResponseEntity.ok(valued);
        } catch (AuthenticationException e) {
            logger.error("Authentication failed for email: {}", request.getEmail(), e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(authRequest.builder().token("Invalid credentials").expiredIn(0).build());
        }
    }
}