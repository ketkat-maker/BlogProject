package com.katkat_maker.Blog.Domain.Dtos;

import lombok.Builder;

import java.util.Objects;
import java.util.UUID;

//@Data
@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public class CategoryDto {
    private UUID id;
    private String name;
    private Long postCount;

    public CategoryDto() {
    }

    public CategoryDto(UUID id, String name, Long postCount) {
        this.id = id;
        this.name = name;
        this.postCount = postCount;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPostCount() {
        return postCount;
    }

    public void setPostCount(Long postCount) {
        this.postCount = postCount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CategoryDto dto = (CategoryDto) o;
        return Objects.equals(id, dto.id) && Objects.equals(name, dto.name) && Objects.equals(postCount, dto.postCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, postCount);
    }
}
