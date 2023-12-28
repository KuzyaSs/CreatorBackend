package ru.ermakov.creator.feature.category.controller;

import org.springframework.web.bind.annotation.*;
import ru.ermakov.creator.feature.category.model.Category;
import ru.ermakov.creator.feature.category.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping(path = "api")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("categories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("users/{userId}/categories")
    public List<Category> getCategoriesByUserId(@PathVariable String userId) {
        return categoryService.getCategoriesByUserId(userId);
    }

    @PutMapping("users/{userId}/categories")
    public void updateCategories(@PathVariable String userId, @RequestBody List<Category> categories) {
        categoryService.updateCategories(userId, categories);
    }
}
