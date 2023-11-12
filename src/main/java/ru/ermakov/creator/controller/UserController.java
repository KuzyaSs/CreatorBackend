package ru.ermakov.creator.controller;

import org.springframework.web.bind.annotation.*;
import ru.ermakov.creator.model.SignUpData;
import ru.ermakov.creator.model.User;
import ru.ermakov.creator.service.UserService;

import java.util.List;

@RestController
@RequestMapping(path = "api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(@RequestParam Integer currentId, @RequestParam Integer limit) {
        return userService.getUsersByPage(currentId, limit);
    }

    @GetMapping("{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping
    public Integer addUser(@RequestBody SignUpData signUpData) {
        return userService.addUser(signUpData);
    }
}
