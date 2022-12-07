package com.adm.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

// Cat, Dog, etc.
@Entity(name = "tb_pet_type")
public class PetType implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String i18nPlaceholder;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getI18nPlaceholder() {
        return i18nPlaceholder;
    }

    public void setI18nPlaceholder(String i18nPlaceholder) {
        this.i18nPlaceholder = i18nPlaceholder;
    }

    @Override
    public void setCreationDateTime(LocalDateTime now) {

    }
}
