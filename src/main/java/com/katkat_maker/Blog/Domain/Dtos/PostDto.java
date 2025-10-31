package com.katkat_maker.Blog.Domain.Dtos;


import com.katkat_maker.Blog.Domain.PostStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Data
//@Setter
public class PostDto {
    private UUID id;
    private String title;
    private String content;
    private AuthorDto author;
    private CategoryDto category;
    private List<TagDto> tag;
    private PostStatus postStatus;
    private Integer readingTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public List<TagDto> getTag() {
        return tag;
    }

    public PostStatus getPostStatus() {
        return postStatus;
    }

    public Integer getReadingTime() {
        return readingTime;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(AuthorDto author) {
        this.author = author;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public void setTag(List<TagDto> tag) {
        this.tag = tag;
    }

    public void setPostStatus(PostStatus postStatus) {
        this.postStatus = postStatus;
    }

    public void setReadingTime(Integer readingTime) {
        this.readingTime = readingTime;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
