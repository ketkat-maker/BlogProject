package com.katkat_maker.Blog.Domain.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
//@Table(name="category")
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts= new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) && Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Category() {
    }
    public Category(String name ) {
        this.name = name;
    }

    public Category(UUID id, String name, List<Post> posts) {
        this.id = id;
        this.name = name;
        this.posts = posts;
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

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
