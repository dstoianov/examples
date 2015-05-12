package com.revimedia.testing.configuration.dto;

/**
 * Created by Funker on 07.11.2014, 23:56.
 */
public class PrePop {

    //  http://docs.revimedia.com/display/RVMD/ReviMedia+URL+Pre-pop+Parameters

    private String insuranceCompany = "insurancecompany=";
    private String dailyMileage = "dailymileage=";
    private String annualMileage = "annualmiles=";
    private String year = "year=";
    private String make = "make=";
    private String model = "model=";
    private String primaryUse = "use=";
    private String coverageType = "cov=";
    private String maritalStatus = "marital=";
    private String homeOwner = "resi=";
    private String education = "edu=";
    private String creditRating = "credit=";
    private String expirationDate = "expire="; //expire=10-10-2014, (in MM-DD-YYYY format), field Expiration date (for currently insured)
    private String howLongHaveYouLivedHere = "resiyrs=";
    private String yearsInsured = "insyrs=";

    public PrePop() {
    }


    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public String getDailyMileage() {
        return dailyMileage;
    }

    public String getAnnualMileage() {
        return annualMileage;
    }

    public String getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getPrimaryUse() {
        return primaryUse;
    }

    public String getCoverageType() {
        return coverageType;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public String getHomeOwner() {
        return homeOwner;
    }

    public String getEducation() {
        return education;
    }

    public String getCreditRating() {
        return creditRating;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getHowLongHaveYouLivedHere() {
        return howLongHaveYouLivedHere;
    }

    public String getYearsInsured() {
        return yearsInsured;
    }
}
