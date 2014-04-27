package xmltoobject;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by Funker on 27.04.14.
 */
public class XMLTest {

    @DataProvider
    public static Object[][] userData() {
        return unMarshal("./src/test/resources/data/data_usa_1000.xml", Rowdata.class);
    }


    @Test(dataProvider = "userData")
    public void testName(Row r) throws Exception {
        System.out.println(r.toString());
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


}
