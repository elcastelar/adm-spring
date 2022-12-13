package com.adm.controller;

import com.adm.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    private UserService userService;

    public boolean isUserAuthenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return !(auth.getPrincipal().equals("anonymousUser")
                && auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ANONYMOUS")));
    }

    public LoginController(UserService userService, ApplicationContext appContext) {
        this.userService = userService;
        // NOTE: Here to check appContext Spring Container
        System.out.println("Creating loginController");

    }

    public String getLoggedUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            return ((User) principal).getUsername();
        }

        return principal.toString();
    }

    @RequestMapping(value = "/")
    public String login() {
        if (isUserAuthenticated()) {
            return "pet/list-pets";
        } else {
            return "login";
        }
    }
}
