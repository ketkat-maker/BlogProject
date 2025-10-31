package com.katkat_maker.Blog.Domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name="tag")
//@AllArgsConstructor
//@NoArgsConstructor
@Setter
@Getter
@Builder
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @ManyToMany (mappedBy = "tag", cascade = CascadeType.ALL)
    private List<Post> posts= new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(id, tag.id) && Objects.equals(name, tag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Tag() {
    }

    public Tag(UUID id, String name, List<Post> posts) {
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
