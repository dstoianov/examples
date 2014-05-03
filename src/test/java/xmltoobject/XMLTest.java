package xmltoobject;

//import com.revimedia.testing.configuration.dataproviders.AutoDataProvider;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Funker on 27.04.14.
 */
public class XMLTest {

    @DataProvider
    public static Object[][] userUSAData() {
        return unMarshal("./src/test/resources/data/data_usa_1000.xml", Rowdata.class);
    }

    @DataProvider
    public static Object[][] userData() {
        return unMarshal("./src/test/resources/data/users_data_new.xml", Users.class);
    }

    @DataProvider
    public static Object[][] contactData() {
        return unMarshal("./src/test/resources/data/leads_data_1000.xml", Contacts.class);
    }

    @Test(dataProvider = "userUSAData")
    public void testName1(Row r) throws Exception {
        System.out.println(r.toString());
    }


    @Test(dataProvider = "userData")
    public void testName2(User u) throws Exception {
        System.out.println(u.toString());
    }


    @Test
    public void testName3() throws Exception {

        Users usersList = (Users) unMarshalToObject("./src/test/resources/data/users_data_new.xml", Users.class);
        Rowdata rowdata = (Rowdata) unMarshalToObject("./src/test/resources/data/data_usa_1000.xml", Rowdata.class);

        Contacts contacts = new Contacts();
        contacts.setContacts(new ArrayList<Contact>());


        for (int i = 0; i < 1000; i++) {
            Contact contact = new Contact();
            contact.setFirstName(usersList.getUsers().get(i).getFirstName());
            contact.setLastName(usersList.getUsers().get(i).getLastName());
            contact.setGender(usersList.getUsers().get(i).getGender());
            contact.setBirthDate(usersList.getUsers().get(i).getDate());
            contact.setPhoneNumber(usersList.getUsers().get(i).getPhone());
            contact.setAddress(rowdata.getRows().get(i).getAddress());
            contact.setEmailAddress(usersList.getUsers().get(i).getEmail());
            if (rowdata.getRows().get(i).getZipCode().length() > 5) {
                continue;
            }
            contact.setZipCode(rowdata.getRows().get(i).getZipCode());
            contact.setCity(rowdata.getRows().get(i).getCity());
            contact.setState(rowdata.getRows().get(i).getStateCode());

            contacts.getContacts().add(contact);
        }


        JAXBContext jaxbContext = JAXBContext.newInstance(Contacts.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        //Marshal the employees list in console
        //jaxbMarshaller.marshal(contacts, System.out);

        //Marshal the employees list in file
        jaxbMarshaller.marshal(contacts, new File("./src/test/resources/data/leads_data_1000_V2.xml"));


    }

    @Test(dataProvider = "contactData")// ,  dataProvider = AutoDataProvider.class)
    public void testName4(Contact contact1, Contact contact2) throws Exception {
        System.out.println(contact1.toString());
        System.out.println(contact2.toString());
    }


    private static <T extends RandomObject> Object[][] unMarshal(String xml_file_name, Class<T> clazz) {
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


    private static <T extends RandomObject> Object unMarshalToObject(String xml_file_name, Class<T> clazz) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            T data = (T) jaxbUnmarshaller.unmarshal(new File(xml_file_name));


            return data;
            //return data.getListData();

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    @DataProvider
    public static Object[][] contactData2() {
        Users usersList = (Users) unMarshalToObject("./src/test/resources/data/users_data_new.xml", Users.class);
        Rowdata rowdata = (Rowdata) unMarshalToObject("./src/test/resources/data/data_usa_1000.xml", Rowdata.class);

        return new Object[][]{
                {usersList.getRandom()},
                {usersList.getRandom()}
        };
    }

    @Test(dataProvider = "contactData2")
    public void testName5(User u) throws Exception {
        System.out.println(u.toString());

    }

}
