package com.adm.entities;

import java.time.LocalDateTime;

public interface IEntity {

    public Long getId();

    void setCreationDateTime(LocalDateTime now);
}
