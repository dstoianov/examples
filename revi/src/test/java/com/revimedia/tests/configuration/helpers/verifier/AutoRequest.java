package com.revimedia.tests.configuration.helpers.verifier;

import com.revimedia.testing.beans.auto.*;
import com.revimedia.testing.cds.auto.staticdata.ExtraDataAutoMFS;
import com.revimedia.testing.cds.auto.staticdata.ExtraDataAutoP;
import com.revimedia.testing.configuration.dto.Contact;
import com.revimedia.testing.configuration.helpers.DataHelper;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;

/**
 * Created by dstoianov on 6/5/2014, 1:16 PM.
 */

public class AutoRequest {

    public static void verify(Contact contact, LeadDataType leadDataType) {
        ContactDataType contactData = leadDataType.getContactData();
        DriverType driver = leadDataType.getQuoteRequest().getDrivers().getDriver();

        assertThat(contactData.getFirstName(), is(contact.getFirstName()));
        assertThat(contactData.getLastName(), is(contact.getLastName()));
        assertThat(contactData.getPhoneNumber(), is(DataHelper.phoneTransformation(contact.getPhoneNumber())));
        assertThat(contactData.getAddress(), is(contact.getAddress()));
        assertThat(contactData.getEmailAddress(), equalToIgnoringCase(contact.getEmailAddress()));
        assertThat(contactData.getZipCode(), is(contact.getZipCode()));
        assertThat(driver.getGender(), is(contact.getGender()));
        assertThat(driver.getBirthDate(), is(DataHelper.dateTransformationAsXMLData(contact.getBirthDate())));
    }

    public static void verify(ExtraDataAutoMFS extraData, LeadDataType leadDataType) {
        ContactDataType contactData = leadDataType.getContactData();
        DriverType driver = leadDataType.getQuoteRequest().getDrivers().getDriver();
        CurrentPolicyType currentPolicy = leadDataType.getQuoteRequest().getInsurance().getCurrentPolicy();
        VehicleType vehicleType = leadDataType.getQuoteRequest().getVehicles().getVehicle().get(0);

        assertThat(driver.getCreditRating(), is(extraData.getCreditRating()));
        assertThat(driver.getMaritalStatus(), is(extraData.getMaritalStatus()));
        assertThat(driver.getEducation(), is(extraData.getEducation()));
        assertThat(currentPolicy.getInsuranceCompany(), is(extraData.getInsuranceCompany()));
        if (!extraData.getInsuranceCompany().equalsIgnoreCase("Currently not insured")) {
            assertThat(currentPolicy.getInsuredSince(), is(DataHelper.dateTransformInsuredSince(extraData.getInsuredSince())));
        }
        assertThat(contactData.getResidenceType(), is(extraData.getResidenceType()));
        assertThat(vehicleType.getYear(), is(extraData.getYear()));
        assertThat(vehicleType.getMake(), is(extraData.getMake()));
        String model = (extraData.getModel()).replace(".", "-");
        assertThat(vehicleType.getModel(), is(model));
    }

    public static void verify(ExtraDataAutoP extraData, LeadDataType leadDataType) {
        ContactDataType contactData = leadDataType.getContactData();
        DriverType driver = leadDataType.getQuoteRequest().getDrivers().getDriver();
        InsuranceType insurance = leadDataType.getQuoteRequest().getInsurance();
        VehicleType vehicleType = leadDataType.getQuoteRequest().getVehicles().getVehicle().get(0);

        assertThat(vehicleType.getYear(), is(extraData.getYear()));
        assertThat(vehicleType.getMake(), is(extraData.getMake()));
        String model = (extraData.getModel()).replace(".", "-");
        assertThat(vehicleType.getModel(), is(model));
        assertThat(vehicleType.getLeased(), is(extraData.getLeased()));
        assertThat(vehicleType.getPrimaryUse(), is(extraData.getPrimaryUse()));
        assertThat(vehicleType.getDailyMileage(), is(DataHelper.transformDailyMileage(extraData.getDailyMileage())));
        assertThat(vehicleType.getAnnualMiles(), is(extraData.getAnnualMiles()));
        assertThat(insurance.getRequestedPolicy().getCoverageType(), is(extraData.getCoverageType()));
        assertThat(insurance.getCurrentPolicy().getInsuranceCompany(), is(extraData.getInsuranceCompany()));
        if (!extraData.getInsuranceCompany().equalsIgnoreCase("Currently not insured")) {
            String expirationDate = insurance.getCurrentPolicy().getExpirationDate();
            String expectedExpiration = DataHelper.dateTransformExpirationDate(extraData.getExpirationDateMonth());
            assertThat(expirationDate, is(expectedExpiration));

            String insuredSince = insurance.getCurrentPolicy().getInsuredSince();
            String expectedSince = DataHelper.dateTransformInsuredSince(extraData.getInsuredSinceYears());
            assertThat(insuredSince, is(expectedSince));
        }
        assertThat(driver.getMaritalStatus(), is(extraData.getMaritalStatus()));
        if (driver.getOccupation().equalsIgnoreCase("Employeed")) {
            extraData.setOccupation("Employeed");
        }
        assertThat(driver.getOccupation(), is(extraData.getOccupation()));

        assertThat(driver.getCreditRating(), is(extraData.getCreditRating()));
        assertThat(driver.getEducation(), is(extraData.getEducation()));
        assertThat(driver.getAgeLicensed(), is(extraData.getAgeLicensed()));
        assertThat(contactData.getResidenceType(), is(extraData.getResidenceType()));
        assertThat(contactData.getYearsAtResidence(), is(extraData.getYearsAtResidence()));
        assertThat(contactData.getMonthsAtResidence(), is(extraData.getMonthsAtResidence()));
    }

    public static void verifyMob(ExtraDataAutoP extraData, LeadDataType leadDataType) {
        ContactDataType contactData = leadDataType.getContactData();
        DriverType driver = leadDataType.getQuoteRequest().getDrivers().getDriver();
        InsuranceType insurance = leadDataType.getQuoteRequest().getInsurance();
        VehicleType vehicleType = leadDataType.getQuoteRequest().getVehicles().getVehicle().get(0);

        assertThat(vehicleType.getYear(), is(extraData.getYear()));
        assertThat(vehicleType.getMake(), is(extraData.getMake()));
        String model = (extraData.getModel()).replace(".", "-");
        assertThat(vehicleType.getModel(), is(model));
        assertThat(vehicleType.getAnnualMiles(), is(extraData.getAnnualMiles()));

        if (extraData.getCurrentlyInsured().equalsIgnoreCase("Yes")) {
            assertThat(insurance.getCurrentPolicy().getInsuranceCompany(), is(extraData.getInsuranceCompany()));
            String expirationDate = insurance.getCurrentPolicy().getExpirationDate();
            String expectedExpiration = DataHelper.dateTransformExpirationDate(extraData.getExpirationDateMonth());
            assertThat(expirationDate, is(expectedExpiration));
            String insuredSince = insurance.getCurrentPolicy().getInsuredSince();
            String expectedSince = DataHelper.dateTransformInsuredSince(extraData.getInsuredSinceYears());
            assertThat(insuredSince, is(expectedSince));
        }
        assertThat(driver.getMaritalStatus(), is(extraData.getMaritalStatus()));
        if (driver.getOccupation().equalsIgnoreCase("Employeed")) {
            extraData.setOccupation("Employeed");
        }
        assertThat(driver.getOccupation(), is(extraData.getOccupation()));
        assertThat(driver.getCreditRating(), is(extraData.getCreditRating()));
        assertThat(driver.getEducation(), is(extraData.getEducation()));
        assertThat(contactData.getResidenceType(), is(extraData.getResidenceType()));
    }


    public static void verifyA(ExtraDataAutoP extraData, LeadDataType leadDataType, String url) {
        ContactDataType contactData = leadDataType.getContactData();
        DriverType driver = leadDataType.getQuoteRequest().getDrivers().getDriver();
        InsuranceType insurance = leadDataType.getQuoteRequest().getInsurance();
        VehicleType vehicleType = leadDataType.getQuoteRequest().getVehicles().getVehicle().get(0);

        if (url.contains("?style=s")) {
            // according to the spec for [auto/a/?style=s] http://docs.revimedia.com/pages/viewpage.action?pageId=3637399
            extraData.setLeased("Yes");
            extraData.setPrimaryUse("Commute To/From Work");
            extraData.setAnnualMiles("12500");
            extraData.setExpirationDateMonth(DataHelper.getCurrentDatePlus1Month());
        }

//Fields from Step 1
        assertThat(vehicleType.getYear(), is(extraData.getYear()));
        assertThat(vehicleType.getMake(), is(extraData.getMake()));
        assertThat(vehicleType.getModel(), is((extraData.getModel()).replace(".", "-")));
//Fields from Step 2
        assertThat(vehicleType.getOwnership(), is(extraData.getLeased()));
        assertThat(vehicleType.getPrimaryUse(), is(extraData.getPrimaryUse()));
        assertThat(vehicleType.getAnnualMiles(), is(extraData.getAnnualMiles()));
//Fields from Step 3
        assertThat(insurance.getRequestedPolicy().getCoverageType(), is(extraData.getCoverageType()));
        if (extraData.getCurrentlyInsured().equalsIgnoreCase("No")) {
            extraData.setInsuranceCompany("Currently not insured");
            assertThat(insurance.getCurrentPolicy().getInsuranceCompany(), is(extraData.getInsuranceCompany()));
        } else {
            assertThat(insurance.getCurrentPolicy().getInsuranceCompany(), is(extraData.getInsuranceCompany()));
            if (url.contains("?style=s")) {
//                assertThat(insurance.getCurrentPolicy().getExpirationDate(), is(extraData.getExpirationDateMonth()));
                assertThat(insurance.getCurrentPolicy().getExpirationDate(), is(DateFormatUtils.format(DateUtils.addMonths(new Date(), 1), "yyyy-MM-dd", Locale.US)));
            } else {
                assertThat(insurance.getCurrentPolicy().getExpirationDate(), is(DataHelper.dateTransformExpirationDate(extraData.getExpirationDateMonth())));
            }
            assertThat(insurance.getCurrentPolicy().getInsuredSince(), is(DataHelper.dateTransformInsuredSince(extraData.getInsuredSinceYears())));
        }
//Fields from Step 4
        assertThat(driver.getMaritalStatus(), is(extraData.getMaritalStatus()));
        if (driver.getOccupation().equalsIgnoreCase("Employeed")) {
            extraData.setOccupation("Employeed");
        }
        assertThat(driver.getOccupation(), is(extraData.getOccupation()));
        assertThat(driver.getEducation(), is(extraData.getEducation()));
        assertThat(driver.getCreditRating(), is(extraData.getCreditRating()));
        if (extraData.getHomeOwner().equalsIgnoreCase("yes")) {
            extraData.setResidenceType("My own house");
        } else {
            extraData.setResidenceType("I am renting");
        }
        assertThat(contactData.getResidenceType(), is(extraData.getResidenceType()));
    }

    public static void verifyUpsell(Contact contact, LeadDataType leadDataType) {
        assertThat(leadDataType.getContactData().getFirstName(), is(contact.getFirstName()));
        assertThat(leadDataType.getContactData().getLastName(), is(contact.getLastName()));
    }
}
