package com.revimedia.testing.cds.home.staticdata;

import com.revimedia.testing.cds.auto.staticdata.CommonExtraData;
import com.revimedia.testing.configuration.helpers.DataHelper;

/**
 * Created by Denis Stoianov on 04/08/2014, 2:01 PM
 * E-mail: denis@revimedia.com
 */
public class ExtraDataHomeMF {

    private String propertyType;
    private String yearBuilt;
    private String squareFootage;
    private String propertyOwned;
    private String isLivingHere;
    private String yearsAtResidence;
    private String propertyZipCode;
    private String address1;
    private String creditRating;
    private String coverageAmount;
    private String deductible;
    private String personalLiabilityCoverage;
    private String currentlyInsured; //extra field for home/mobu
    private String insuranceCompany;
    private String insuredSinceYear;
    private String expirationDateMonth; //extra field for home/mobu
    private String claimsOrLossesPast5Years;
    private String replacementCost; //extra field for home/mobu
    private String stories;
    private String bedrooms;
    private String bathrooms;
    private String garage;

    public ExtraDataHomeMF() {
        this.propertyType = CommonExtraData.getRandomPropertyType();
        this.yearBuilt = CommonExtraData.getRandomYearBuilt();
        this.squareFootage = CommonExtraData.getRandomSquareFootage();
        this.propertyOwned = (CommonExtraData.getRandomYesNo().equalsIgnoreCase("yes") ? "Own" : "Rent");
        this.isLivingHere = CommonExtraData.getRandomYesNo();
        this.currentlyInsured = CommonExtraData.getRandomYesNo();
        this.yearsAtResidence = CommonExtraData.getRandomYearsAtResidence();
        this.propertyZipCode = "90222";
        this.address1 = "Property address " + DataHelper.randInt(100, 999);
        this.creditRating = CommonExtraData.getRandomCreditRating();
        this.coverageAmount = CommonExtraData.getRandomCoverageAmount();
        this.deductible = CommonExtraData.getRandomDeductible();
        this.personalLiabilityCoverage = CommonExtraData.getRandomPersonalLiabilityCoverage();
        this.insuranceCompany = CommonExtraData.getRandomInsuranceCompanyHome();
        this.insuredSinceYear = CommonExtraData.getRandomInsuredSince();
        this.expirationDateMonth = CommonExtraData.getRandomExpirationDateMonth();
        this.claimsOrLossesPast5Years = CommonExtraData.getRandomYesNo();
        this.replacementCost = String.valueOf(DataHelper.randInt(1000, 9999));
        this.stories = CommonExtraData.getRandomStories();
        this.bedrooms = CommonExtraData.getRandomBedrooms();
        this.bathrooms = CommonExtraData.getRandomBathrooms();
        this.garage = CommonExtraData.getRandomGarage();
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getYearBuilt() {
        return yearBuilt;
    }

    public void setYearBuilt(String yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public String getSquareFootage() {
        return squareFootage;
    }

    public void setSquareFootage(String squareFootage) {
        this.squareFootage = squareFootage;
    }

    public String getPropertyOwned() {
        return propertyOwned;
    }

    public void setPropertyOwned(String propertyOwned) {
        this.propertyOwned = propertyOwned;
    }

    public String getIsLivingHere() {
        return isLivingHere;
    }

    public void setIsLivingHere(String isLivingHere) {
        this.isLivingHere = isLivingHere;
    }

    public String getYearsAtResidence() {
        return yearsAtResidence;
    }

    public void setYearsAtResidence(String yearsAtResidence) {
        this.yearsAtResidence = yearsAtResidence;
    }

    public String getPropertyZipCode() {
        return propertyZipCode;
    }

    public void setPropertyZipCode(String propertyZipCode) {
        this.propertyZipCode = propertyZipCode;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(String creditRating) {
        this.creditRating = creditRating;
    }

    public String getCoverageAmount() {
        return coverageAmount;
    }

    public void setCoverageAmount(String coverageAmount) {
        this.coverageAmount = coverageAmount;
    }

    public String getDeductible() {
        return deductible;
    }

    public void setDeductible(String deductible) {
        this.deductible = deductible;
    }

    public String getPersonalLiabilityCoverage() {
        return personalLiabilityCoverage;
    }

    public void setPersonalLiabilityCoverage(String personalLiabilityCoverage) {
        this.personalLiabilityCoverage = personalLiabilityCoverage;
    }

    public String getCurrentlyInsured() {
        return currentlyInsured;
    }

    public void setCurrentlyInsured(String currentlyInsured) {
        this.currentlyInsured = currentlyInsured;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public String getInsuredSinceYear() {
        return insuredSinceYear;
    }

    public void setInsuredSinceYear(String insuredSinceYear) {
        this.insuredSinceYear = insuredSinceYear;
    }

    public String getExpirationDateMonth() {
        return expirationDateMonth;
    }

    public void setExpirationDateMonth(String expirationDateMonth) {
        this.expirationDateMonth = expirationDateMonth;
    }

    public String getClaimsOrLossesPast5Years() {
        return claimsOrLossesPast5Years;
    }

    public void setClaimsOrLossesPast5Years(String claimsOrLossesPast5Years) {
        this.claimsOrLossesPast5Years = claimsOrLossesPast5Years;
    }

    public String getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(String replacementCost) {
        this.replacementCost = replacementCost;
    }

    public String getStories() {
        return stories;
    }

    public void setStories(String stories) {
        this.stories = stories;
    }

    public String getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(String bedrooms) {
        this.bedrooms = bedrooms;
    }

    public String getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(String bathrooms) {
        this.bathrooms = bathrooms;
    }

    public String getGarage() {
        return garage;
    }

    public void setGarage(String garage) {
        this.garage = garage;
    }

    @Override
    public String toString() {
        return //"ExtraDataHomeMF{" +
                ",\npropertyType='" + propertyType + '\'' +
                        ",\nyearBuilt='" + yearBuilt + '\'' +
                        ",\nsquareFootage='" + squareFootage + '\'' +
                        ",\npropertyOwned='" + propertyOwned + '\'' +
                        ",\nisLivingHere='" + isLivingHere + '\'' +
                        ",\nyearsAtResidence='" + yearsAtResidence + '\'' +
                        ",\npropertyZipCode='" + propertyZipCode + '\'' +
                        ",\naddress1='" + address1 + '\'' +
                        ",\ncreditRating='" + creditRating + '\'' +
                        ",\ncoverageAmount='" + coverageAmount + '\'' +
                        ",\ndeductible='" + deductible + '\'' +
                        ",\npersonalLiabilityCoverage='" + personalLiabilityCoverage + '\'' +
                        ",\ncurrentlyInsured='" + currentlyInsured + '\'' +
                        ",\ninsuranceCompany='" + insuranceCompany + '\'' +
                        ",\ninsuredSinceYear='" + insuredSinceYear + '\'' +
                        ",\nexpirationDateMonth='" + expirationDateMonth + '\'' +
                        ",\nclaimsOrLossesPast5Years='" + claimsOrLossesPast5Years + '\'' +
                        ",\nreplacementCost='" + replacementCost + '\'' +
                        ",\nstories='" + stories + '\'' +
                        ",\nbedrooms='" + bedrooms + '\'' +
                        ",\nbathrooms='" + bathrooms + '\'' +
                        ",\ngarage='" + garage + '\'';
    }
}
