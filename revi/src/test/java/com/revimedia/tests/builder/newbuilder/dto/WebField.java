package com.revimedia.tests.builder.newbuilder.dto;

/**
 * Created by Funker on 10.08.2015.
 */
public enum WebField {

    IS_LIVING_HERE("IsLivingHere"),
    YEARS_AT_RESIDENCE("YearsAtResidence"),
    PROPERTY_ZIP_CODE("PropertyZipCode"),
    ADDRESS1("Address1"),

    YAEAR("Year"),
    MAKE("Make"),
    MODEL("Model"),

    HAS_SYSTEM("HasSystem"),
    CURRENT_SECURITY_SYSTEM_COMPANY("CurrentSecuritySystemCompany"),

    EXPIRATION_DATE("ExpirationDate"),
    OWN_RENTED("OwnRented"),
    AUTHORIZED_FOR_PROPERTY_CHANGES("AuthorizedForPropertyChanges"),
    INSURANCE_COMPANY("InsuranceCompany"),
    INSURED_SINCE("InsuredSince"),

    HEIGHT_FT("Height_FT"),
    HEIGHT_INCH("Height_Inch"),;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    private String name;

    WebField(String name) {
        this.name = name;
    }
}
