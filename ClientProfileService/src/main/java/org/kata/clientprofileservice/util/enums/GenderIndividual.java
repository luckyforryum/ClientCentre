package org.kata.clientprofileservice.util.enums;

public enum GenderIndividual {
    Male,
    Female,
    NonBinary,
    Unspecified,
    Transgender,
    Binary,
    Bigender,
    Agender,
    Androgynous,
    Polyflexible;

    public String toStringGender() {
        return this.name();
    }
}
