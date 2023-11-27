package ru.ermakov.creator.service;

import org.springframework.stereotype.Service;
import ru.ermakov.creator.exception.UserNotFoundException;
import ru.ermakov.creator.model.Category;
import ru.ermakov.creator.model.UserCategory;
import ru.ermakov.creator.repository.CategoryDao;
import ru.ermakov.creator.repository.UserDao;

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
    public List<UserCategory> getUserCategoriesByUserId(String userId) {
        if (!userDao.userExistsById(userId)) {
            throw new UserNotFoundException();
        }
        return categoryDao.getUserCategoriesByUserId(userId);
    }

    @Override
    public void updateUserCategories(String userId, List<UserCategory> userCategories) {
        categoryDao.updateUserCategories(userId, userCategories);
    }
}