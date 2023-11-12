package ru.ermakov.creator.service;

import ru.ermakov.creator.model.SignUpData;
import ru.ermakov.creator.model.User;

import java.util.List;

public interface UserService {
    public List<User> getUsersByPage(Integer currentId, Integer limit);

    User getUserById(Long userId);

    Long addUser(SignUpData signUpData);

    Long editUser(User user);
}