package ru.ermakov.creator.service;

import org.springframework.stereotype.Service;
import ru.ermakov.creator.exception.UserNotFoundException;
import ru.ermakov.creator.exception.UsernameInUseException;
import ru.ermakov.creator.model.AuthUser;
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
    public List<User> getUsersByPage(Integer limit, Integer offset) {
        return userDao.getUsersByPage(limit, offset);
    }

    @Override
    public User getUserById(String userId) {
        return userDao.getUserById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with id %s not found", userId)));
    }

    @Override
    public void insertUser(AuthUser authUser) {
        userDao.insertUser(authUser);
    }

    @Override
    public void updateUser(User user) {
        if (!userDao.userExistsById(user.getId())) {
            throw new UserNotFoundException(String.format("User with id %s not found", user.getId()));
        }
        if (!userDao.checkUsernameUniqueness(user.getUsername(), user.getId())) {
            throw new UsernameInUseException(String.format("User with username %s exists", user.getUsername()));
        }
        userDao.updateUser(user);
    }
}