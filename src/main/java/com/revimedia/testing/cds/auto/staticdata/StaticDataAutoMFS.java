package com.revimedia.testing.cds.auto.staticdata;

import java.util.Random;

/**
 * Created by dstoianov on 4/29/2014, 7:31 PM.
 */
public class StaticDataAutoMFS {


    private String[] insuranceCompany = {"21st century", "AAA", "AARP", "AFLAC", "Allstate",
            "American Family", "Amica", "Auto Club Insurance Company", "Country Insurance and Financial Services",
            "Esurance", "Farmers", "GEICO", "The Hartford", "Liberty Mutual", "Nationwide",
            "Progressive", "State Farm", "Travelers", "USAA", "Company not listed"};
    private String[] insuredSince = {"1", "2", "3", "4", "5", "5+"};
    private String[] residenceType = {"Dorm / Student housing", "I am renting", "My own house", "Other", "With my parents"};
    private String[] maritalStatus = {"Divorced", "Married", "Separated", "Single", "Widowed"};
    private String[] creditRating = {"Excellent", "Good", "Some Problems", "Major Problems"};
    private String[] education = {"Associate Degree", "Bachelors Degree", "Doctorate Degree", "High school diploma", "Masters Degree", "None", "Other", "Some College"};


    private String getRandomFromList(String[] list) {
        Random r = new Random();
        return list[r.nextInt(list.length)];
    }
}
