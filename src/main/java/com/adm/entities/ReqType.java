package com.adm.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

// adoption term, home photos
@Entity(name = "tb_req_type")
public class ReqType implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;
    private String i18nPlaceholder;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getI18nPlaceholder() {
        return i18nPlaceholder;
    }

    public void setI18nPlaceholder(String i18nPlaceholder) {
        this.i18nPlaceholder = i18nPlaceholder;
    }
}
