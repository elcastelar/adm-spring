package com.adm.controller;

import com.adm.entities.IEntity;
import com.adm.service.GenericService;

import java.util.List;

public abstract class SimpleListController<S extends GenericService<E, ?>, E extends IEntity> {

    private final S service;

    private List<E> entities;

    private E entity;

    public SimpleListController(S service) {
        this.service = service;
        this.entity = buildNewEntity();
    }

    public abstract String getEntityPreffix();

    public abstract E buildNewEntity();

    //
    public String edit(E entity) {
        this.entity = entity;
        return "maintain-" + getEntityPreffix() + ".xhtml";
    }

    public void delete(E entity) {
        this.service.delete(entity);
        this.entities.remove(entity);
    }

    public List<E> getList() {
        if (this.entities == null) {
            this.entities = service.getList();
        }

        return this.entities;
    }

    public String submit() {
        if (this.entity.getId() == null) {
            this.service.create(entity);
        } else {
            this.service.update(entity);
        }

        this.entity = buildNewEntity();

        reloadAllEntities();

        return "list-" + getEntityPreffix() + ".xhtml";
    }

    private void reloadAllEntities() {
        this.entities = this.service.getList();
    }

    public E getEntity() {
        return entity;
    }

}
