package com.adm.service;

import com.adm.dao.ReqTypeDao;
import com.adm.entities.ReqType;
import org.springframework.stereotype.Service;

@Service
public class ReqTypeService extends GenericService<ReqType, ReqTypeDao> {

    public ReqTypeService(ReqTypeDao genericDao) {
        super(genericDao);
    }
}
