package ru.ermakov.creator.service;

import org.springframework.stereotype.Service;
import ru.ermakov.creator.exception.UserNotFoundException;
import ru.ermakov.creator.model.SignUpData;
import ru.ermakov.creator.model.User;
import ru.ermakov.creator.repository.UserDao;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getUsersByPage(Integer currentId, Integer limit) {
        return userDao.getUsersByPage(currentId, limit);
    }

    @Override
    public User getUserById(Long userId) {
        return userDao.getUserById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with id %s not found", userId)));
    }

    @Override
    public Long addUser(SignUpData signUpData) {
        return userDao.insertUser(signUpData);
    }

    @Override
    public Long editUser(User user) {
        if (!userDao.userExistsById(user.getId())) {
            throw new UserNotFoundException(String.format("User with id %s not found", user.getId()));
        }
        return userDao.updateUser(user);
    }
}