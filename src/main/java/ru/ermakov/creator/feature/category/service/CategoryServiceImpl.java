package ru.ermakov.creator.feature.category.service;

import org.springframework.stereotype.Service;
import ru.ermakov.creator.feature.category.repository.CategoryDao;
import ru.ermakov.creator.feature.user.exception.UserNotFoundException;
import ru.ermakov.creator.feature.user.repository.UserDao;
import ru.ermakov.creator.feature.category.model.Category;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao;
    private final UserDao userDao;

    public CategoryServiceImpl(CategoryDao categoryDao, UserDao userDao) {
        this.categoryDao = categoryDao;
        this.userDao = userDao;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    @Override
    public List<Category> getCategoriesByUserId(String userId) {
        if (!userDao.userExistsById(userId)) {
            throw new UserNotFoundException();
        }
        return categoryDao.getCategoriesByUserId(userId);
    }

    @Override
    public void updateCategories(String userId, List<Category> categories) {
        categoryDao.updateCategories(userId, categories);
    }
}
