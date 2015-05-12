package com.revimedia.testing.cds.auto.staticdata;

import com.revimedia.testing.beans.auto.ContactDataType;
import com.revimedia.testing.beans.auto.DriverType;
import com.revimedia.testing.beans.auto.LeadDataType;
import com.revimedia.testing.configuration.dto.Contact;
import com.revimedia.testing.configuration.helpers.DataHelper;

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
        contact.setPhoneNumber(DataHelper.phoneTransformationAddHyphens(contactData.getPhoneNumber()));
        contact.setBirthDate(DataHelper.dateTransformationAsContactData(driver.getBirthDate()));
        return contact;
    }
}
