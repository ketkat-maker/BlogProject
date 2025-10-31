package com.katkat_maker.Blog.Services.impl;

import com.katkat_maker.Blog.Domain.PostStatus;
import com.katkat_maker.Blog.Domain.entities.Category;
import com.katkat_maker.Blog.Domain.entities.Post;
import com.katkat_maker.Blog.Domain.entities.Tag;
import com.katkat_maker.Blog.Domain.entities.User;
import com.katkat_maker.Blog.Repositories.PostRepository;
import com.katkat_maker.Blog.Services.CategoryService;
import com.katkat_maker.Blog.Services.PostService;
import com.katkat_maker.Blog.Services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.katkat_maker.Blog.Domain.PostStatus.DRAFT;
import static com.katkat_maker.Blog.Domain.PostStatus.PUBLISHED;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final TagService tagService;
    private final CategoryService categoryService;

    @Override
    public List<Post> getAllPosts(UUID categoryId, UUID tagId) {

        if (categoryId!=null&&tagId!=null){
            Tag tag = tagService.getTagById(tagId);
            Category category=categoryService.getCategoryById(categoryId);
            return postRepository.findAllByStatusAndCategoryAndTag(
                    PUBLISHED,
                    tag,
                    category);
        }
        if (categoryId!=null){
            Category category=categoryService.getCategoryById(categoryId);
            return postRepository.
                    findAllByStatusAndCategory(PUBLISHED
                            ,category);
        }
        if(tagId!=null){
            Tag tag = tagService.getTagById(tagId);
            return postRepository
                            .findAllByStatusAndTag(
                                PUBLISHED,
                                  tag);
        }
        return postRepository.findAllByStatus(PUBLISHED);
    }

    @Override
    public List<Post> getDraftPosts(User user) {
        return postRepository.findAllByAuthorAndStatus(user,DRAFT);
    }

}
