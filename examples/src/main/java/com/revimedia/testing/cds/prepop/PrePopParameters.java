package com.revimedia.testing.cds.prepop;

import com.revimedia.testing.cds.auto.staticdata.ExtraDataAutoMFS;
import com.revimedia.testing.cds.auto.staticdata.ExtraDataAutoP;
import com.revimedia.testing.cds.health.staticdata.ExtraDataHealthMF;
import com.revimedia.testing.cds.home.staticdata.ExtraDataHomeMF;
import com.revimedia.testing.configuration.dto.Contact;
import com.revimedia.testing.configuration.dto.PrePop;
import com.revimedia.testing.configuration.helpers.CampaignsHelper;
import com.revimedia.testing.configuration.helpers.DataHelper;
import org.apache.log4j.Logger;

/**
 * Created by dstoianov on 5/7/2014, 5:11 PM.
 */
public class PrePopParameters {
    protected static Logger log = Logger.getLogger(PrePopParameters.class);
    private static PrePop prePop;
    private static StringBuffer buffer;

    // one HashMap object
    private static String[] contactFields = {"firstname", "lastname", "address1", "city", "state", "zipcode", "phone", "email", "birthdate", "gender"};
    private static String[] autoMfs = {"insurancecompany"};
    private static String[] healthMf = autoMfs;
    private static String[] autoP = {"insurancecompany", "dailymileage", "annualmiles", "year", "make", "model", "use", "cov", "marital", "resi", "resiyrs", "edu", "credit", "expire", "insyrs"};
    private static String[] homeMf = {"resiyrs", "credit", "insurancecompany", "insyrs"};
    private static String[] autoMfsContact = {"firstname", "lastname", "address1", "city", "state", "zipcode", "phone", "email", "birthdate", "gender", "insurancecompany"};
    private static String[] healtA = {"firstname", "lastname", "address1", "city", "state", "zipcode", "phone", "email", "birthdate", "gender", "insurancecompany", "insyrs", "expire"};

    public static String getURLWithPrePopHealthM(String url, Contact contact, ExtraDataHealthMF extraData) {
        String contactURL = convertContactToUrl(contact);
        prePop = new PrePop();
        buffer = new StringBuffer();
        String toDelete = contactURL.substring(contactURL.indexOf("gender"));
        contactURL = contactURL.replaceAll(toDelete, "");

        buffer.append(prePop.getInsuranceCompany() + extraData.getInsuranceCompany()).append("&");
        buffer.append(prePop.getYearsInsured() + getInsuredSinceYears(extraData.getInsuredSinceYear())).append("&");
        buffer.append(prePop.getExpirationDate() + DataHelper.dateTransformExpirationDate(extraData.getExpirationDateMonth()));
        return url + "?" + contactURL + "&" + buffer.toString();
    }

    public static String getURLWithPrePopHealthA(String url, Contact contact, ExtraDataHealthMF extraData) {
        String contactURL = convertContactToUrl(contact);
        prePop = new PrePop();
        buffer = new StringBuffer();

        buffer.append(prePop.getInsuranceCompany() + extraData.getInsuranceCompany()).append("&");
        buffer.append(prePop.getYearsInsured() + getInsuredSinceYears(extraData.getInsuredSinceYear())).append("&");
        buffer.append(prePop.getExpirationDate() + DataHelper.dateTransformExpirationDate(extraData.getExpirationDateMonth()));
        return url + "?" + contactURL + "&" + buffer.toString();
    }

    private static String createURLWithPrePopAutoMFS(Contact contact, ExtraDataAutoMFS staticData) {
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

    public static String generateURLForAutoMFSWithContactAndExtra(String currentUrl, Contact contact, ExtraDataAutoMFS staticData) {
        if (currentUrl.substring(currentUrl.length() - 1).equals("/")) {
            currentUrl = currentUrl.substring(0, currentUrl.length() - 1);
        }
        String result = currentUrl + createURLWithPrePopAutoMFS(contact, staticData);
        log.info("Loadable URL is: " + result);
        return result;
    }

    public static String generateURLForThisData(String url, Contact contact, ExtraDataHealthMF extraData) {
        String contactURL = convertContactToUrl(contact);
        String sd = convertStaticDataToUrl(extraData);
        String result = url + "?" + contactURL + "&" + sd;
        log.info("Loadable URL is: " + result);
        return result;
    }

    public static String generateURLForHomeSecurityData(String url, Contact contact) {
        String contactURL = convertContactToUrl(contact);
        String result = url + "?" + contactURL;
        log.info("Loadable URL is: " + result);
        return result;
    }

    public static String generateURLForHomeData(String url, Contact contact, ExtraDataHomeMF extraData) {
        String contactURL = convertContactToUrl(contact);
        prePop = new PrePop();
        buffer = new StringBuffer();
        buffer.append(prePop.getHowLongHaveYouLivedHere() + extraData.getYearsAtResidence()).append("&");
        buffer.append(prePop.getCreditRating() + extraData.getCreditRating()).append("&");
        buffer.append(prePop.getInsuranceCompany() + extraData.getInsuranceCompany()).append("&");
        buffer.append(prePop.getYearsInsured() + getInsuredSinceYears(extraData.getInsuredSinceYear()));
        return url + "?" + contactURL + "&" + buffer.toString();
    }

    private static String convertStaticDataToUrl(ExtraDataHealthMF staticData) {
        String s = healthMf[0] + "=" + staticData.getInsuranceCompany();
        return s;
    }

    private static String convertContactToUrl(Contact contact) {
        String s = contactFields[0] + "=" + contact.getFirstName() + "&" +
                contactFields[1] + "=" + contact.getLastName() + "&" +
                contactFields[2] + "=" + contact.getAddress() + "&" +
                contactFields[3] + "=" + contact.getCity() + "&" +
                contactFields[4] + "=" + contact.getState() + "&" +
                contactFields[5] + "=" + contact.getZipCode() + "&" +
                contactFields[6] + "=" + contact.getPhoneNumber() + "&" +
                contactFields[7] + "=" + contact.getEmailAddress() + "&" +
                contactFields[8] + "=" + contact.getBirthDate() + "&" +
                contactFields[9] + "=" + contact.getGender();
        return s;
    }

    public static String generateURLForAutoPWithContactAndExtra(String url, Contact contact, ExtraDataAutoP extraData) {
        String contactURL = convertContactToUrl(contact);
        String campaignName = CampaignsHelper.getCampaignName(url);
        String sd = "";
        if (campaignName.contains("mobv5")) {
            sd = convertExtraDataToUrlAutoPMob(extraData);
        } else if (campaignName.contains("auto/a")) {
            sd = convertExtraDataToUrlAutoA(extraData);
        } else {
            sd = convertExtraDataToUrlAutoP(extraData);
        }
        String result = url + "?" + contactURL + "&" + sd;
        log.info("Loadable URL is: " + result);
        return result;
    }

    private static String convertExtraDataToUrlAutoA(ExtraDataAutoP extraData) {
        String s =
                autoP[0] + "=" + extraData.getInsuranceCompany() + "&" +
//                autoP[1] + "=" + extraData.getDailyMileage() + "&" +
                        autoP[2] + "=" + extraData.getAnnualMiles() + "&" +
                        autoP[6] + "=" + extraData.getPrimaryUse() + "&" +
                        autoP[8] + "=" + extraData.getMaritalStatus() + "&" +
                        autoP[7] + "=" + extraData.getCoverageType() + "&" +
                        autoP[9] + "=" + extraData.getResidenceType() + "&" +
                        autoP[10] + "=" + extraData.getYearsAtResidence() + "&" +
                        autoP[11] + "=" + extraData.getEducation() + "&" +
                        autoP[12] + "=" + extraData.getCreditRating() + "&" +
                        autoP[13] + "=" + DataHelper.dateTransformExpirationDate(extraData.getExpirationDateMonth()) + "&" +
                        autoP[14] + "=" + getInsuredSinceYears(extraData.getInsuredSinceYears());
        return s;
    }

    private static String convertExtraDataToUrlAutoPMob(ExtraDataAutoP extraData) {
        return autoP[9] + "=" + extraData.getResidenceType() + "&" + getSimilarData(extraData);
    }

    private static String convertExtraDataToUrlAutoP(ExtraDataAutoP extraData) {
        String s = getSimilarData(extraData) + "&" +
                autoP[0] + "=" + extraData.getInsuranceCompany() + "&" +
                autoP[1] + "=" + extraData.getDailyMileage() + "&" +
                autoP[7] + "=" + extraData.getCoverageType() + "&" +
                autoP[9] + "=" + extraData.getResidenceType() + "&" +
                autoP[10] + "=" + extraData.getYearsAtResidence() + "&" +
                autoP[13] + "=" + DataHelper.dateTransformExpirationDate(extraData.getExpirationDateMonth()) + "&" +
                autoP[14] + "=" + getInsuredSinceYears(extraData.getInsuredSinceYears());
        return s;
    }

    private static String getInsuredSinceYears(String years) {
        if (years.equals("5+")) {
            return "10";
        } else if (years.equals("0")) {
            return "1";
        } else {
            return years;
        }
    }

    private static String getSimilarData(ExtraDataAutoP extraData) {
        String s = autoP[2] + "=" + extraData.getAnnualMiles() + "&" +
                autoP[3] + "=" + extraData.getYear() + "&" +
                autoP[4] + "=" + extraData.getMake() + "&" +
                autoP[5] + "=" + extraData.getModel().replace(".", "-") + "&" +
                autoP[6] + "=" + extraData.getPrimaryUse() + "&" +
                autoP[12] + "=" + extraData.getCreditRating() + "&" +
                autoP[8] + "=" + extraData.getMaritalStatus() + "&" +
                autoP[11] + "=" + extraData.getEducation();
        return s;
    }

/*    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        ExtraDataAutoP extraDataAutoP = new ExtraDataAutoP();
        ExtraDataHealthMF extraDataHealthMF = new ExtraDataHealthMF();
        ExtraDataHomeMF homeMF = new ExtraDataHomeMF();

        String lastname = getFieldValue(homeMF, "insurancecompany");

        String url = "";
        for (String s : autoP) {
            url = url + getFieldValue(extraDataAutoP, s) + "&";
        }
    }


    private static String generateValue(Object object, String fieldName) {

        return null;
    }


    private static String getFieldValue(Object object, String fieldName) {
        try {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field f : fields) {
                if (f.getName().equalsIgnoreCase(fieldName)) {
                    f.setAccessible(true);
                    Object value = f.get(object);
                    return fieldName + "=" + String.valueOf(value);
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    private static String getFieldValue2(Object object, String fieldName) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            Object value = field.get(object);
            return fieldName + "=" + String.valueOf(value);
        } catch (Exception e) {
            return null;
        }
    }*/

/*
  Object to Map

  Map<String, String> introspected = new BeanMap(extraData);
    Map<String, Object> describe = BeanUtils.describe(extraData);
    Map<String, Object> introspect = objectToMap(extraData);


    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        BeanInfo info = Introspector.getBeanInfo(obj.getClass());
        for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
            Method reader = pd.getReadMethod();
            if (reader != null)
                result.put(pd.getName(), reader.invoke(obj));
        }
        return result;
    }

    */
}
