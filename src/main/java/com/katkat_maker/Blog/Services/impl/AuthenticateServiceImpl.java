package com.katkat_maker.Blog.Services.impl;


import com.katkat_maker.Blog.Services.AuthenticateService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AuthenticateServiceImpl implements AuthenticateService {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;


    private final Long jwtExpiresMs=86400000L;

    @Override
    public UserDetails authenticate(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email,password)
        );

        return userDetailsService.loadUserByUsername(email);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiresMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    @Override
    public UserDetails validToken(String token) {
        String userName=extractToken(token);
        return userDetailsService.loadUserByUsername(userName);
    }

    private String extractToken(String token){
        return  Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private Key getSigningKey() {
        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }
}
