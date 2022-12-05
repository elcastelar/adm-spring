package com.adm.controller;

import com.adm.entities.User;
import com.adm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@Controller
//@RequestMapping("users")
// FIXME: User can access directly to
// http://localhost:8080/HotelBooking/views/list-users.jsp
// https://docs.spring.io/spring-framework/docs/3.0.0.RC2/reference/html/ch15s03.html
@Component(value = "userController")
public class UserController {

//    @Autowired
//    private ApplicationContext applicationContext;

    // FIXME: How to change the level at the server?
    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    private List<User> allUsers;

    private User user = new User();

    public UserController(UserService userService) {
        log.info("UserController created!");
        this.userService = userService;
        this.user = new User();
    }

    public String edit(User user) {
        log.info("button edit UserController clicked!");
        this.user = user;
        return "maintain-users.xhtml";
    }

    public void delete(User user) {
        log.info("delete called!");

        this.userService.delete(user);
        this.allUsers.remove(user);
    }

    //    @GetMapping("/list")
    public List<User> getList() {
        if (this.allUsers == null) {
            this.allUsers = userService.getList();
        }

        return allUsers;
    }

    public String submit() {
        if (this.user.getId() == null) {
            log.info("Creating new user");
            this.userService.create(user);
        } else {
            log.info("Editing a user");
            this.userService.update(user);
        }

        this.user = new User();

        reloadAllUsers();

        return "list-users.xhtml";
    }

    private void reloadAllUsers() {
        this.allUsers = this.userService.getList();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
