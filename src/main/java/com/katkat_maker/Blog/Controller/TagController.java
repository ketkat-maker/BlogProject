package com.katkat_maker.Blog.Controller;

import com.katkat_maker.Blog.Domain.Dtos.TagDto;
import com.katkat_maker.Blog.Domain.Dtos.createTagRequest;
import com.katkat_maker.Blog.Domain.entities.Tag;
import com.katkat_maker.Blog.Mapper.TagMapper;
import com.katkat_maker.Blog.Services.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tags")
public class TagController {
    private final TagService tagService;
    private final TagMapper tagMapper;

    @GetMapping
    public ResponseEntity<List<TagDto>>getTags(){

        List<Tag> tag = tagService.getTag();
        List<TagDto> tagDto =tag.stream().
                map( tagMapper::toTagDto).toList();

        return ResponseEntity.ok(tagDto);
    }

    @PostMapping
            public ResponseEntity <List<TagDto>>createTag(@Valid @RequestBody createTagRequest response){
        List<Tag> serviceTag = tagService.createTag(response.getNames());
        List<TagDto> tagRespons = serviceTag.stream().map(tagMapper::toTagDto).toList();
        return new ResponseEntity<>(
                tagRespons
                ,HttpStatus.CREATED
        );
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void>deleteTag(@PathVariable UUID id){
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }

}
