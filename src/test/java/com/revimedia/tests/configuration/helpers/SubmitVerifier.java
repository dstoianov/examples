package com.revimedia.tests.configuration.helpers;

import com.revimedia.testing.beans.auto.*;
import com.revimedia.testing.cds.auto.staticdata.StaticDataAutoMFS;
import com.revimedia.testing.configuration.dto.Contact;
import com.revimedia.testing.configuration.helpers.DataHelper;
import com.revimedia.testing.configuration.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by Funker on 08.05.14.
 */
public class SubmitVerifier {

    public static void verifyResponse(Response response) {
        assertThat(response, allOf(
                hasProperty("_success", equalTo("BaeOK")),
                hasProperty("success", is(true)),
                hasProperty("isWarning", is(false))
        ));
        assertThat(response.getTransactionId().length(), is(36));
    }

    public static void verifyRequest(Contact contact, LeadDataType leadDataType) {
        ContactDataType contactData = leadDataType.getContactData();

        assertThat(contact.getFirstName(), is(contactData.getFirstName()));
        assertThat(contact.getLastName(), is(contactData.getLastName()));
        assertThat(DataHelper.phoneTransformation(contact.getPhoneNumber()), is(contactData.getPhoneNumber()));
        assertThat(contact.getAddress(), is(contactData.getAddress()));
        assertThat(contact.getEmailAddress(), is(contactData.getEmailAddress()));
        assertThat(contact.getZipCode(), is(contactData.getZipCode()));

        DriverType driver = leadDataType.getQuoteRequest().getDrivers().getDriver();
        assertThat(contact.getGender(), is(driver.getGender()));
        assertThat(DataHelper.dateTransformationAsXMLData(contact.getBirthDate()), is(driver.getBirthDate()));
    }

    public static void verifyRequest(StaticDataAutoMFS staticData, LeadDataType leadDataType) {
        ContactDataType contactData = leadDataType.getContactData();
        DriverType driver = leadDataType.getQuoteRequest().getDrivers().getDriver();
        CurrentPolicyType currentPolicy = leadDataType.getQuoteRequest().getInsurance().getCurrentPolicy();
        VehicleType vehicleType = leadDataType.getQuoteRequest().getVehicles().getVehicle().get(0);

        assertThat(staticData.getCreditRating(), is(driver.getCreditRating()));
        assertThat(staticData.getMaritalStatus(), is(driver.getMaritalStatus()));
        assertThat(staticData.getEducation(), is(driver.getEducation()));

        assertThat(staticData.getInsuranceCompany(), is(currentPolicy.getInsuranceCompany()));
        if (!staticData.getInsuranceCompany().equalsIgnoreCase("Currently not insured")) {
            assertThat(DataHelper.dateTransformInsuredSince(staticData.getInsuredSince()), is(currentPolicy.getInsuredSince()));
        }
        assertThat(staticData.getResidenceType(), is(contactData.getResidenceType()));

        assertThat(staticData.getYear(), is(vehicleType.getYear()));
        assertThat(staticData.getMake(), is(vehicleType.getMake()));

        String model = (staticData.getModel()).replace(".", "-");
        assertThat(model, is(vehicleType.getModel()));
    }
}
