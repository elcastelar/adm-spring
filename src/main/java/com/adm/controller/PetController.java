package com.adm.controller;

import com.adm.entities.Pet;
import com.adm.entities.PetType;
import com.adm.entities.ReqType;
import com.adm.entities.User;
import com.adm.enums.PetSex;
import com.adm.enums.PetTamperType;
import com.adm.service.PetService;
import com.adm.service.PetTypeService;
import com.adm.service.ReqTypeService;
import com.adm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component(value = "petController")
public class PetController extends SimpleHelperController {
    private final Logger log = LoggerFactory.getLogger(PetController.class);

    private final PetService petService;

    private final UserService userService;

    private final ReqTypeService reqTypeService;

    private final PetTypeService petTypeService;

    private List<Pet> allPets;

    private List<User> allUsers;

    private List<PetType> petTypeList;

    private List<ReqType> reqTypeList;

    private Map<Integer, ReqType> reqTypeMapCache;

    private Map<String, PetTamperType> petTamperTypeCache;

    private String selectedTamperTypes = "";

    private String selectedReqTypes;

    private Pet pet;

    public PetController(PetService petService, UserService userService,
                         ReqTypeService reqTypeService, PetTypeService petTypeService) {
        this.petService = petService;
        this.userService = userService;
        this.reqTypeService = reqTypeService;
        this.petTypeService = petTypeService;

        this.pet = new Pet();
    }

    public String edit(Pet pet) {
        this.pet = pet;

        if (!this.pet.getReqTypeSet().isEmpty()) {
            reqTypeMapCache = new HashMap<>();
            this.pet.getReqTypeSet().forEach(o -> reqTypeMapCache.put(o.getId(), o));
        }

        return "maintain-pets.xhtml";
    }

    public void delete(Pet pet) {
        this.petService.delete(pet);
        this.allPets.remove(pet);
    }

    public String submit() {
        Set<ReqType> newReqTypes = new HashSet<>();
        for (String number : selectedReqTypes.split(",")) {
            newReqTypes.add(reqTypeMapCache.get(Integer.parseInt(number)));
        }

        Set<PetTamperType> newTamperTypes = new HashSet<>();
        for (String selected : selectedTamperTypes.split(",")) {
            newTamperTypes.add(petTamperTypeCache.get(selected));
        }


        this.pet.setReqTypeSet(newReqTypes);
        this.pet.setPetTamperType(newTamperTypes);

        if (this.pet.getId() == null) {
            log.info("Creating new pet");
            this.petService.create(pet);
        } else {
            log.info("Editing a pet");
            this.petService.update(pet);
        }

        this.pet = new Pet();
        this.reqTypeMapCache = new HashMap<>();

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

    public List<PetType> getPetTypeList() {
        if (this.petTypeList == null) {
            this.petTypeList = petTypeService.getList();
        }

        return this.petTypeList;
    }

    public List<ReqType> getReqTypeList() {
        if (this.reqTypeList == null) {
            this.reqTypeList = reqTypeService.getList();
            this.reqTypeMapCache = reqTypeList.stream().collect(Collectors.toMap(ReqType::getId, o -> o));
            this.selectedReqTypes = "";
        }

        return this.reqTypeList;
    }

    public String getSelectedReqTypes() {
        return selectedReqTypes;
    }

    public void setSelectedReqTypes(String selectedReqTypes) {
        this.selectedReqTypes = selectedReqTypes;
    }

    public List<PetSex> getPetSexOptions() {
        return List.of(PetSex.values());
    }

    public List<PetTamperType> getPetTamperOptions() {
        if (petTamperTypeCache == null || petTamperTypeCache.isEmpty()) {
            this.petTamperTypeCache = Arrays.stream(PetTamperType.values()).collect(Collectors.toMap(PetTamperType::getDescription, o -> o));
        }

        return List.of(PetTamperType.values());
    }

    public String getSelectedTamperTypes() {
        return selectedTamperTypes;
    }

    public void setSelectedTamperTypes(String selectedTamperTypes) {
        this.selectedTamperTypes = selectedTamperTypes;
    }

    @Override
    public String getEntityPreffix() {
        return "pets";
    }
}
