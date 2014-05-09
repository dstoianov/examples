package com.revimedia.testing.configuration.helpers;


import org.codehaus.jackson.map.ObjectMapper;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;


/**
 * Created by Funker on 10.05.14.
 */
public class Formatter {

    public static String prettyXMLFormat(String input) {
        return prettyFormat(input, 4);
    }

    private static String prettyFormat(String input, int indent) {
        try {
            Source xmlInput = new StreamSource(new StringReader(input));
            StringWriter stringWriter = new StringWriter();
            StreamResult xmlOutput = new StreamResult(stringWriter);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", String.valueOf(indent));
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString();
        } catch (Throwable t) {
            return input;
        }
    }

    public static String prettyJSONFormat(String input) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Object json = mapper.readValue(input, Object.class);
            String indented = mapper.defaultPrettyPrintingWriter().writeValueAsString(json);
            return indented;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

}
