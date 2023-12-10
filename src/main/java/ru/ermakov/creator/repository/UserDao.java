package ru.ermakov.creator.repository;

import ru.ermakov.creator.model.AuthUser;
import ru.ermakov.creator.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> getUsersByPage(String searchQuery, Integer limit, Integer offset);

    Optional<User> getUserById(String userId);

    Boolean userExistsById(String userId);

    Boolean userExistsByUsername(String username, String userId);

    void insertUser(AuthUser authUser);

    void updateUser(User user);
}
