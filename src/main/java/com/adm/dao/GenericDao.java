package com.adm.dao;

import com.adm.config.SecurityConfiguration;
import com.adm.entities.IEntity;
import com.adm.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public abstract class GenericDao<T extends IEntity> implements IDao<T> {

    protected final Logger log = LoggerFactory.getLogger(getImplementationClass());

    protected final SessionFactory sessionFactory;

    protected final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public GenericDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<T> getList() {
        Session currSession = sessionFactory.getCurrentSession();
        List<T> entitiesList = currSession.createQuery("SELECT e FROM " + this.getEntityClass().getName() + " e").getResultList();
        return entitiesList;
    }

    public T findById(Long id) {
        Session currSession = sessionFactory.getCurrentSession();
        TypedQuery<T> query = currSession.createQuery("SELECT e FROM " + this.getEntityClass().getName() + " e WHERE e.id = ?1", this.getEntityClass());
        query.setParameter(1, id);
        T singleResult = query.getSingleResult();
        return singleResult;
    }

    public void update(T entity) {
        Session currSession = sessionFactory.getCurrentSession();
        T entityDB = currSession.find(this.getEntityClass(), entity.getId());

        if (entityDB != null) {
            // TODO: Use reflection to be more generic.
            updateEntity(entityDB, entity);
            currSession.flush();
        }
    }

    public abstract T updateEntity(T dbEntity, T newEntity);

    public void delete(T entity) {
        Session currSession = sessionFactory.getCurrentSession();
        if (!currSession.contains(entity)) {
            entity = (T) currSession.merge(entity);
        }

        currSession.remove(entity);
    }

    public void create(T entity) {
        Session currSession = sessionFactory.getCurrentSession();
        EntityTransaction transaction = currSession.getTransaction();

        currSession.persist(entity);
    }

    public abstract Class<?> getImplementationClass();
}
