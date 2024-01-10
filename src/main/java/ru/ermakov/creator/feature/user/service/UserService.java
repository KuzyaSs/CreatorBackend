package ru.ermakov.creator.feature.user.service;

import ru.ermakov.creator.feature.user.model.AuthUser;
import ru.ermakov.creator.feature.user.model.User;

import java.util.List;

public interface UserService {
    List<User> getUserPageBySearchQuery(String searchQuery, Integer limit, Integer offset);

    User getUserById(String userId);

    void insertUser(AuthUser authUser);

    void updateUser(User user);
}