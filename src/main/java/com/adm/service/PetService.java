package com.adm.service;

import com.adm.dao.PetsDao;
import com.adm.entities.Pet;
import com.adm.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PetService extends GenericService<Pet, PetsDao> {

    public PetService(PetsDao petsDao) {
        super(petsDao);
    }
}
