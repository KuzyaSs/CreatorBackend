package ru.ermakov.creator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ermakov.creator.model.Category;
import ru.ermakov.creator.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("api")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("category")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("user/{userId}/category")
    public List<Category> getUserCategories(@PathVariable String userId) {
        return categoryService.getUserCategories(userId);
    }
}
