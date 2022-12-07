package com.adm.enums;

public enum PetSex {
    MALE("male"),
    FEMALE("female");

    private final String description;

    PetSex(String description) {
        this.description = description;
    }

    boolean isMale() {
        return this == MALE;
    }

    boolean isFemale() {
        return this == FEMALE;
    }

    public String getDescription() {
        return description;
    }
}
