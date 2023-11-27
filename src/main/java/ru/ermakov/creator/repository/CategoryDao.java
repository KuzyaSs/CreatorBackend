package ru.ermakov.creator.repository;

import ru.ermakov.creator.model.Category;
import ru.ermakov.creator.model.UserCategory;

import java.util.List;

public interface CategoryDao {
    List<Category> getAllCategories();

    List<UserCategory> getUserCategoriesByUserId(String userId);

    void updateUserCategories(List<UserCategory> userCategories);
}
