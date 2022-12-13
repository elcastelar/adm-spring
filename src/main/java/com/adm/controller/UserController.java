package com.adm.controller;

import com.adm.entities.User;
import com.adm.service.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController extends SimpleListController<UserService, User> {

    public UserController(UserService service) {
        super(service);
    }

    @Override
    public String getEntityPreffix() {
        return "users";
    }

    @Override
    public User buildNewEntity() {
        return new User();
    }
}
