package com.adm.controller;

import com.adm.entities.Pet;
import com.adm.entities.PetType;
import com.adm.entities.User;
import com.adm.service.PetService;
import com.adm.service.PetTypeService;
import com.adm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "petTypeController")
public class PetTypeController extends SimpleListController<PetTypeService, PetType> {

    public PetTypeController(PetTypeService service) {
        super(service);
    }

    @Override
    public String getEntityPreffix() {
        return "pets-types";
    }

    @Override
    public PetType buildNewEntity() {
        return new PetType();
    }
}
