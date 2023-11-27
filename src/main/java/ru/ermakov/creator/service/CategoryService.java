package ru.ermakov.creator.service;

import ru.ermakov.creator.model.Category;
import ru.ermakov.creator.model.UserCategory;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    List<UserCategory> getUserCategoriesByUserId(String userId);

    void updateUserCategories(String userId, List<UserCategory> userCategories);
}
