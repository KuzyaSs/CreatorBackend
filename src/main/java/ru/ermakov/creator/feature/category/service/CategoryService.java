package ru.ermakov.creator.feature.category.service;

import ru.ermakov.creator.feature.category.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    List<Category> getCategoriesByUserId(String userId);

    void updateCategories(String userId, List<Category> categories);
}
