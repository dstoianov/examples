package com.revimedia.testing.configuration.dataproviders;

import com.revimedia.testing.configuration.dto.RandomObject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by stde on 4/28/2014.
 */
public class DataProviderHelper {

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
}
