package ru.ermakov.creator.service;

import ru.ermakov.creator.model.AuthUser;
import ru.ermakov.creator.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsersByPage(String searchQuery, Integer limit, Integer offset);

    User getUserById(String userId);

    void insertUser(AuthUser authUser);

    void updateUser(User user);
}