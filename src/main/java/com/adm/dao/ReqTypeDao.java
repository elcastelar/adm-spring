package com.adm.dao;

import com.adm.entities.Pet;
import com.adm.entities.ReqType;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ReqTypeDao extends GenericDao<ReqType> {

    public ReqTypeDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public ReqType updateEntity(ReqType dbEntity, ReqType newEntity) {
        dbEntity.setDescription(newEntity.getDescription());
        dbEntity.setI18nPlaceholder(newEntity.getI18nPlaceholder());
        return dbEntity;
    }

    @Override
    public Class<ReqType> getEntityClass() {
        return ReqType.class;
    }
}
