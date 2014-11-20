package com.revimedia.tests.configuration.helpers.verifier;


import com.revimedia.testing.beans.home.*;
import com.revimedia.testing.cds.home.staticdata.ExtraDataHomeMF;
import com.revimedia.testing.configuration.dto.Contact;
import com.revimedia.testing.configuration.helpers.DataHelper;

import java.util.Calendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;

/**
 * Created by Denis Stoianov on 05/08/2014, 1:40 PM
 * E-mail: denis@revimedia.com
 */
public class HomeRequest {

    public static void verify(Contact contact, LeadDataType leadDataType) {
        ContactDataType contactData = leadDataType.getContactData();
        PersonType person = leadDataType.getQuoteRequest().getPerson();

        assertThat(contactData.getFirstName(), is(contact.getFirstName()));
        assertThat(contactData.getLastName(), is(contact.getLastName()));
        assertThat(contactData.getPhoneNumber(), is(DataHelper.phoneTransformation(contact.getPhoneNumber())));
        assertThat(contactData.getAddress(), is(contact.getAddress()));
        assertThat(contactData.getEmailAddress(), equalToIgnoringCase(contact.getEmailAddress()));
        assertThat(contactData.getZipCode(), is(contact.getZipCode()));
        assertThat(person.getGender(), is(contact.getGender()));
        assertThat(person.getBirthDate(), is(DataHelper.dateTransformationAsXMLData(contact.getBirthDate())));
    }

    public static void verify(ExtraDataHomeMF extraData, LeadDataType leadDataType) {
        HomeType home = leadDataType.getQuoteRequest().getHomes().getHome();
        InsuranceType insurance = leadDataType.getQuoteRequest().getInsurance();

//        assertThat(home.getIsLivingHere(), is(extraData.getIsLivingHere()));
        if (extraData.getIsLivingHere().equalsIgnoreCase("Yes")) {
            assertThat(leadDataType.getContactData().getYearsAtResidence(), is(extraData.getYearsAtResidence()));
        } else {
            assertThat(home.getPropertyAddress().getZipCode(), is(extraData.getPropertyZipCode()));
            assertThat(home.getPropertyAddress().getAddress1(), is(extraData.getAddress1()));
        }
        assertThat(home.getDeductible(), is(extraData.getDeductible()));
        assertThat(insurance.getRequestedPolicy().getPersonalLiabilityCoverage(), is(extraData.getPersonalLiabilityCoverage()));

        commonChecking(extraData, leadDataType);
    }

    public static void verifyMobu(ExtraDataHomeMF extraData, LeadDataType leadDataType) {
        HomeType home = leadDataType.getQuoteRequest().getHomes().getHome();
        InsuranceType insurance = leadDataType.getQuoteRequest().getInsurance();

        assertThat(home.getPropertyType(), is(extraData.getPropertyType()));
        assertThat(home.getPropertyOwned(), is(extraData.getPropertyOwned()));
        if (extraData.getCurrentlyInsured().equalsIgnoreCase("Yes")) {
            assertThat(insurance.getCurrentPolicy().getInsuranceCompany(), is(extraData.getInsuranceCompany()));
            assertThat(insurance.getCurrentPolicy().getInsuredSince(), is(DataHelper.dateTransformInsuredSince(extraData.getInsuredSinceYear())));
            assertThat(insurance.getCurrentPolicy().getExpirationDate(), is(DataHelper.dateTransformExpirationDate(extraData.getExpirationDateMonth())));
        }
        assertThat(home.getClaimsOrLossesPast5Years(), is(extraData.getClaimsOrLossesPast5Years()));
        assertThat(home.getYearBuilt(), is(extraData.getYearBuilt()));
        assertThat(home.getSquareFootage(), is(DataHelper.squareFootage.get(extraData.getSquareFootage())));

        checkStories(home, extraData);
    }

    public static void verifyAliases(LeadDataType leadDataType, String sqFootage) {
//      spec you can find here  http://docs.revimedia.com/pages/viewpage.action?pageId=2851006
        HomeType home = leadDataType.getQuoteRequest().getHomes().getHome();
        PersonType person = leadDataType.getQuoteRequest().getPerson();

        verifyYesNoAnswers(home);

        assertThat(person.getMaritalStatus(), is("Married"));
        assertThat(person.getOccupation(), is("Employeed"));
        assertThat(person.getEducation(), is("Bachelors Degree"));
        assertThat(home.getMunicipalLocation(), is("Inside city limits"));
        assertThat(home.getFoundation(), is("Slab"));
        assertThat(home.getHomeSecurity(), is("None"));
        assertThat(home.getYearUpgraded(), is(String.valueOf(Calendar.getInstance().get(Calendar.YEAR))));
        assertThat(home.getConstructionType(), is("Mostly wood frame"));
        assertThat(home.getRoofType(), is("Asphalt shingle"));
        assertThat(home.getRoofAge(), is("1 - 5 years"));

        //        DwellingValue- (sent to LXP as sq footage x 100)
        int i = Integer.parseInt(DataHelper.squareFootage.get(sqFootage)) * 100;
        assertThat(home.getDwellingValue(), is(String.valueOf(i)));

        assertThat(home.getDog(), is("No"));
        assertThat(home.getDangerousDogBreed(), is("No"));

        assertThat(home.getPrimaryHeating(), is("Gas (Forced air)"));
        assertThat(home.getWiringType(), is("Copper"));
        assertThat(home.getNewlyPurchased(), is("Yes"));
        assertThat(home.getConstructionClass(), is("Standard"));
        assertThat(home.getPanelType(), is("Circuit breaker"));
        assertThat(home.getProximityWater(), is("Not applicable"));
        assertThat(home.getExteriorWalls(), is("Brick"));
        assertThat(home.getFireAlarm(), is("Monitored"));
    }

    public static void verifyAliasesA(LeadDataType leadDataType) {
//        http://docs.revimedia.com/pages/viewpage.action?pageId=3637517
        HomeType home = leadDataType.getQuoteRequest().getHomes().getHome();
        PersonType person = leadDataType.getQuoteRequest().getPerson();
        InsuranceType insurance = leadDataType.getQuoteRequest().getInsurance();
        ContactDataType contact = leadDataType.getContactData();

        verifyYesNoAnswers(home);

        String nowPlusOneMonth = DataHelper.addMonthToNow(1, DataHelper.DATE);

        assertThat("Expiration Date(1 month after current date) ", insurance.getCurrentPolicy().getExpirationDate(),
                is(nowPlusOneMonth.substring(0, nowPlusOneMonth.lastIndexOf("-")) + "-01")
        ); //ExpirationDate	(1 month after current date)

        assertThat(person.getMaritalStatus(), is("Married"));
        assertThat(person.getOccupation(), is("Employeed"));
        assertThat(person.getEducation(), is("Bachelors Degree"));
        assertThat(home.getMunicipalLocation(), is("Inside city limits"));
        assertThat(home.getFoundation(), is("Slab"));
        assertThat(home.getHomeSecurity(), is("None"));
        assertThat(home.getYearUpgraded(), is(String.valueOf(Calendar.getInstance().get(Calendar.YEAR))));
        assertThat(home.getConstructionType(), is("Mostly wood frame"));
        assertThat(home.getRoofType(), is("Asphalt shingle"));
        assertThat(home.getRoofAge(), is("1 - 5 years"));

        assertThat(home.getDog(), is("No"));
        assertThat(home.getDangerousDogBreed(), is("No"));

        assertThat(home.getPrimaryHeating(), is("Gas (Forced air)"));
        assertThat(home.getWiringType(), is("Copper"));
        assertThat(home.getNewlyPurchased(), is("Yes"));
        assertThat(home.getConstructionClass(), is("Standard"));
        assertThat(home.getPanelType(), is("Circuit breaker"));
        assertThat(home.getProximityWater(), is("Not applicable"));
        assertThat(home.getExteriorWalls(), is("Brick"));
        assertThat(home.getFireAlarm(), is("Monitored"));

        assertThat(contact.getMonthsAtResidence(), is("0"));
        assertThat(person.getYearsAtPreviousResidence(), is("1"));
        assertThat(home.getFeatures().getElectricity(), is("Yes"));
        assertThat(insurance.getRequestedPolicy().getInterestedMortgageProtection(), is("Yes"));
        assertThat(insurance.getRequestedPolicy().getCoverageType(), is("Full package"));

//        TODO: this is not an aliases, this have to check in Submit
//        DwellingValue	(sent to LXP as sq footage x 100)
//        ResidenceType (star)	My own house
//        Occupancy (star)(star)	Primary residence
    }

    public static void verifyStyleUp(ExtraDataHomeMF extraData, LeadDataType leadDataType) {
        commonChecking(extraData, leadDataType);
    }

    private static void commonChecking(ExtraDataHomeMF extraData, LeadDataType leadDataType) {
        HomeType home = leadDataType.getQuoteRequest().getHomes().getHome();
        InsuranceType insurance = leadDataType.getQuoteRequest().getInsurance();

        assertThat(home.getPropertyType(), is(extraData.getPropertyType()));
        assertThat("Field 'Year built (ok to estimate)' is incorrect ", home.getYearBuilt(), is(extraData.getYearBuilt()));
        assertThat(home.getSquareFootage(), is(DataHelper.squareFootage.get(extraData.getSquareFootage())));
        assertThat(home.getPropertyOwned(), is(extraData.getPropertyOwned()));
        assertThat(insurance.getRequestedPolicy().getCoverageAmount(), is(DataHelper.coverageAmount.get(extraData.getCoverageAmount())));
        assertThat(insurance.getCurrentPolicy().getInsuranceCompany(), is(extraData.getInsuranceCompany()));
        if (!extraData.getInsuranceCompany().equalsIgnoreCase("Currently not insured")) {
            assertThat(insurance.getCurrentPolicy().getInsuredSince(), is(DataHelper.dateTransformInsuredSince(extraData.getInsuredSinceYear())));
        }
        assertThat(home.getClaimsOrLossesPast5Years(), is(extraData.getClaimsOrLossesPast5Years()));

        checkStories(home, extraData);
    }

    public static void verifyAliasesStyleUp(LeadDataType leadDataType) {
        assertThat(leadDataType.getQuoteRequest().getHomes().getHome().getIsLivingHere(), is("Yes"));
        assertThat(leadDataType.getQuoteRequest().getHomes().getHome().getDeductible(), is("500"));
        assertThat(leadDataType.getQuoteRequest().getInsurance().getRequestedPolicy().getPersonalLiabilityCoverage(), is("300,000"));
        assertThat(leadDataType.getQuoteRequest().getPerson().getYearsAtPreviousResidence(), is("1"));
    }

    public static void verifyA(ExtraDataHomeMF extraData, LeadDataType leadDataType) {
        HomeType home = leadDataType.getQuoteRequest().getHomes().getHome();
        InsuranceType insurance = leadDataType.getQuoteRequest().getInsurance();

//        DwellingValue	(sent to LXP as sq footage x 100)
        String s = DataHelper.squareFootage.get(extraData.getSquareFootage());
        if (s.equalsIgnoreCase("900")) {
            s = "1000";
        } else if (s.equalsIgnoreCase("5500")) {
            s = "5000";
        }
        assertThat(home.getDwellingValue(), is(String.valueOf(Integer.parseInt(s) * 100)));

//        ResidenceType (star)	My own house
        if (extraData.getPropertyOwned().equalsIgnoreCase("Own")) {
            assertThat(leadDataType.getContactData().getResidenceType(), is("My own house"));
        } else {
            assertThat(leadDataType.getContactData().getResidenceType(), is("I am renting"));
        }

//        Occupancy (star)(star)	Primary residence
        if (extraData.getIsLivingHere().equalsIgnoreCase("Yes")) {
            assertThat(home.getOccupancy(), is("Primary residence"));
        } else {
            assertThat(home.getOccupancy(), is("Seasonal residence"));
        }

        // Asserting the rest fields
        assertThat(home.getPropertyType(), is(extraData.getPropertyType()));
        assertThat("Field 'Year built (ok to estimate)' is incorrect ", home.getYearBuilt(), is(extraData.getYearBuilt()));
        assertThat(home.getPropertyOwned(), is(extraData.getPropertyOwned()));

        if (extraData.getIsLivingHere().equalsIgnoreCase("Yes")) {
            assertThat(leadDataType.getContactData().getYearsAtResidence(), is(extraData.getYearsAtResidence()));
        } else {
            assertThat(home.getPropertyAddress().getZipCode(), is(extraData.getPropertyZipCode()));
            assertThat(home.getPropertyAddress().getAddress1(), is(extraData.getAddress1()));
        }

        assertThat(insurance.getRequestedPolicy().getCoverageAmount(), is(DataHelper.coverageAmount.get(extraData.getCoverageAmount())));

        assertThat(home.getDeductible(), is(extraData.getDeductible()));
        assertThat(insurance.getRequestedPolicy().getPersonalLiabilityCoverage(), is(extraData.getPersonalLiabilityCoverage()));
        if (extraData.getCurrentlyInsured().equalsIgnoreCase("Yes")) {
            assertThat(insurance.getCurrentPolicy().getInsuranceCompany(), is(extraData.getInsuranceCompany()));
            assertThat(insurance.getCurrentPolicy().getInsuredSince(), is(DataHelper.dateTransformInsuredSince(extraData.getInsuredSinceYear())));
        }
        checkStories(home, extraData);
    }

    private static void checkStories(HomeType home, ExtraDataHomeMF extraData) {
        assertThat(home.getStories(), is(extraData.getStories()));
        assertThat(home.getBedrooms(), is(extraData.getBedrooms()));
        assertThat(home.getBathrooms(), is(extraData.getBathrooms()));
        assertThat(home.getGarage(), is(extraData.getGarage()));
    }

    private static void verifyYesNoAnswers(HomeType home) {
        assertThat(home.getFeatures().getDeadbolts(), is("Yes"));
        assertThat(home.getFeatures().getFireExtinguisher(), is("Yes"));
        assertThat(home.getFeatures().getNearFireStation(), is("Yes"));
        assertThat(home.getFeatures().getFireHydrant(), is("Yes"));
        assertThat(home.getFeatures().getCopperWaterPipes(), is("Yes"));
        assertThat(home.getFeatures().getCentralAirConditioning(), is("Yes"));

        assertThat(home.getFeatures().getSwimmingPool(), is("No"));
        assertThat(home.getFeatures().getTrampoline(), is("No"));
        assertThat(home.getFeatures().getFloodPlain(), is("No"));
        assertThat(home.getFeatures().getCoveredDeckOrPatio(), is("No"));
        assertThat(home.getFeatures().getFireplace(), is("No"));
        assertThat(home.getFeatures().getIndoorSprinklers(), is("No"));
        assertThat(home.getFeatures().getBrushHazard(), is("No"));
        assertThat(home.getFeatures().getSauna(), is("No"));
        assertThat(home.getFeatures().getHotTub(), is("No"));
        assertThat(home.getFeatures().getWoodburningStove(), is("No"));
        assertThat(home.getFeatures().getSumpPump(), is("No"));
        assertThat(home.getFeatures().getSwimmingPoolIsFenced(), is("No"));
        assertThat(home.getFeatures().getSmokerInHousehold(), is("No"));
    }

    public static void verifyM(ExtraDataHomeMF extraData, LeadDataType leadDataType) {
        HomeType home = leadDataType.getQuoteRequest().getHomes().getHome();
        InsuranceType insurance = leadDataType.getQuoteRequest().getInsurance();

//        ResidenceType (star)	My own house
        if (extraData.getPropertyOwned().equalsIgnoreCase("Own")) {
            assertThat(leadDataType.getContactData().getResidenceType(), is("My own house"));
        } else {
            assertThat(leadDataType.getContactData().getResidenceType(), is("I am renting"));
        }

        // Asserting the rest fields
        assertThat(home.getPropertyType(), is(extraData.getPropertyType()));
        assertThat(home.getYearBuilt(), is(extraData.getYearBuilt()));

        String s = DataHelper.squareFootage.get(extraData.getSquareFootage());
        if (s.equalsIgnoreCase("900")) {
            s = "1000";
        } else if (s.equalsIgnoreCase("5500")) {
            s = "5000";
        }
        assertThat(home.getSquareFootage(), is(s));

        assertThat(home.getPropertyOwned(), is(extraData.getPropertyOwned()));
        assertThat(insurance.getRequestedPolicy().getCoverageAmount(), is(DataHelper.coverageAmount.get(extraData.getCoverageAmount())));

        if (extraData.getCurrentlyInsured().equalsIgnoreCase("Yes")) {
            assertThat(insurance.getCurrentPolicy().getInsuranceCompany(), is(extraData.getInsuranceCompany()));
            assertThat(insurance.getCurrentPolicy().getInsuredSince(), is(DataHelper.dateTransformInsuredSince(extraData.getInsuredSinceYear())));
        }
        checkStories(home, extraData);

        assertThat(leadDataType.getQuoteRequest().getPerson().getCreditRating(), is(extraData.getCreditRating()));
    }
}
