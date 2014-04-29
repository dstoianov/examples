package com.revimedia.testing.cds.auto.staticdata;

import java.util.Random;

/**
 * Created by dstoianov on 4/29/2014, 7:31 PM.
 */
public class StaticDataAutoMFS {

    private String insuranceCompany;
    private String insuredSince;
    private String residenceType;
    private String maritalStatus;
    private String creditRating;
    private String education;
    private String year;
    private String make;
    private String model;

    private String[] insuranceCompanyList = {"21st century", "AAA", "AARP", "AFLAC", "Allstate",
            "American Family", "Amica", "Auto Club Insurance Company", "Country Insurance and Financial Services",
            "Esurance", "Farmers", "GEICO", "The Hartford", "Liberty Mutual", "Nationwide",
            "Progressive", "State Farm", "Travelers", "USAA", "Company not listed"};
    private String[] insuredSinceList = {"1", "2", "3", "4", "5", "5+"};
    private String[] residenceTypeList = {"Dorm / Student housing", "I am renting", "My own house", "Other", "With my parents"};
    private String[] maritalStatusList = {"Divorced", "Married", "Separated", "Single", "Widowed"};
    private String[] creditRatingList = {"Excellent", "Good", "Some Problems", "Major Problems"};
    private String[] educationList = {"Associate Degree", "Bachelors Degree", "Doctorate Degree", "High school diploma", "Masters Degree", "None", "Other", "Some College"};


    private String getRandomFromList(String[] list) {
        Random r = new Random();
        return list[r.nextInt(list.length)];
    }
}
