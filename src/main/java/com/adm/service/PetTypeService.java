package com.adm.service;

import com.adm.dao.PetTypeDao;
import com.adm.entities.PetType;
import org.springframework.stereotype.Service;

@Service
public class PetTypeService extends GenericService<PetType, PetTypeDao> {
    public PetTypeService(PetTypeDao genericDao) {
        super(genericDao);
    }
}
