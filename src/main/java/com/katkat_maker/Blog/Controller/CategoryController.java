package com.katkat_maker.Blog.Controller;

import com.katkat_maker.Blog.Domain.Dtos.CategoryDto;
import com.katkat_maker.Blog.Domain.Dtos.createCategoryRequest;
import com.katkat_maker.Blog.Domain.entities.Category;
import com.katkat_maker.Blog.Mapper.CategoryMapper;
import com.katkat_maker.Blog.Services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories(){
        List<CategoryDto> categories = categoryService.listCategories()
                .stream().map(categoryMapper::toDto)
                .toList();
        return  ResponseEntity.ok(categories);
    }
    @PostMapping
    public ResponseEntity<CategoryDto>createCategory(
            @Valid@RequestBody createCategoryRequest create
    )
    {
        Category categoryEntity = categoryMapper.toEntity(create);
        Category categoryCreated = categoryService.CreateCategory(categoryEntity);
    return new ResponseEntity<>(
            categoryMapper.toDto(categoryCreated),
            HttpStatus.CREATED
            );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable UUID id){
        categoryService.deleteCategory(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
