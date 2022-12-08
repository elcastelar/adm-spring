package com.adm.controller;

import com.adm.entities.User;
import com.adm.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {

    private UserService userService;

    private User user;

    private ApplicationContext appContext;

    public LoginController(UserService userService, ApplicationContext appContext) {
        this.userService = userService;
        this.appContext = appContext;
        // NOTE: Here to check appContext Spring Container

    }

    public String login() {
        return null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
