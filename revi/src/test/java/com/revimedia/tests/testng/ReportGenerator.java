package com.revimedia.tests.testng;

import com.revimedia.testing.beans.test.TestngResultsType;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by denys.stoianov on 2016-04-22.
 */
public class ReportGenerator {

    public static String resultsXml = "src/main/resources/xsds/test/testng-results-autohero.xml";

    public <T> T unMarshal(String xml_file_name, Class<T> clazz) throws Exception {

        File file = new File(xml_file_name);
//            InputStream targetStream = new FileInputStream(file);
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        T data = (T) jaxbUnmarshaller.unmarshal(file);

        return (T) data;

    }

    @Test
    public void testName() throws Exception {
//        SuiteType suiteType = unMarshal(resultsXml, SuiteType.class);
        TestngResultsType testngResultsType = unMarshal(resultsXml, TestngResultsType.class);

    }
}
