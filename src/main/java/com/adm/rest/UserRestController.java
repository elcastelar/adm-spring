package com.adm.rest;

import com.adm.entities.User;
import com.adm.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRestController {

    private UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/restapi/users")
    public List<User> all() {
        return userService.getList();
    }

    @GetMapping("/restapi/users/{id}")
    public User findById(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @PostMapping("/restapi/users/create")
    public void add(@RequestBody User user) {
        userService.create(user);
    }

    @DeleteMapping("/restapi/users")
    public void delete(@RequestBody User user) {
        userService.delete(user);
    }
}
