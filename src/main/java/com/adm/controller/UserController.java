package com.adm.controller;

import com.adm.entities.User;
import com.adm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//@Controller
//@RequestMapping("users")
// FIXME: User can access directly to
// http://localhost:8080/HotelBooking/views/list-users.jsp
// https://docs.spring.io/spring-framework/docs/3.0.0.RC2/reference/html/ch15s03.html
@Controller
@RequestMapping(path = "/users")
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
