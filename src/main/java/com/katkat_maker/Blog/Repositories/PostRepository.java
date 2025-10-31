package com.katkat_maker.Blog.Repositories;

import com.katkat_maker.Blog.Domain.PostStatus;
import com.katkat_maker.Blog.Domain.entities.Category;
import com.katkat_maker.Blog.Domain.entities.Post;
import com.katkat_maker.Blog.Domain.entities.Tag;
import com.katkat_maker.Blog.Domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

List<Post> findAllByStatusAndCategoryAndTag(PostStatus status, Tag tag, Category category);
List<Post> findAllByStatusAndCategory(PostStatus status,Category category);
List<Post>findAllByStatusAndTag(PostStatus status,Tag tag);
List<Post> findAllByStatus(PostStatus postStatus);
List<Post> findAllByAuthorAndStatus(User author, PostStatus status);
}
