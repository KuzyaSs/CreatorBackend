package ru.ermakov.creator.controller;

import org.springframework.web.bind.annotation.*;
import ru.ermakov.creator.model.Category;
import ru.ermakov.creator.model.UserCategory;
import ru.ermakov.creator.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("api")
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
    public List<UserCategory> getUserCategoriesByUserId(@PathVariable String userId) {
        return categoryService.getUserCategoriesByUserId(userId);
    }

    @PutMapping("users/{userId}/categories")
    public void updateUserCategories(@PathVariable String userId, @RequestBody List<UserCategory> userCategories) {
        categoryService.updateUserCategories(userId, userCategories);
    }
}
