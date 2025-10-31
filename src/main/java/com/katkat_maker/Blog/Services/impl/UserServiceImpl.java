package com.katkat_maker.Blog.Services.impl;

import com.katkat_maker.Blog.Domain.entities.User;
import com.katkat_maker.Blog.Repositories.UserRepository;
import com.katkat_maker.Blog.Services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public User getUserById(UUID idFromService) {
        return userRepository.findById(idFromService).orElseThrow(()->new EntityNotFoundException("User Not Found By this id "+ idFromService));
    }

}
