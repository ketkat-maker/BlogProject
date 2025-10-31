package com.katkat_maker.Blog.Mapper;

import com.katkat_maker.Blog.Domain.Dtos.PostDto;
import com.katkat_maker.Blog.Domain.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PostMapper {
    @Mapping(target = "author",source = "author")
    @Mapping(target="category",source = "category")
    @Mapping(target = "tag",source = "tag")
    PostDto toDto(Post post);
}
