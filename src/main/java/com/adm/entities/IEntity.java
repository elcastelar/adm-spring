package com.adm.entities;

import java.time.LocalDateTime;

public interface IEntity {

    public Integer getId();

    void setCreationDateTime(LocalDateTime now);
}
