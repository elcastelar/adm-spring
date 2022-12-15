package com.adm.service;

import com.adm.dao.IDao;
import com.adm.entities.IEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class GenericService<E extends IEntity, T extends IDao<E>> {

    protected final T genericDao;

    public GenericService(T genericDao) {
        this.genericDao = genericDao;
    }

    @Transactional
    public List<E> getList() {
        return genericDao.getList();
    }

    @Transactional
    public E findById(Integer id) {
        return genericDao.findById(id);
    }

    @Transactional
    public void update(E entity) {
        genericDao.update(entity);
    }

    @Transactional
    public void delete(E entity) {
        genericDao.delete(entity);
    }

    @Transactional
    public void create(E entity) {
        genericDao.create(entity);
    }
}
