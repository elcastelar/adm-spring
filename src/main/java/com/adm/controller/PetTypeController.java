package com.adm.controller;

import com.adm.entities.PetType;
import com.adm.service.PetTypeService;
import org.springframework.stereotype.Component;

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
