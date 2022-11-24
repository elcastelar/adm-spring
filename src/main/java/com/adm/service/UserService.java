package com.adm.service;

import com.adm.entities.User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getList() {
        List<User> from_tb_users = entityManager.createQuery("SELECT u FROM " + User.class.getName() + " u").getResultList();
        return from_tb_users;
    }

    public User findById(Long id) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM " + User.class.getName() + " u WHERE u.id = ?1", User.class);
        query.setParameter(1, id);
        return query.getSingleResult();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void update(User user) {
        User userDB = this.entityManager.find(User.class, user.getId());
        if (userDB != null) {
            userDB.setUsername(user.getUsername());
            userDB.setPassword(user.getPassword());
            this.entityManager.flush();
        }
    }

    @Transactional
    public void delete(User user) {
        if (!this.entityManager.contains(user)) {
            user = this.entityManager.merge(user);
        }

        this.entityManager.remove(user);
    }

    @Transactional
    public void create(User user) {
        this.entityManager.persist(user);
    }
}
