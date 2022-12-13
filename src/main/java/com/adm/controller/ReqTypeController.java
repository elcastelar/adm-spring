package com.adm.controller;

import com.adm.entities.ReqType;
import com.adm.service.ReqTypeService;
import org.springframework.stereotype.Component;

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
