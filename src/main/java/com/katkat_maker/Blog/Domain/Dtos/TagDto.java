package com.katkat_maker.Blog.Domain.Dtos;

import java.util.UUID;


//@Builder
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class TagDto {
    private UUID id;
    private String name;
    private Integer postCount;
    public TagDto() {
    }

    public TagDto(UUID id, String name, Integer postCount) {
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

    public Integer getPostCount() {
        return postCount;
    }

    public void setPostCount(Integer postCount) {
        this.postCount = postCount;
    }

}
