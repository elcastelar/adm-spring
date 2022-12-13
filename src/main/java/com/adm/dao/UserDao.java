package com.adm.dao;

import com.adm.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class UserDao extends GenericDao<User> {

    public UserDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public User updateEntity(User dbEntity, User newEntity) {
        dbEntity.setUsername(newEntity.getUsername());
        dbEntity.setPassword(newEntity.getPassword());
        return dbEntity;
    }

    @Override
    public Class<?> getImplementationClass() {
        return UserDao.class;
    }

    public User checkUser(User user) {
        Session currSession = sessionFactory.getCurrentSession();
        TypedQuery<User> query = currSession.createQuery("SELECT u FROM " + User.class.getName() + " u WHERE u.username = ?1 and u.password = ?2", User.class);
        query.setParameter(1, user.getUsername());
        query.setParameter(2, user.getPassword());
        User userResult = null;
        try {
            userResult = query.getSingleResult();
        } catch (NoResultException e) {
            log.error(String.format("No entity found with username %s", user.getUsername()), e);
        }

        return userResult;
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }
}
