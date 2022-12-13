package com.adm.rest;

import com.adm.controller.UserController;
import com.adm.entities.User;
import com.adm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserRestController(UserService userService) {
        log.info("Creating UserRestController!");
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> listAll() {
        log.info("Request REST [users/] received!");
        return userService.getList();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id) {
        log.info("Request REST [users/{}] received!", id);
        return userService.findById(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody User user) {
        log.info("Request REST [users/update] received!");
        userService.update(user);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody User user) {
        log.info("Request REST [users/add] received!");
        userService.create(user);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestBody User user) {
        log.info("Request REST [users/delete] received!");
        userService.delete(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public boolean checkCredentials(@RequestBody User user) {
        log.info("Checking REST [users/login] received!");
        // TODO: Return access token
        return false;
//        return userService.checkUser(user);
    }
}
