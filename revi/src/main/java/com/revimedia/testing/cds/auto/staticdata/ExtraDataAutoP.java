package com.revimedia.testing.cds.auto.staticdata;

/**
 * Created by Denis Stoianov on 6/12/2014, 12:42 PM
 * E-mail: denis@revimedia.com
 */
public class ExtraDataAutoP {

    private String year;
    private String make;
    private String model;
    private String leased;
    private String primaryUse;
    private String dailyMileage;
    private String annualMiles;
    private String coverageType;
    private String currentlyInsured;
    private String insuranceCompany;
    private String expirationDateMonth;
    private String insuredSinceYears;
    private String maritalStatus;
    private String occupation;
    private String education;
    private String creditRating;
    private String ageLicensed;
    private String residenceType;
    private String yearsAtResidence;
    private String monthsAtResidence;
    private String hadIncidents;
    private String homeOwner;

    public ExtraDataAutoP() {
        String[] car = CommonExtraData.getRandomCar();
        this.year = car[0];
        this.make = car[1];
        this.model = car[2];
        this.leased = CommonExtraData.getRandomYesNo();
        this.primaryUse = CommonExtraData.getRandomPrimaryUse();
        this.dailyMileage = CommonExtraData.getRandomDailyMileage();
        this.annualMiles = CommonExtraData.getRandomAnnualMileage();
        this.coverageType = CommonExtraData.getRandomCoverageType();
        this.currentlyInsured = CommonExtraData.getRandomYesNo();
        this.insuranceCompany = CommonExtraData.getRandomInsuranceCompany();
        this.expirationDateMonth = CommonExtraData.getRandomExpirationDateMonth();
        this.insuredSinceYears = CommonExtraData.getRandomInsuredSince();
        this.maritalStatus = CommonExtraData.getRandomMaritalStatus();
        this.occupation = CommonExtraData.getRandomOccupation();
        this.education = CommonExtraData.getRandomEducation();
        this.creditRating = CommonExtraData.getRandomCreditRating();
        this.ageLicensed = CommonExtraData.getRandomAgeLicensed();
        this.residenceType = CommonExtraData.getRandomResidenceType();
        this.yearsAtResidence = CommonExtraData.getRandomYearsAtResidence();
        this.monthsAtResidence = CommonExtraData.getRandomMonthsAtResidence();
        this.hadIncidents = CommonExtraData.getRandomYesNo();
        this.homeOwner = CommonExtraData.getRandomYesNo();

    }

    public ExtraDataAutoP(String year, String make, String model, String leased, String primaryUse, String dailyMileage, String annualMiles, String coverageType, String insuranceCompany, String expirationDateMonth, String insuredSinceYears, String maritalStatus, String occupation,
                          String education, String creditRating, String ageLicensed, String residenceType, String yearsAtResidence, String monthsAtResidence) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.leased = leased;
        this.primaryUse = primaryUse;
        this.dailyMileage = dailyMileage;
        this.annualMiles = annualMiles;
        this.coverageType = coverageType;
        this.insuranceCompany = insuranceCompany;
        this.expirationDateMonth = expirationDateMonth;
        this.insuredSinceYears = insuredSinceYears;
        this.maritalStatus = maritalStatus;
        this.occupation = occupation;
        this.education = education;
        this.creditRating = creditRating;
        this.ageLicensed = ageLicensed;
        this.residenceType = residenceType;
        this.yearsAtResidence = yearsAtResidence;
        this.monthsAtResidence = monthsAtResidence;
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

    public String getLeased() {
        return leased;
    }

    public void setLeased(String leased) {
        this.leased = leased;
    }

    public String getPrimaryUse() {
        return primaryUse;
    }

    public void setPrimaryUse(String primaryUse) {
        this.primaryUse = primaryUse;
    }

    public String getDailyMileage() {
        return dailyMileage;
    }

    public String getAnnualMiles() {
        return annualMiles;
    }

    public void setAnnualMiles(String annualMiles) {
        this.annualMiles = annualMiles;
    }

    public String getCoverageType() {
        return coverageType;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public String getExpirationDateMonth() {
        return expirationDateMonth;
    }

    public void setExpirationDateMonth(String expirationDateMonth) {
        this.expirationDateMonth = expirationDateMonth;
    }

    public String getInsuredSinceYears() {
        return insuredSinceYears;
    }

    public void setInsuredSinceYears(String insuredSinceYears) {
        this.insuredSinceYears = insuredSinceYears;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getCreditRating() {
        return creditRating;
    }

    public String getAgeLicensed() {
        return ageLicensed;
    }

    public String getResidenceType() {
        return residenceType;
    }

    public void setResidenceType(String residenceType) {
        this.residenceType = residenceType;
    }

    public String getYearsAtResidence() {
        return yearsAtResidence;
    }

    public String getMonthsAtResidence() {
        return monthsAtResidence;
    }

    public String getCurrentlyInsured() {
        return currentlyInsured;
    }

    public void setCurrentlyInsured(String currentlyInsured) {
        this.currentlyInsured = currentlyInsured;
    }

    public String getHadIncidents() {
        return hadIncidents;
    }

    public void setHadIncidents(String hadIncidents) {
        this.hadIncidents = hadIncidents;
    }

    public String getHomeOwner() {
        return homeOwner;
    }

    public void setHomeOwner(String homeOwner) {
        this.homeOwner = homeOwner;
    }

    @Override
    public String toString() {
        return "\nyear='" + year + '\'' +
                ", \nmake='" + make + '\'' +
                ", \nmodel='" + model + '\'' +
                ", \nleased='" + leased + '\'' +
                ", \nprimaryUse='" + primaryUse + '\'' +
                ", \ndailyMileage='" + dailyMileage + '\'' +
                ", \nannualMiles='" + annualMiles + '\'' +
                ", \ncoverageType='" + coverageType + '\'' +
                ", \ncurrentlyInsured='" + currentlyInsured + '\'' +
                ", \ninsuranceCompany='" + insuranceCompany + '\'' +
                ", \nexpirationDateMonth='" + expirationDateMonth + '\'' +
                ", \ninsuredSinceYears='" + insuredSinceYears + '\'' +
                ", \nmaritalStatus='" + maritalStatus + '\'' +
                ", \noccupation='" + occupation + '\'' +
                ", \neducation='" + education + '\'' +
                ", \ncreditRating='" + creditRating + '\'' +
                ", \nageLicensed='" + ageLicensed + '\'' +
                ", \nresidenceType='" + residenceType + '\'' +
                ", \nyearsAtResidence='" + yearsAtResidence + '\'' +
                ", \nmonthsAtResidence='" + monthsAtResidence + '\'' +
                ", \nhadIncidents='" + hadIncidents + '\'' +
                ", \nhomeOwner='" + homeOwner + '\'' + "\n";
    }
}
