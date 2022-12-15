package com.adm.rest;

import com.adm.config.JwtTokenUtil;
import com.adm.entities.User;
import com.adm.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginRestController {

    private JwtTokenUtil jwtTokenUtil;

    private UserService userService;

    public LoginRestController(JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @PostMapping("/restapi/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        // TODO: Check user credentials
        // TODO: Generate Token
        User userDB = userService.checkUser(user);
        if (userDB != null) {
            String apiKey = jwtTokenUtil.generateAccessToken(new User());
            return ResponseEntity.ok().body(Map.of(JwtTokenUtil.TOKEN_APIKEY, apiKey));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", "Invalid Credentials"));
        }
    }
}
