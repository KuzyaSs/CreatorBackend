package ru.ermakov.creator.repository;

import ru.ermakov.creator.model.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> getAllCategories();

    List<Category> getCategoriesByUserId(String userId);

    void updateCategories(String userId, List<Category> categories);
}
