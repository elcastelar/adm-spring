package com.adm.controller;

import com.adm.entities.Pet;
import com.adm.entities.User;
import com.adm.service.PetService;
import com.adm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

public class GenericController {
//    private final Logger log = LoggerFactory.getLogger(GenericController.class);
//
//    private final PetService petService;
//
//    private List<Pet> allPets;
//
//    private List<User> allUsers;
//
//    private Pet pet;
//
//    public GenericController(PetService petService) {
//        log.info("PetController created!");
//        this.petService = petService;
//        this.pet = new Pet();
//    }
//
//    public String edit(User user) {
//        log.info("button edit PetController clicked!");
//        this.pet = user;
//        return "maintain-pets.xhtml";
//    }
//
//    public void delete(User user) {
//        log.info("PetController delete called!");
//
//        this.petService.delete(pet);
//        this.allPets.remove(pet);
//    }
//
//    //    @GetMapping("/list")
//    public List<User> getList() {
//        if (this.allUsers == null) {
//            this.allUsers = userService.getList();
//        }
//
//        return allUsers;
//    }
//
//    public String submit() {
//        if (this.user.getId() == null) {
//            log.info("Creating new user");
//            this.userService.create(user);
//        } else {
//            log.info("Editing a user");
//            this.userService.update(user);
//        }
//
//        this.user = new User();
//
//        reloadAllUsers();
//
//        return "list-users.xhtml";
//    }
//
//    private void reloadAllUsers() {
//        this.allUsers = this.userService.getList();
//    }
//
//    public UserService getUserService() {
//        return userService;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}
