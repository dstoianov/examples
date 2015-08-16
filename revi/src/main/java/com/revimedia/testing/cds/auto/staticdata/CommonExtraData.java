package com.revimedia.testing.cds.auto.staticdata;

import com.revimedia.testing.configuration.helpers.DataHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Denis Stoianov on 6/12/2014, 12:42 PM
 * E-mail: denis@revimedia.com
 * http://lxpbase.stagingrevi.com/lxpbase?command=lead.manual&vertical=auto#OneWayDistance
 */
public class CommonExtraData {
    private static final String[] insuranceCompanyList = {"Currently not insured", "21st century", "AAA", "AARP", "AFLAC", "Allstate", "American Family", "Amica", "Auto Club Insurance Company", "Country Insurance and Financial Services", "Esurance", "Farmers", "GEICO", "The Hartford", "Liberty Mutual", "Nationwide", "Progressive", "State Farm", "Travelers", "USAA"};
    private static final String[] insuranceCompanyHomeList = {"AAA", "AIG", "Allied", "Allstate", "American Family", "Amica", "Auto Owners", "Chubb", "Country Insurance and Financial Services", "Erie Insurance", "Farmers", "Geico", "The Hartford", "Liberty Mutual", "Nationwide", "Progressive", "SAFECO", "State Farm", "Travelers", "USAA", "Company not listed"};
    private static final String[] expirationDateMonthList = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private static final String[] insuredSinceList = {"1", "2", "3", "4", "5", "5+"};
    private static final String[] residenceTypeList = {"Dorm / Student housing", "I am renting", "My own house", "Other", "With my parents"};
    private static final String[] maritalStatusList = {"Divorced", "Married", "Separated", "Single", "Widowed"};
    private static final String[] creditRatingList = {"Excellent", "Good", "Some Problems", "Major Problems"};
    private static final String[] educationList = {"Associate Degree", "Bachelors Degree", "Doctorate Degree", "High school diploma", "Masters Degree", "None", "Other", "Some College"};
    private static final String[] yesNoList = {"Yes", "No"};
    private static final String[] primaryUseList = {"Commute To/From Work", "Commute To/From School", "Business Individual", "Business Corporate", "Pleasure", "Farm", "Government"};
    private static final String[] dailyMileageList = {"0", "1-6", "7-10", "11-19", "20-39", "40-99", "100+"};
    private static final String[] annualMilesList = {"2500", "7500", "12500", "15000"};
    private static final String[] coverageTypeList = {"Superior Protection", "Standard Protection", "Basic Protection", "State Minimum"};
    private static final String[] occupationList = {"Employed", "Government", "Homemaker", "Retired", "Student Living w/ Parents", "Student not Living w/ Parents", "Unemployed", "Military", "Retail", "Sales", "Marketing", "IT", "Medical", "Unknown", "BusinessOwner", "Student", "SalesInside", "SalesOutside", "Scientist", "OtherTechnical", "MilitaryEnlisted", "Architect"};
    private static final String[] yearsAtResidenceList = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "15", "20", "25", "30"};
    private static final String[] monthsAtResidenceList = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
    private static final String[] coverageAmountList = {"< $50,000", "$50,000", "$100,000", "$150,000", "$200,000", "$250,000", "$300,000", "$350,000", "$400,000", "$450,000", "$500,000", "$500,000+"};
    private static final String[] deductibleList = {"250", "500", "1,000", "2,000"};
    private static final String[] personalLiabilityCoverageList = {"100,000", "300,000", "1,000,000"};
    private static final String[] storiesList = {"One story", "Bi level", "Two story", "Tri level", "Other"};
    private static final String[] bedroomsList = {"1", "2", "3", "4", "5", "6", "7+"};
    private static final String[] bathroomsList = {"1", "1.5", "2", "2.5", "3", "3.5", "4", "4+"};
    private static final String[] garageList = {"Attached 1 car", "Attached 2 car", "Attached 3 car", "Attached carpool", "Detached 1 car", "Detached 2 car", "Detached 3 car", "Detached carpool", "No garage", "Other"};
    private static final String[] propertyTypeList = {"Single family", "Apartment", "Duplex", "Condo", "Townhome", "Mobile home"};
    private static final String[] squareFootageList = {"< 1000 sq.ft", "1000 – 2000 sq.ft", "2001 – 3000 sq.ft", "3001 – 4000 sq.ft", "4001 – 5000 sq.ft", "5000+ sq.ft"};
    private static Map<Integer, String[]> carMap;

    static {
        carMap = new HashMap<>();
        carMap.put(0, new String[]{"2003", "FORD", "Focus LX"});
        carMap.put(1, new String[]{"1993", "SATURN", "S-Series SW1"});
        carMap.put(2, new String[]{"1983", "VOLVO", "240 GLT Turbo"});
        carMap.put(3, new String[]{"2012", "AUDI", "TT RS 2.5 quattro"});
        carMap.put(4, new String[]{"2009", "BMW", "M3"});
        carMap.put(5, new String[]{"2002", "BUICK", "Regal LS"});
        carMap.put(6, new String[]{"2012", "MERCEDES-BENZ", "CLS-Class CLS550"});
        carMap.put(7, new String[]{"2012", "HONDA", "Accord EX-L w/Navi"});
        carMap.put(8, new String[]{"2012", "KIA", "Soul+"});
    }

    private static String getRandomValueFromList(String[] list) {
        return list[(new Random()).nextInt(list.length)];
    }

    public static String getRandomInsuranceCompany() {
        return getRandomValueFromList(insuranceCompanyList);
    }

    public static String getRandomInsuredSince() {
        return getRandomValueFromList(insuredSinceList);
    }

    public static String getRandomResidenceType() {
        return getRandomValueFromList(residenceTypeList);
    }

    public static String getRandomMaritalStatus() {
        return getRandomValueFromList(maritalStatusList);
    }

    public static String getRandomCreditRating() {
        return getRandomValueFromList(creditRatingList);
    }

    public static String getRandomEducation() {
        return getRandomValueFromList(educationList);
    }

    public static String[] getRandomCar() {
        return carMap.get((new Random()).nextInt(carMap.size()));
    }

    public static String getRandomYesNo() {
        return getRandomValueFromList(yesNoList);
    }

    public static String getRandomPrimaryUse() {
        return getRandomValueFromList(primaryUseList);
    }

    public static String getRandomDailyMileage() {
        return getRandomValueFromList(dailyMileageList);
    }

    public static String getRandomAnnualMileage() {
        return getRandomValueFromList(annualMilesList);
    }

    public static String getRandomCoverageType() {
        return getRandomValueFromList(coverageTypeList);
    }

    public static String getRandomExpirationDateMonth() {
        return getRandomValueFromList(expirationDateMonthList);
    }

    public static String getRandomOccupation() {
        return getRandomValueFromList(occupationList);
    }

    public static String getRandomAgeLicensed() {
        return Integer.toString(DataHelper.randInt(16, 90));
    }

    public static String getRandomYearsAtResidence() {
        return getRandomValueFromList(yearsAtResidenceList);
    }

    public static String getRandomMonthsAtResidence() {
        return getRandomValueFromList(monthsAtResidenceList);
    }

    public static String getRandomCoverageAmount() {
        return getRandomValueFromList(coverageAmountList);
    }

    public static String getRandomDeductible() {
        return getRandomValueFromList(deductibleList);
    }

    public static String getRandomPersonalLiabilityCoverage() {
        return getRandomValueFromList(personalLiabilityCoverageList);
    }

    public static String getRandomStories() {
        return getRandomValueFromList(storiesList);
    }

    public static String getRandomBedrooms() {
        return getRandomValueFromList(bedroomsList);
    }

    public static String getRandomBathrooms() {
        return getRandomValueFromList(bathroomsList);
    }

    public static String getRandomGarage() {
        return getRandomValueFromList(garageList);
    }

    public static String getRandomPropertyType() {
        return getRandomValueFromList(propertyTypeList);
    }

    public static String getRandomYearBuilt() {
        return String.valueOf(DataHelper.randInt(1900, 2014));
    }

    public static String getRandomSquareFootage() {
        return getRandomValueFromList(squareFootageList);
    }

    public static String getRandomInsuranceCompanyHome() {
        return getRandomValueFromList(insuranceCompanyHomeList);
    }

}
