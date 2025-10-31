package com.katkat_maker.Blog.Repositories;

import com.katkat_maker.Blog.Domain.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface TagRepository extends JpaRepository<Tag, UUID> {

    @Query("SELECT t FROM Tag t LEFT JOIN FETCH t.posts")
    public List<Tag> getAllTags();
    public List<Tag> findByNameIn(List<String> tagNames);
    public void deleteById(UUID id);
}
