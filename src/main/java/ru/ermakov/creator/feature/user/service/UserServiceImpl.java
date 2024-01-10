package ru.ermakov.creator.feature.user.service;

import org.springframework.stereotype.Service;
import ru.ermakov.creator.feature.user.exception.DuplicateUsernameException;
import ru.ermakov.creator.feature.user.exception.UserNotFoundException;
import ru.ermakov.creator.feature.user.repository.UserDao;
import ru.ermakov.creator.feature.user.model.AuthUser;
import ru.ermakov.creator.feature.user.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getUserPageBySearchQuery(String searchQuery, Integer limit, Integer offset) {
        return userDao.getUserPageBySearchQuery(searchQuery, limit, offset);
    }

    @Override
    public User getUserById(String userId) {
        return userDao.getUserById(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void insertUser(AuthUser authUser) {
        userDao.insertUser(authUser);
    }

    @Override
    public void updateUser(User user) {
        if (!userDao.userExistsById(user.id())) {
            throw new UserNotFoundException();
        }
        if (userDao.userExistsByUsername(user.username(), user.id())) {
            throw new DuplicateUsernameException();
        }
        userDao.updateUser(user);
    }
}