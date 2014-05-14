package xml;

import org.testng.annotations.Test;
import xml.dto.generated.LeadDataType;
import xml.dto.generated.ObjectFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;

/**
 * Created by Funker on 25.04.14.
 */
public class XmlToObject {

    URL searchResultUrl = XmlToObject.class.getResource("/lead.xml");

    private static String FILE_NAME = "./src/test/java/xml/lead.xml";
    private static String XML = "<?xml version=\"1.0\" encoding=\"utf-8\"?><LeadData Target=\"Lead.CDSInsert\" RequestTime=\"2014-05-14 17:27:53\" TimeZone=\" 0300\" UserAgent=\"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0\" SessionLength=\"50033\"><AffiliateData Id=\"1\" OfferId=\"\" SubId=\"\" Sub2Id=\"\" Source=\"\" SurveyPath=\"AutoFrameSteps\" TransactionId=\"\" LeadId=\"EE0117DB-96FB-447D-3BE3-6360CDFD7266\" VerifyAddress=\"true\"/><QuoteRequest QuoteType=\"Auto\"><Drivers><Driver><FirstName>Qqqqqq</FirstName><LastName>Wwwww</LastName><Gender>Male</Gender><BirthDate>1996-01-01</BirthDate><LicenseState>VA</LicenseState><AgeLicensed>16</AgeLicensed><YearsAtEmployer>5</YearsAtEmployer><RequiresSR22Filing>No</RequiresSR22Filing><LicenseEverSuspendedRevoked>No</LicenseEverSuspendedRevoked><CreditRating>Good</CreditRating><Occupation>Employeed</Occupation><LicenseStatus>Current</LicenseStatus><Education>Doctorate Degree</Education><MaritalStatus>Separated</MaritalStatus><RelationshipToApplicant>self</RelationshipToApplicant></Driver></Drivers><Vehicles><Vehicle><AnnualMiles>12500</AnnualMiles><CollisionDeductible>500</CollisionDeductible><ComphrensiveDeductible>500</ComphrensiveDeductible><Garage>No Cover</Garage><OneWayDistance>10-19</OneWayDistance><PrimaryUse>Commute To/From Work</PrimaryUse><Ownership>Yes</Ownership><WeeklyCommuteDays>5</WeeklyCommuteDays><Year>2011</Year><Make>CHEVROLET</Make><Model>Avalanche LTZ</Model><Leased>No</Leased><DailyMileage>4-5</DailyMileage></Vehicle></Vehicles><Insurance><CurrentPolicy><ExpirationDate>2014-06-01</ExpirationDate><InsuredSince>2012-05-14</InsuredSince><InsuranceCompany>21st century</InsuranceCompany></CurrentPolicy><RequestedPolicy><PropertyDamage/><CoverageType>Standard Protection</CoverageType><BodilyInjury/></RequestedPolicy></Insurance></QuoteRequest><ContactData><FirstName>Qqqqqq</FirstName><LastName>Wwwww</LastName><PhoneNumber>5656566566</PhoneNumber><Address>srsgfdsf 22</Address><EmailAddress>kjdfhkjdgfh@dfjhsd.ru</EmailAddress><ZipCode>23232</ZipCode><City>RICHMOND</City><IPAddress>192.168.0.1</IPAddress><State>VA</State><YearsAtResidence>1</YearsAtResidence><MonthsAtResidence>0</MonthsAtResidence><ResidenceType>My own house</ResidenceType></ContactData></LeadData>";

    @Test
    public void testSss() throws Exception {

        // jaxbXMLToObject();
        //LeadDataType from = from(searchResultUrl);
        LeadDataType from = from(XML);

    }


    private static ObjectFactory jaxbXMLToObject() {
        try {
            JAXBContext context = JAXBContext.newInstance(LeadDataType.class);
            Unmarshaller un = context.createUnmarshaller();
            //File file = new File(FILE_NAME);
            StringBuffer xmlStr = new StringBuffer(XML);
            StreamSource streamSource = new StreamSource(new StringReader(xmlStr.toString()));
            ObjectFactory emp = (ObjectFactory) un.unmarshal(streamSource);

            return emp;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static LeadDataType from(URL url) throws JAXBException, IOException {
        return unmarshal(LeadDataType.class, url);
    }

    public static LeadDataType from(String xml) throws JAXBException, IOException {
        return unmarshal(LeadDataType.class, xml);
    }


    public static <T> T unmarshal(Class<T> clazz, URL xml) throws JAXBException, IOException {
        JAXBContext jc = JAXBContext.newInstance(clazz);
        Unmarshaller u = jc.createUnmarshaller();
        // U can use IOUtils.toInputStream(xml) if add to deps commons-io and parse String
        // Also, it can be Node, String, etc
        return u.unmarshal(new StreamSource(xml.openStream()), clazz).getValue();
    }


    public static <T> T unmarshal(Class<T> clazz, String xml) throws JAXBException, IOException {
        JAXBContext jc = JAXBContext.newInstance(clazz);
        Unmarshaller u = jc.createUnmarshaller();
        StringBuffer xmlStr = new StringBuffer(xml);
        StreamSource streamSource = new StreamSource(new StringReader(xmlStr.toString()));
        // U can use IOUtils.toInputStream(xml) if add to deps commons-io and parse String
        // Also, it can be Node, String, etc
        return u.unmarshal(streamSource, clazz).getValue();
    }

}
