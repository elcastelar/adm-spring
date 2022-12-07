package com.adm.enums;

public enum PetTamperType {
    PLAYFUL("playful"),
    SHY("shy"),
    CALM("calm"),
    GUARD("guard"),
    LAZY("lazy"),
    AFFECTIONATE("affectionate");

    private final String description;

    PetTamperType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
