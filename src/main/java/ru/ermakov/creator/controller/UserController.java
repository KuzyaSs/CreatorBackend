package ru.ermakov.creator.controller;

import org.springframework.web.bind.annotation.*;
import ru.ermakov.creator.model.AuthUser;
import ru.ermakov.creator.model.User;
import ru.ermakov.creator.service.UserService;

import java.util.List;

@RestController
@RequestMapping(path = "api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(@RequestParam Integer limit, @RequestParam Integer offset) {
        return userService.getUsersByPage(limit, offset);
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
