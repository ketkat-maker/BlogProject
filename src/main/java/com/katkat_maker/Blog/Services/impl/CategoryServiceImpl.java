package com.katkat_maker.Blog.Services.impl;

import com.katkat_maker.Blog.Domain.entities.Category;
import com.katkat_maker.Blog.Repositories.CategoryRepository;
import com.katkat_maker.Blog.Services.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAllWithPostCount();
    }

    @Override
    @Transactional
    public Category CreateCategory(@NotNull Category category) {
        String name = category.getName();
       if( categoryRepository.existsByNameIgnoreCase(name)){
           throw new IllegalArgumentException("Category already exists with name "+name);
       }
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(UUID id) {
    Optional<Category> category=categoryRepository.findById(id);
    if (category.isPresent()){
        if (!category.get().getPosts().isEmpty()){
            throw new IllegalArgumentException("Category has some posts on it");
        }
    }
    categoryRepository.deleteById(id);
    }

    @Override
    public Category getCategoryById(UUID id) {
        return
                categoryRepository.findById(id)
                        .orElseThrow(()->new EntityNotFoundException("Category not found by this id"+id));
    }
}
