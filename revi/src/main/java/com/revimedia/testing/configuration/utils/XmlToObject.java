package com.revimedia.testing.configuration.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;

/**
 * Created by dstoianov on 5/19/2014, 1:45 PM.
 */
public class XmlToObject {

    public static <T> T unMarshal(Class<T> clazz, URL xml) throws JAXBException, IOException {
        JAXBContext jc = JAXBContext.newInstance(clazz);
        Unmarshaller u = jc.createUnmarshaller();
        // U can use IOUtils.toInputStream(xml) if add to deps commons-io and parse String
        // Also, it can be Node, String, etc
        return u.unmarshal(new StreamSource(xml.openStream()), clazz).getValue();
    }

    public static <T> T unMarshal(Class<T> clazz, String xml) throws JAXBException, IOException {
        JAXBContext jc = JAXBContext.newInstance(clazz);
        Unmarshaller u = jc.createUnmarshaller();
        StringBuffer xmlStr = new StringBuffer(xml);
        StreamSource streamSource = new StreamSource(new StringReader(xmlStr.toString()));
        return u.unmarshal(streamSource, clazz).getValue();
    }
}
