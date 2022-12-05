package com.adm.dao;

import java.util.List;

public interface IDao<T> {

    public List<T> getList();

    public T findById(Long id);

    public void update(T entity);

    public void delete(T entity);

    public void create(T entity);

    public Class<T> getEntityClass();
}
