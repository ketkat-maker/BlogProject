package com.katkat_maker.Blog.Services;

import com.katkat_maker.Blog.Domain.entities.Tag;


import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface TagService{

    public List<Tag> getTag();
     List<Tag> createTag(List<String> tagNames);
     void deleteTag(UUID id);
     Tag getTagById(UUID id);
}
