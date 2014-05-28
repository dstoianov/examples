package com.revimedia.testing.cds.auto.staticdata;

import com.revimedia.testing.beans.auto.ContactDataType;
import com.revimedia.testing.beans.auto.DriverType;
import com.revimedia.testing.beans.auto.LeadDataType;
import com.revimedia.testing.configuration.dto.Contact;
import com.revimedia.testing.configuration.helpers.DataHelper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Funker on 29.05.14.
 */
public class ObjectExtractor {

    public static Contact getContact(LeadDataType leadDataType) {
        ContactDataType contactData = leadDataType.getContactData();
        DriverType driver = leadDataType.getQuoteRequest().getDrivers().getDriver();

        Contact contact = new Contact();
        contact.setFirstName(contactData.getFirstName());
        contact.setLastName(contactData.getLastName());
        contact.setGender(driver.getGender());
        contact.setAddress(contactData.getAddress());
        contact.setEmailAddress(contactData.getEmailAddress());
        contact.setZipCode(contactData.getZipCode());
        contact.setCity(contactData.getCity());
        contact.setState(contactData.getState());

        String phoneNumber = contactData.getPhoneNumber();
        String s1 = phoneNumber.substring(0, 3);
        String s2 = phoneNumber.substring(3, 6);
        String s3 = phoneNumber.substring(6);

        contact.setPhoneNumber(s1 + "-" + s2 + "-" + s3);
        //560-384-7995 is(5656565656)
        // assertThat(DataHelper.phoneTransformation(contact.getPhoneNumber()), is(   ));


        //May 8, 1977 is(1990-01-01)
        assertThat(DataHelper.dateTransformation(contact.getBirthDate()), is(driver.getBirthDate()));

        return contact;
    }
}
