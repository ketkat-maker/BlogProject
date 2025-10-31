package com.katkat_maker.Blog.Services;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticateService {
    UserDetails authenticate(String email,String password);
    String generateToken(UserDetails details);
    UserDetails validToken(String token);
}
