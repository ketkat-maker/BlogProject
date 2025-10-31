package com.katkat_maker.Blog.Services.impl;

import com.katkat_maker.Blog.Domain.entities.Tag;
import com.katkat_maker.Blog.Repositories.TagRepository;
import com.katkat_maker.Blog.Services.TagService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    @Override
    public List<Tag> getTag() {
        return tagRepository.getAllTags();
    }

    @Transactional
    @Override
    public List<Tag> createTag(List<String> name) {


        List<Tag> existingTagNames = tagRepository.findByNameIn(name);

        Set<String> setTagName = existingTagNames.stream()
                .map(Tag::getName)
                .collect(Collectors.toSet());



        List<Tag> newTag = name.stream()
                .filter(name1 -> !existingTagNames.contains(name1))
                .map(name1 -> Tag.builder().
                        name(name1)
                        .posts(new ArrayList<>())
                        .build()).toList();

//        if (name.isEmpty()||name==null) {
//            return new ArrayList<>();
//        }
            List<Tag> savedTags=new ArrayList<>();
            if (!newTag.isEmpty()){
                savedTags=tagRepository.saveAll(newTag);
            }

            savedTags.addAll(existingTagNames);


            return  savedTags;
    }
    @Transactional
    @Override
    public void deleteTag(UUID id) {
        tagRepository.findById(id).ifPresent(tag-> {
            if(!tag.getPosts().isEmpty()){
            throw new IllegalStateException("Cant delete tag with posts");
            }
            tagRepository.deleteById(id);
        });

    }

    @Override
    public Tag getTagById(UUID id) {
        return
                tagRepository.findById(id).
                        orElseThrow(()->new EntityNotFoundException("Tag not found by this id"+id));
    }
//@Transactional
//@Override
//public void deleteTag(UUID id) {
//    System.out.println("Looking for tag with ID: " + id);
//    Optional<Tag> tagOptional = tagRepository.findById(id);
//    if (tagOptional.isPresent()) {
//        Tag tag = tagOptional.get();
//        System.out.println("Found tag: " + tag.getName());
//        if (!tag.getPosts().isEmpty()) {
//            throw new IllegalStateException("Cant delete tag with posts");
//        }
//        tagRepository.deleteById(id);
//        System.out.println("Tag deleted successfully");
//    } else {
//        System.out.println("Tag not found for ID: " + id);
//    }
//}

}
