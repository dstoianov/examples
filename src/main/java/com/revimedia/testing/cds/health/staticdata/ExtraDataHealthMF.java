package com.revimedia.testing.cds.health.staticdata;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by dstoianov on 6/3/2014, 7:31 PM.
 */

public class ExtraDataHealthMF {

    private String conditions;
    private String smoker;
    private String hospitalized;
    private String pregnant;
    private String deniedInsurance;
    private String coverageType;
    private String insuranceCompany;
    private String expirationDateMonth;
    private String insuredSinceYear;
    private String insuredSinceMonths;
    private String houseHoldIncome;
    private String heightFT;
    private String heightInch;
    private String weight;
    private String preExistingConditions;
    private String areYouInsured;
    private String[] conditionsList = {"AidsHiv", "Alzheimer", "Diabetes", "Liver_Disease", "Alcohol Abuse", "Asthma", "Cancer", "Cholesterol", "Depression", "Heart_Disease", "High_Blood_Pressure", "Kidney_Disease", "Mental_Illness", "Pulmonary_Disease", "Vascular_Disease", "Stroke"};
    private String[] smokerList = {"Yes", "No"};
    private String[] coverageTypeList = {"COBRA", "Dental Only", "Discount Plan", "Individual Family", "Maternity Only", "Medicaid", "Medicare Supplement", "Prescription Only", "Short Term", "Vision Only"};
    private String[] insuranceCompanyList = {"Currently not insured", "AETNA", "AFLAC", "American Family", "American Republic", "Ameriprise", "Assurant", "Blue Cross / Blue Shield", "Cigna", "Farm Bureau/Farm Family/Rural", "Golden Rule Insurance", "Health Net", "Health Plus of America", "Humana", "Mega/Midwest", "State Farm", "Uni care", "United HealthCare", "US Health Group", "Company not listed"};
    private String[] houseHoldIncomeList = {"Below $30,000", "$30,000 - $44,999", "$45,000 - $59,999", "$60,000 - $74,999", "Above $75,000"};
    private String[] expirationDateMonthList = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private String[] insuredSinceYearList = {"1", "2", "3", "4", "5", "5+"};
    //    private String[] insuredSinceYearList = {"1", "2", "3", "4", "5", "10"};
    private String[] heightFTList = {"4", "5", "6", "7"};
    private String[] heightInchList = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};

    public ExtraDataHealthMF() {
        this.conditions = getRandomValueFromList(conditionsList);
        this.smoker = getRandomValueFromList(smokerList);
        this.hospitalized = getRandomValueFromList(smokerList);
        this.pregnant = getRandomValueFromList(smokerList);
        this.areYouInsured = getRandomValueFromList(smokerList);
        ;
        this.deniedInsurance = getRandomValueFromList(smokerList);
        this.preExistingConditions = getRandomValueFromList(smokerList);
        this.coverageType = getRandomValueFromList(coverageTypeList);
        this.insuranceCompany = getRandomValueFromList(insuranceCompanyList);
        this.expirationDateMonth = getRandomValueFromList(expirationDateMonthList);
        this.houseHoldIncome = getRandomValueFromList(houseHoldIncomeList);
        this.insuredSinceYear = getRandomValueFromList(insuredSinceYearList);
        this.insuredSinceMonths = getRandomValueFromList(heightInchList);
        this.heightFT = getRandomValueFromList(heightFTList);
        this.heightInch = getRandomValueFromList(heightInchList);
        setWeight();
    }

    public String getConditions() {
        return conditions;
    }

    public String getSmoker() {
        return smoker;
    }

    public String getHospitalized() {
        return hospitalized;
    }

    public String getPregnant() {
        return pregnant;
    }

    public String getAreYouInsured() {
        return areYouInsured;
    }

    public String getDeniedInsurance() {
        return deniedInsurance;
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

    public String getInsuredSinceYear() {
        return insuredSinceYear;
    }

    public String getInsuredSinceMonths() {
        return insuredSinceMonths;
    }

    public String getHeightFT() {
        return heightFT;
    }

    public String getHeightInch() {
        return heightInch;
    }

    public String getWeight() {
        return weight;
    }

    public String getPreExistingConditions() {
        return preExistingConditions;
    }

    public String getHouseHoldIncome() {
        return houseHoldIncome;
    }

    public void setHouseHoldIncome(String houseHoldIncome) {
        this.houseHoldIncome = houseHoldIncome;
    }

    private void setWeight() {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 100; i <= 250; i += 5) {
            list.add(i);
        }
        int weight = list.get((new Random()).nextInt(list.size()));
        this.weight = Integer.toString(weight);
    }

    public String getRandomValueFromList(String[] list) {
        Random r = new Random();
        return list[r.nextInt(list.length)];
    }

    @Override
    public String toString() {
        return //"StaticDataHealthMF{" +
                "\nconditions='" + conditions + '\'' +
                        ",\nsmoker='" + smoker + '\'' +
                        ",\nhospitalized='" + hospitalized + '\'' +
                        ",\npregnant='" + pregnant + '\'' +
                        ",\ndeniedInsurance='" + deniedInsurance + '\'' +
                        ",\ncoverageType='" + coverageType + '\'' +
                        ",\ninsuranceCompany='" + insuranceCompany + '\'' +
                        ",\nexpirationDateMonth='" + expirationDateMonth + '\'' +
                        ",\ninsuredSinceYear='" + insuredSinceYear + '\'' +
                        ",\ninsuredSinceMonths='" + insuredSinceMonths + '\'' +
                        ",\nhouseHoldIncome='" + houseHoldIncome + '\'' +
                        ",\nheightFT='" + heightFT + '\'' +
                        ",\nheightInch='" + heightInch + '\'' +
                        ",\nweight='" + weight + '\'';
        //'}';
    }
}