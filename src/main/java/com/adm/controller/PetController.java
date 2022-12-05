package com.adm.controller;

import com.adm.entities.Pet;
import com.adm.entities.User;
import com.adm.service.PetService;
import com.adm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "petController")
public class PetController {

    // FIXME: How to change the level at the server?
    private final Logger log = LoggerFactory.getLogger(PetController.class);

    private final PetService petService;

    private final UserService userService;

    private List<Pet> allPets;

    private List<User> allUsers;

    private Pet pet;

    public PetController(PetService petService, UserService userService) {
        log.info("PetController created!");
        this.petService = petService;
        this.userService = userService;
        this.pet = new Pet();
        this.pet.setOwner(new User());
    }

    public String edit(Pet pet) {
        log.info("button edit PetController clicked!");
        this.pet = pet;
        return "maintain-pets.xhtml";
    }

    public void delete(Pet pet) {
        log.info("PetController delete called!");

        this.petService.delete(pet);
        this.allPets.remove(pet);
    }

    public String submit() {
        if (this.pet.getId() == null) {
            log.info("Creating new pet");
            this.petService.create(pet);
        } else {
            log.info("Editing a pet");
            this.petService.update(pet);
        }

        this.pet = new Pet();

        reloadAllPets();

        return "list-pets.xhtml";
    }

    private void reloadAllPets() {
        this.allPets = this.petService.getList();
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public List<Pet> getAllPets() {
        if (this.allPets == null) {
            this.allPets = petService.getList();
        }

        return allPets;
    }

    public List<User> getAllUsers() {
        if (this.allUsers == null) {
            this.allUsers = userService.getList();
        }

        return allUsers;
    }
}
