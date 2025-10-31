package com.katkat_maker.Blog.Services;

import com.katkat_maker.Blog.Domain.entities.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> listCategories();
    Category CreateCategory(Category category);
    void deleteCategory(UUID id);
    Category getCategoryById(UUID id);
}
