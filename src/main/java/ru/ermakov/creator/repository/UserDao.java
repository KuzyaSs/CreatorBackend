package ru.ermakov.creator.repository;

import ru.ermakov.creator.model.SignUpData;
import ru.ermakov.creator.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> getUsersByPage(Integer currentId, Integer limit);

    Optional<User> getUserById(Long userId);

    Boolean userExistsById(Long userId);

    Long insertUser(SignUpData signUpData);

    Long updateUser(User user);
}
