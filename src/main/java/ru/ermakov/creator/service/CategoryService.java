package ru.ermakov.creator.service;

import ru.ermakov.creator.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    List<Category> getUserCategories(String userId);
}
