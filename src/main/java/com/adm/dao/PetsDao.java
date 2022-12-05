package com.adm.dao;

import com.adm.entities.Pet;
import com.adm.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class PetsDao extends GenericDao<Pet> {
    public PetsDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Pet updateEntity(Pet dbEntity, Pet newEntity) {
        dbEntity.setName(newEntity.getName());
        return dbEntity;
    }

    @Override
    public Class<Pet> getEntityClass() {
        return Pet.class;
    }
}
