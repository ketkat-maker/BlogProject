package com.katkat_maker.Blog.Services;

import com.katkat_maker.Blog.Domain.entities.User;

import java.util.UUID;

public interface UserService {
    User getUserById(UUID idFromService);
}
