package com.revimedia.tests.builder.newbuilder.dto;

/**
 * Created by Funker on 10.08.2015.
 */
public enum WebField {

    YEARS_AT_RESIDENCE("YearsAtResidence"),
    ADDRESS1("Address1"),

    YAEAR("Year"),
    MAKE("Make"),
    MODEL("Model"),

    EXPIRATION_DATE("ExpirationDate"),
    MONTH("Month"),

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
