package com.katkat_maker.Blog.Domain.entities;

import com.katkat_maker.Blog.Domain.PostStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
//@Getter
//@Builder
@Table(name="post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PostStatus status;

    @Column(nullable = false)
    private  Integer readingTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id",nullable = false)
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "post_tag",
            joinColumns = @JoinColumn(name="post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tag=new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(title, post.title) && Objects.equals(content, post.content) && Objects.equals(createdAt, post.createdAt) && Objects.equals(updatedAt, post.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, createdAt, updatedAt);
    }

    @PrePersist
    protected void createdOn(){
        LocalDateTime now = LocalDateTime.now();
        this.createdAt= now;
        this.updatedAt=now;
    }

    @PreUpdate
    protected void updatedOn(){
        this.updatedAt=LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public PostStatus getStatus() {
        return status;
    }

    public Integer getReadingTime() {
        return readingTime;
    }

    public User getAuthor() {
        return author;
    }

    public Category getCategory() {
        return category;
    }

    public List<Tag> getTag() {
        return tag;
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

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }

    public void setReadingTime(Integer readingTime) {
        this.readingTime = readingTime;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setTag(List<Tag> tag) {
        this.tag = tag;
    }
}
