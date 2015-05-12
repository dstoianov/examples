package com.revimedia.tests.configuration.dataproviders;

import com.revimedia.testing.cds.auto.staticdata.ExtraDataAutoP;
import com.revimedia.testing.cds.home.staticdata.ExtraDataHomeMF;
import com.revimedia.testing.configuration.dto.Contact;
import com.revimedia.testing.configuration.dto.Contacts;
import com.revimedia.testing.configuration.dto.RandomObject;
import com.revimedia.testing.configuration.helpers.DataHelper;
import org.apache.commons.lang3.RandomStringUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Created by stde on 4/28/2014.
 */
@SuppressWarnings("unchecked")
public class DataProviderHelper {

    //public static String xmlContactData = "./src/test/resources/data/leads_data_1000.xml";
    public static String xmlContactData = "./revi/src/test/resources/data/leads_data_1000_dummy.xml";
    public static Contact contact = new Contact("Robinson", "Dummy", "Female", "Feb 29, 1988", "7180483889", "123Fake", "blah.blahblah@gmail.con", "75201", "DALLAS", "TX");
    public static Contact contactMob = new Contact("Robinson", "Dummy", "Female", "Feb 29, 1988", "7180483889", "6221 Monterey Rd 101", "blah.blahblah@gmail.con", "75201", "DALLAS", "TX");
    public static Contact contact1 = new Contact("Kung Fu", "Dummy", "Male", "Oct 31, 1995", "7180483889", "6221 Monterey Rd 101", "miley.cyrus@hotmail.com", "90005", "LOS ANGELES", "CA");
    public static Contact contact2 = new Contact("Robinson", "Dummy", "Female", "Feb 29, 1980", "3238550093", "123Fake", "blah.blahblah@gmail.con", "75201", "DALLAS", "TX");
    public static List<String> expectedErrorsSorted = Arrays.asList("Please enter valid email address.", "Please enter valid phone number.", "Please enter valid street address.");
    public static List<String> expectedErrorsMob = Arrays.asList("Please enter valid phone number.", "Please enter valid email address.");
    public static List<String> expectedErrorsHealthMob = Arrays.asList("Please enter valid street address.");

    static <T extends RandomObject> Object[][] unMarshal(String xml_file_name, Class<T> clazz) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            T data = (T) jaxbUnmarshaller.unmarshal(new File(xml_file_name));

            return new Object[][]{{data.getRandom()}};

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    static <T extends RandomObject> Object unMarshalToObject(String xml_file_name, Class<T> clazz) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            T data = (T) jaxbUnmarshaller.unmarshal(new File(xml_file_name));

            return data.getRandom();

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    static <T extends RandomObject> Contact unMarshalToContact(String xml_file_name, Class<T> clazz) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            T data = (T) jaxbUnmarshaller.unmarshal(new File(xml_file_name));

            return (Contact) data.getRandom();

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Contact getContactForEBureauVerification() {
        Contact contact = unMarshalToContact(xmlContactData, Contacts.class);
        contact.setAddress(DataHelper.generateInvalidAddress());
        contact.setEmailAddress(RandomStringUtils.random(10, true, true) + "_" + contact.getEmailAddress());
        return contact;
    }

    protected static ExtraDataAutoP getExtraDataAutoPMob() {

        ExtraDataAutoP data = new ExtraDataAutoP();
        if (data.getInsuranceCompany().equalsIgnoreCase("Currently not insured")) {
            data.setInsuranceCompany("AAA");
        }

        if (data.getEducation().equalsIgnoreCase("None") ||
                data.getEducation().equalsIgnoreCase("Some College") ||
                data.getEducation().equalsIgnoreCase("Doctorate Degree")) {
            data.setEducation("Other");
        }

        if (data.getResidenceType().equalsIgnoreCase("Other")) {
            data.setResidenceType("My own house");
        }
        return data;
    }

    protected static ExtraDataHomeMF getExtraDataHomeMobu() {
        ExtraDataHomeMF homeMF = new ExtraDataHomeMF();
        if (homeMF.getInsuranceCompany().equalsIgnoreCase("Currently not insured")) {
            homeMF.setInsuranceCompany("AAA");
        }
        return homeMF;
    }

    protected static ExtraDataAutoP getExtraDataAutoA() {
        ExtraDataAutoP data = new ExtraDataAutoP();
        if (data.getInsuranceCompany().equalsIgnoreCase("Currently not insured") || data.getInsuranceCompany().equalsIgnoreCase("Company not listed")) {
            data.setInsuranceCompany("AAA");
        }
        return data;
    }


}
