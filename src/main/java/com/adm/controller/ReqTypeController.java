package com.adm.controller;

import com.adm.entities.IEntity;
import com.adm.entities.Pet;
import com.adm.entities.ReqType;
import com.adm.entities.User;
import com.adm.service.GenericService;
import com.adm.service.PetService;
import com.adm.service.ReqTypeService;
import com.adm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "reqTypeController")
public class ReqTypeController extends SimpleListController<ReqTypeService, ReqType> {

    public ReqTypeController(ReqTypeService service) {
        super(service);
    }

    @Override
    public String getEntityPreffix() {
        return "req-types";
    }

    @Override
    public ReqType buildNewEntity() {
        return new ReqType();
    }

}
