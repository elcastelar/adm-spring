package com.adm.enums;

public enum PetRegisterType {

    ADOPTION("adoption") {
    },

    SPONSORSHIP("sponsorship") {
    },

    AID("aid") {
    };

    public final String description;

    PetRegisterType(String description) {
        this.description = description;
    }

    public boolean isAid() {
        return this == AID;
    }

    public boolean isSponsorship() {
        return this == SPONSORSHIP;
    }

    public boolean isAdoption() {
        return this == ADOPTION;
    }
}