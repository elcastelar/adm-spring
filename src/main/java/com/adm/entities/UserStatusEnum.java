package com.adm.entities;

public enum UserStatusEnum {
    ACTIVE("active"),
    INACTIVE("inactive"),
    SUSPENDED("suspended"),
    BANNED("banned");

    private String name;

    UserStatusEnum(String active) {
        this.name = active;
    }

}
