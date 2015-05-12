package com.revimedia.tests.configuration.helpers.verifier;

import com.revimedia.testing.beans.health.ContactDataType;
import com.revimedia.testing.beans.health.InsuranceType;
import com.revimedia.testing.beans.health.LeadDataType;
import com.revimedia.testing.beans.health.PersonType;
import com.revimedia.testing.cds.health.staticdata.ExtraDataHealthMF;
import com.revimedia.testing.configuration.dto.Contact;
import com.revimedia.testing.configuration.helpers.DataHelper;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hamcrest.CoreMatchers;

import java.util.Date;
import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by dstoianov on 6/5/2014, 1:16 PM.
 */

public class HealthRequest {

    public static void verify(Contact contact, LeadDataType leadDataType) {
        ContactDataType contactData = leadDataType.getContactData();
        PersonType person = leadDataType.getQuoteRequest().getPersons().getPerson();

        assertThat(contactData.getFirstName(), is(contact.getFirstName()));
        assertThat(contactData.getLastName(), is(contact.getLastName()));
        assertThat(contactData.getPhoneNumber(), is(DataHelper.phoneTransformation(contact.getPhoneNumber())));
        assertThat(contactData.getAddress(), is(contact.getAddress()));
        assertThat(contactData.getEmailAddress(), is(contact.getEmailAddress()));
        assertThat(contactData.getZipCode(), is(contact.getZipCode()));
        assertThat(person.getGender(), is(contact.getGender()));
        assertThat(person.getBirthDate(), is(DataHelper.dateTransformationAsXMLData(contact.getBirthDate())));
    }

    public static void verifyMF(ExtraDataHealthMF staticData, LeadDataType leadDataType, String gender) {
        PersonType person = leadDataType.getQuoteRequest().getPersons().getPerson();
        InsuranceType insurance = leadDataType.getQuoteRequest().getInsurance();

        assertThat(person.getConditions(), is(staticData.getConditions()));
        assertThat(person.getMedicalHistory().getSmoker(), is(staticData.getSmoker()));
        assertThat(person.getMedicalHistory().getHospitalized(), is(staticData.getHospitalized()));
        if (gender.equalsIgnoreCase("Female")) {
            assertThat(person.getMedicalHistory().getPregnant(), is(staticData.getPregnant()));
        } else {
            assertThat(person.getMedicalHistory().getPregnant(), is("No"));
        }
        assertThat(person.getDeniedInsurance(), is(staticData.getDeniedInsurance()));
        assertThat(insurance.getRequestedPolicy().getCoverageType(), is(staticData.getCoverageType()));

        if (staticData.getInsuranceCompany().equalsIgnoreCase("Uni Care") || staticData.getInsuranceCompany().equalsIgnoreCase("United HealthCare")) {
            staticData.setInsuranceCompany("Company not listed");
        }

        assertThat(insurance.getCurrentPolicy().getInsuranceCompany(), is(staticData.getInsuranceCompany()));

        if (!staticData.getInsuranceCompany().equalsIgnoreCase("Currently not insured")) {
            String expirationDate = insurance.getCurrentPolicy().getExpirationDate();
            String expectedExpiration = DataHelper.dateTransformExpirationDate(staticData.getExpirationDateMonth());
            assertThat(expirationDate, is(expectedExpiration));

            String insuredSince = insurance.getCurrentPolicy().getInsuredSince();
            String expectedSince = DataHelper.dateTransformInsuredSince(staticData.getInsuredSinceYear(), staticData.getInsuredSinceMonths());
            assertThat(insuredSince, is(expectedSince));
        }
        assertThat(person.getHeightFT(), is(staticData.getHeightFT()));
        assertThat(person.getHeightInch(), is(staticData.getHeightInch()));
        assertThat(person.getHouseHoldIncome(), is(staticData.getHouseHoldIncome()));
    }


    public static void verifyInsuranceMF(InsuranceType insurance, ExtraDataHealthMF extraData) {
        if (!extraData.getInsuranceCompany().equalsIgnoreCase("Currently not insured")) {
            String expirationDate = insurance.getCurrentPolicy().getExpirationDate();
            String expectedExpiration = DataHelper.dateTransformExpirationDate(extraData.getExpirationDateMonth());
            assertThat(expirationDate, is(expectedExpiration));

            String insuredSince = insurance.getCurrentPolicy().getInsuredSince();
            String expectedSince = DataHelper.dateTransformInsuredSince(extraData.getInsuredSinceYear(), extraData.getInsuredSinceMonths());
            assertThat(insuredSince, is(expectedSince));
        }
    }

    public static void verifyInsuranceA(InsuranceType insurance, ExtraDataHealthMF extraData) {
        if (extraData.getAreYouInsured().equalsIgnoreCase("yes")) {
            String expectedSince = DataHelper.dateTransformInsuredSince(extraData.getInsuredSinceYear(), "0");
            assertThat(insurance.getCurrentPolicy().getInsuredSince(), is(expectedSince));
        }
    }


    public static void verify(ExtraDataHealthMF extraData, LeadDataType leadDataType) {
        PersonType person = leadDataType.getQuoteRequest().getPersons().getPerson();
        InsuranceType insurance = leadDataType.getQuoteRequest().getInsurance();

        // person
        assertThat(person.getHeightFT(), is(extraData.getHeightFT()));
        assertThat(person.getHeightInch(), is(extraData.getHeightInch()));
        assertThat(person.getWeight(), is(extraData.getWeight()));
        assertThat(person.getHouseHoldIncome(), is(extraData.getHouseHoldIncome()));
        assertThat(person.getDeniedInsurance(), is(extraData.getDeniedInsurance()));

        if (extraData.getPreExistingConditions().equalsIgnoreCase("YES")) {
            assertThat(person.getConditions(), is(extraData.getConditions()));
        }

        // medical
        assertThat(person.getMedicalHistory().getSmoker(), is(extraData.getSmoker()));
        assertThat(person.getMedicalHistory().getHospitalized(), is(extraData.getHospitalized()));
//      assertThat(person.getMedicalHistory().getPregnant(), is(extraData.getPregnant()));
        if (person.getGender().equalsIgnoreCase("Female")) {
            assertThat(person.getMedicalHistory().getPregnant(), is(extraData.getPregnant()));
        } else {
            assertThat(person.getMedicalHistory().getPregnant(), is("No"));
        }

        // insurance
        if (extraData.getAreYouInsured().equalsIgnoreCase("YES")) {
            if (extraData.getInsuranceCompany().equalsIgnoreCase("Uni Care") || extraData.getInsuranceCompany().equalsIgnoreCase("United HealthCare")) {
                extraData.setInsuranceCompany("Company not listed");
            }
            assertThat(insurance.getCurrentPolicy().getInsuranceCompany(), is(extraData.getInsuranceCompany()));
        }

        assertThat(insurance.getRequestedPolicy().getCoverageType(), is(extraData.getCoverageType()));
    }

    public static void verifyAliases(LeadDataType leadDataType) {
        PersonType person = leadDataType.getQuoteRequest().getPersons().getPerson();

        // Assert all  Default Alias Answers for this campaign
        assertThat(leadDataType.getContactData().getResidenceType(), CoreMatchers.is("My own house"));
        assertThat(leadDataType.getContactData().getYearsAtResidence(), CoreMatchers.is("1"));
        assertThat(leadDataType.getContactData().getMonthsAtResidence(), CoreMatchers.is("0"));
        assertThat(person.getMaritalStatus(), CoreMatchers.is("Married"));
        assertThat(person.getRelationshipToApplicant(), CoreMatchers.is("Self"));
        assertThat(person.getUSResidence(), CoreMatchers.is("True"));
        assertThat(person.getStudent(), CoreMatchers.is("false"));
        assertThat(person.getOccupation(), CoreMatchers.is("Employeed"));
        assertThat(person.getEducation(), CoreMatchers.is("Bachelors Degree"));
        assertThat(person.getMedicalHistory().getAlcoholabstain(), CoreMatchers.is("Yes"));

        String expirationDate = leadDataType.getQuoteRequest().getInsurance().getCurrentPolicy().getExpirationDate();
        String expectedExpiration = DateFormatUtils.format(DateUtils.addMonths(new Date(), 1), "yyyy-MM-dd", Locale.US);
        assertThat(expirationDate, CoreMatchers.is(expectedExpiration));
    }

}
