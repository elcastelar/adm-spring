package com.adm.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    public boolean isUserAuthenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return !(auth.getPrincipal().equals("anonymousUser")
                && auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ANONYMOUS")));
    }

    public String getLoggedUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            return ((User) principal).getUsername();
        }

        return principal.toString();
    }

    // Used to redirect user if it's already logged in
    @RequestMapping(value = "/")
    public String login() {
        if (isUserAuthenticated()) {
            return "pet/list-pets";
        } else {
            return "login";
        }
    }
}
