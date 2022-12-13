package com.adm.dao;

import com.adm.entities.PetType;
import com.adm.entities.ReqType;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class PetTypeDao extends GenericDao<PetType> {
    public PetTypeDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public PetType updateEntity(PetType dbEntity, PetType newEntity) {
        dbEntity.setName(newEntity.getName());
        dbEntity.setI18nPlaceholder(newEntity.getI18nPlaceholder());
        return dbEntity;
    }

    @Override
    public Class<?> getImplementationClass() {
        return PetTypeDao.class;
    }

    @Override
    public Class<PetType> getEntityClass() {
        return PetType.class;
    }
}
