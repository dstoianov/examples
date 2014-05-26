package com.revimedia.testing.cds.prepop;

import com.revimedia.testing.cds.auto.staticdata.StaticDataAutoMFS;
import com.revimedia.testing.configuration.dto.Contact;
import org.apache.log4j.Logger;

/**
 * Created by dstoianov on 5/7/2014, 5:11 PM.
 */
public class PrePopParameters {
    protected static Logger log = Logger.getLogger(PrePopParameters.class);

    private static String[] autoMfsContact = {"firstname", "lastname", "address1", "city", "state", "zipcode", "phone", "email", "birthdate", "gender", "insurancecompany"};
    private static String[] autoOfferParams = {"firstname", "lastname", "address1", "city", "state", "zipcode", "phone", "email", "birthdate", "gender", "insurancecompany"};


    private static String createURLWithPrePopAutoMFS(Contact contact, StaticDataAutoMFS staticData) {
        String s = "/?" + autoMfsContact[0] + "=" + contact.getFirstName() + "&" +
                autoMfsContact[1] + "=" + contact.getLastName() + "&" +
                autoMfsContact[2] + "=" + contact.getAddress() + "&" +
                autoMfsContact[3] + "=" + contact.getCity() + "&" +
                autoMfsContact[4] + "=" + contact.getState() + "&" +
                autoMfsContact[5] + "=" + contact.getZipCode() + "&" +
                autoMfsContact[6] + "=" + contact.getPhoneNumber() + "&" +
                autoMfsContact[7] + "=" + contact.getEmailAddress() + "&" +
                autoMfsContact[8] + "=" + contact.getBirthDate() + "&" +
                autoMfsContact[9] + "=" + contact.getGender() + "&" +
                autoMfsContact[10] + "=" + staticData.getInsuranceCompany();
        return s;
    }


    public static String generateURLForAutoMFSWithContactAndStatic(String currentUrl, Contact contact, StaticDataAutoMFS staticData) {
        if (currentUrl.substring(currentUrl.length() - 1).equals("/")) {
            currentUrl = currentUrl.substring(0, currentUrl.length() - 1);
        }
        String result = currentUrl + createURLWithPrePopAutoMFS(contact, staticData);
        log.info("Loadable  URL is: " + result);
        return result;
    }


}
