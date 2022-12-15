package com.adm.dao;

import com.adm.entities.IEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public abstract class GenericDao<T extends IEntity> implements IDao<T> {

    protected final Logger log = LoggerFactory.getLogger(getImplementationClass());

    protected final SessionFactory sessionFactory;

    public GenericDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<T> getList() {
        Session currSession = sessionFactory.getCurrentSession();
        List<T> entitiesList = currSession.createQuery("SELECT e FROM " + this.getEntityClass().getName() + " e").getResultList();
        return entitiesList;
    }

    @Override
    public T findById(Integer id) {
        Session currSession = sessionFactory.getCurrentSession();
        TypedQuery<T> query = currSession.createQuery("SELECT e FROM " + this.getEntityClass().getName() + " e WHERE e.id = ?1", this.getEntityClass());
        query.setParameter(1, id);
        T singleResult = query.getSingleResult();
        return singleResult;
    }

    @Override
    public void update(T entity) {
        Session currSession = sessionFactory.getCurrentSession();
        T entityDB = currSession.find(this.getEntityClass(), entity.getId());

        if (entityDB != null) {
            updateEntity(entityDB, entity);
            currSession.flush();
        }
    }

    public abstract T updateEntity(T dbEntity, T newEntity);

    @Override
    public void delete(T entity) {
        Session currSession = sessionFactory.getCurrentSession();
        if (!currSession.contains(entity)) {
            entity = (T) currSession.merge(entity);
        }

        currSession.remove(entity);
    }

    @Override
    public void create(T entity) {
        Session currSession = sessionFactory.getCurrentSession();
        EntityTransaction transaction = currSession.getTransaction();

        currSession.persist(entity);
    }

    public abstract Class<?> getImplementationClass();
}
