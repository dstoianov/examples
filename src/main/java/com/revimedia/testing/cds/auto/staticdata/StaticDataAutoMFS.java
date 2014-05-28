package com.revimedia.testing.cds.auto.staticdata;

import java.util.*;

/**
 * Created by dstoianov on 4/29/2014, 7:31 PM.
 */
public class StaticDataAutoMFS implements Cloneable {

    public StaticDataAutoMFS() {
        this.insuranceCompany = getRandomValueFromList(insuranceCompanyList);
        this.insuredSince = getRandomValueFromList(insuredSinceList);
        this.residenceType = getRandomValueFromList(residenceTypeList);
        this.maritalStatus = getRandomValueFromList(maritalStatusList);
        this.creditRating = getRandomValueFromList(creditRatingList);
        this.education = getRandomValueFromList(educationList);
        setRandomCar();
    }

    public StaticDataAutoMFS(String insuranceCompany, String insuredSince, String residenceType, String maritalStatus, String creditRating, String education, String year, String make, String model) {
        this.insuranceCompany = insuranceCompany;
        this.insuredSince = insuredSince;
        this.residenceType = residenceType;
        this.maritalStatus = maritalStatus;
        this.creditRating = creditRating;
        this.education = education;
        this.year = year;
        this.make = make;
        this.model = model;
    }

    public StaticDataAutoMFS clone() throws CloneNotSupportedException {
        return (StaticDataAutoMFS) super.clone();
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public String getInsuredSince() {
        return insuredSince;
    }

    public String getResidenceType() {
        return residenceType;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public String getCreditRating() {
        return creditRating;
    }

    public String getEducation() {
        return education;
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

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public void setInsuredSince(String insuredSince) {
        this.insuredSince = insuredSince;
    }

    public void setResidenceType(String residenceType) {
        this.residenceType = residenceType;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public void setCreditRating(String creditRating) {
        this.creditRating = creditRating;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    private String insuranceCompany;
    private String insuredSince;
    private String residenceType;
    private String maritalStatus;
    private String creditRating;
    private String education;
    private String year;
    private String make;
    private String model;

    private String[] insuranceCompanyList = {"Currently not insured", "21st century", "AAA", "AARP", "AFLAC", "Allstate",
            "American Family", "Amica", "Auto Club Insurance Company", "Country Insurance and Financial Services",
            "Esurance", "Farmers", "GEICO", "The Hartford", "Liberty Mutual", "Nationwide",
            "Progressive", "State Farm", "Travelers", "USAA", "Company not listed"};
    private String[] insuredSinceList = {"1", "2", "3", "4", "5", "5+"};
    private String[] residenceTypeList = {"Dorm / Student housing", "I am renting", "My own house", "Other", "With my parents"};
    private String[] maritalStatusList = {"Divorced", "Married", "Separated", "Single", "Widowed"};
    private String[] creditRatingList = {"Excellent", "Good", "Some Problems", "Major Problems"};
    private String[] educationList = {"Associate Degree", "Bachelors Degree", "Doctorate Degree", "High school diploma", "Masters Degree", "None", "Other", "Some College"};

    private void setRandomCar() {
        Map<Integer, String[]> car = new HashMap<>();
        car.put(0, new String[]{"2003", "FORD", "Focus LX"});
        car.put(1, new String[]{"1993", "SATURN", "S-Series SW1"});
        car.put(2, new String[]{"1983", "VOLVO", "240 GLT Turbo"});
        car.put(3, new String[]{"2012", "AUDI", "TT RS 2.5 quattro"});
        car.put(4, new String[]{"2009", "BMW", "M3"});
        car.put(5, new String[]{"2002", "BUICK", "Regal LS"});
        car.put(6, new String[]{"2012", "MERCEDES-BENZ", "CLS-Class CLS550"});
        car.put(7, new String[]{"2012", "HONDA", "Accord EX-L w/Navi"});
        car.put(8, new String[]{"2012", "KIA", "Soul Soul+"});

        String[] strings = car.get((new Random()).nextInt(car.size()));
        this.year = strings[0];
        this.make = strings[1];
        this.model = strings[2];
    }

    private String getRandomValueFromList(String[] list) {
        Random r = new Random();
        return list[r.nextInt(list.length)];
    }

    @Override
    public String toString() {
        return //"\n\n--------------- Static Data Auto MFS ----------------- " +
                "\ninsuranceCompany='" + insuranceCompany + '\'' +
                        ",\ninsuredSince='" + insuredSince + '\'' +
                        ",\nresidenceType='" + residenceType + '\'' +
                        ",\nmaritalStatus='" + maritalStatus + '\'' +
                        ",\ncreditRating='" + creditRating + '\'' +
                        ",\neducation='" + education + '\'' +
                        ",\nyear='" + year + '\'' +
                        ",\nmake='" + make + '\'' +
                        "\nmodel='" + model + '\'';
    }


}