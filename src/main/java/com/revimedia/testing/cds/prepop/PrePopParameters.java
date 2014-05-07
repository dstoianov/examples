package com.revimedia.testing.cds.prepop;

import com.revimedia.testing.cds.auto.staticdata.StaticDataAutoMFS;
import com.revimedia.testing.configuration.dto.Contact;
import org.apache.log4j.Logger;

/**
 * Created by dstoianov on 5/7/2014, 5:11 PM.
 */
public class PrePopParameters {
    protected static Logger log = Logger.getLogger(PrePopParameters.class);

    private static String[] autoMfs = {"firstname", "lastname", "address1", "city", "state", "zipcode", "phone", "email", "birthdate", "gender", "insurancecompany"};


    private static String createURLWithPrePopAutoMFS(Contact contact, StaticDataAutoMFS staticData) {
        String s = "/?" + autoMfs[0] + "=" + contact.getFirstName() + "&" +
                autoMfs[1] + "=" + contact.getLastName() + "&" +
                autoMfs[2] + "=" + contact.getAddress() + "&" +
                autoMfs[3] + "=" + contact.getCity() + "&" +
                autoMfs[4] + "=" + contact.getState() + "&" +
                autoMfs[5] + "=" + contact.getZipCode() + "&" +
                autoMfs[6] + "=" + contact.getPhoneNumber() + "&" +
                autoMfs[7] + "=" + contact.getEmailAddress() + "&" +
                autoMfs[8] + "=" + contact.getBirthDate() + "&" +
                autoMfs[9] + "=" + contact.getGender() + "&" +
                autoMfs[10] + "=" + staticData.getInsuranceCompany();
        return s;
    }


    public static String getAutoMFS(String currentUrl, Contact contact, StaticDataAutoMFS staticData) {
        if (currentUrl.substring(currentUrl.length() - 1).equals("/")) {
            currentUrl = currentUrl.substring(0, currentUrl.length() - 1);
        }
        String result = currentUrl + createURLWithPrePopAutoMFS(contact, staticData);
        log.info("Loadable  URL is: " + result);
        return result;
    }
}
