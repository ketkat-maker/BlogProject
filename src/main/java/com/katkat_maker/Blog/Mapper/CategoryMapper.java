package com.katkat_maker.Blog.Mapper;

import com.katkat_maker.Blog.Domain.Dtos.CategoryDto;
import com.katkat_maker.Blog.Domain.Dtos.createCategoryRequest;
import com.katkat_maker.Blog.Domain.PostStatus;
import com.katkat_maker.Blog.Domain.entities.Category;
import com.katkat_maker.Blog.Domain.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    CategoryDto toDto(Category category);

    @Mapping(target = "name", source = "names")
    Category toEntity(createCategoryRequest createCategoryRequest);

    @Named("calculatePostCount")
    default long calculatePostCount(List<Post> posts) {
        if (null == posts) {
            return 0;
        }
        return posts.stream()
                .filter(post -> PostStatus.PUBLISHED.equals(post.getStatus()))
                .count();
    }
}