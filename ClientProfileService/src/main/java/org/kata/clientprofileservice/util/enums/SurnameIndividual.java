package org.kata.clientprofileservice.util.enums;

public enum SurnameIndividual {
    Smith,
    Johnson,
    Williams,
    Jones,
    Brown,
    Davis,
    Miller,
    Wilson,
    Moore,
    Taylor;

    public String toStringSurname() {
        return this.name();
    }
}
