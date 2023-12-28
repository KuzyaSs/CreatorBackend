package ru.ermakov.creator.feature.user.controller;

import org.springframework.web.bind.annotation.*;
import ru.ermakov.creator.feature.user.model.AuthUser;
import ru.ermakov.creator.feature.user.model.User;
import ru.ermakov.creator.feature.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping(path = "api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsersByPage(@RequestParam String searchQuery, @RequestParam Integer limit, @RequestParam Integer offset) {
        return userService.getUsersByPage(searchQuery, limit, offset);
    }

    @GetMapping("{userId}")
    public User getUserById(@PathVariable String userId) {
        return userService.getUserById(userId);
    }

    @PostMapping
    public void insertUser(@RequestBody AuthUser authUser) {
        userService.insertUser(authUser);
    }

    @PutMapping
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }
}
