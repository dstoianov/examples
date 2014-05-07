package xml;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Funker on 08.05.14.
 */
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.spi.DirStateFactory.Result;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class xml {
    public static void main(String[] args) {
        HashMap<String, String> hMap = new HashMap<String, String>();
        File file = new File("./src/test/java/xml/request.xml");

        if (file.exists()) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(file);
                Element documentElement = document.getDocumentElement();
                NodeList sList = documentElement.getElementsByTagName("Driver");
                if (sList != null && sList.getLength() > 0) {
                    for (int i = 0; i < sList.getLength(); i++) {
                        Node node = sList.item(i);
                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            Element e = (Element) node;

                            NodeList nodeList = e.getElementsByTagName("Room");

                            String roomName = nodeList.item(0).getChildNodes().item(0).getNodeValue();


                            nodeList = e.getElementsByTagName("CoordLt");
                            String coordValues = nodeList.item(0).getChildNodes().item(0).getNodeValue();


                            nodeList = e.getElementsByTagName("CoordLn");
                            coordValues = coordValues + "," + nodeList.item(0).getChildNodes().item(0).getNodeValue();
                            hMap.put(roomName, coordValues);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("exception occured");
            }
        } else {
            System.out.println("File not exists");
        }

    }
}

