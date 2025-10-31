package com.katkat_maker.Blog.Services;

import com.katkat_maker.Blog.Domain.PostStatus;
import com.katkat_maker.Blog.Domain.entities.Post;
import com.katkat_maker.Blog.Domain.entities.User;

import java.util.List;
import java.util.UUID;

public interface PostService {
    public List<Post> getAllPosts(UUID categoryId , UUID tagId);
    public List<Post>getDraftPosts(User user);
}
