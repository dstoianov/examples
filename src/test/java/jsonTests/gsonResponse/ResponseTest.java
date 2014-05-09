package jsonTests.gsonResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revimedia.testing.configuration.response.Errors;
import com.revimedia.testing.configuration.response.ErrorsDeserializer;
import com.revimedia.testing.configuration.response.Response;
import com.revimedia.testing.configuration.response.ResponseDeserializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import java.io.File;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by Funker on 03.05.14.
 */
public class ResponseTest {

    public String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><LeadData Target=\"Lead.CDSInsert\" RequestTime=\"2014-05-09 23:50:11\" TimeZone=\" 0300\" UserAgent=\"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.131 Safari/537.36\" SessionLength=\"4902\"><AffiliateData Id=\"1\" OfferId=\"\" SubId=\"\" Sub2Id=\"\" Source=\"\" SurveyPath=\"AutoFrameSteps\" TransactionId=\"\" LeadId=\"8AE0A844-41B6-FC4E-6C61-C2C2638217DB\" VerifyAddress=\"true\"/><QuoteRequest QuoteType=\"Auto\"><Drivers><Driver><FirstName>Carly</FirstName><LastName>Crawford</LastName><Gender>Female</Gender><BirthDate>1983-05-18</BirthDate><LicenseState>CA</LicenseState><AgeLicensed>16</AgeLicensed><YearsAtEmployer>5</YearsAtEmployer><RequiresSR22Filing>No</RequiresSR22Filing><LicenseEverSuspendedRevoked>No</LicenseEverSuspendedRevoked><CreditRating>Excellent</CreditRating><Occupation>Employeed</Occupation><LicenseStatus>Current</LicenseStatus><Education>Some College</Education><MaritalStatus>Single</MaritalStatus><RelationshipToApplicant>self</RelationshipToApplicant></Driver></Drivers><Vehicles><Vehicle><AnnualMiles>12500</AnnualMiles><CollisionDeductible>500</CollisionDeductible><ComphrensiveDeductible>500</ComphrensiveDeductible><Garage>No Cover</Garage><OneWayDistance>10-19</OneWayDistance><PrimaryUse>Commute To/From Work</PrimaryUse><Ownership>Yes</Ownership><WeeklyCommuteDays>5</WeeklyCommuteDays><Year>2002</Year><Make>BUICK</Make><Model>Regal LS</Model></Vehicle></Vehicles><Insurance><CurrentPolicy><ExpirationDate>2014-06-09</ExpirationDate><InsuredSince>2009-05-09</InsuredSince><InsuranceCompany>Liberty Mutual</InsuranceCompany></CurrentPolicy><RequestedPolicy><PropertyDamage/><CoverageType>Standard Protection</CoverageType><BodilyInjury/></RequestedPolicy></Insurance></QuoteRequest><ContactData><FirstName>Carly</FirstName><LastName>Crawford</LastName><PhoneNumber>5605693704</PhoneNumber><Address>121 Gray Avenue</Address><EmailAddress>massa@et.ca</EmailAddress><ZipCode>93101</ZipCode><City>SANTA BARBARA</City><IPAddress>192.168.0.1</IPAddress><State>CA</State><YearsAtResidence>1</YearsAtResidence><MonthsAtResidence>0</MonthsAtResidence><ResidenceType>With my parents</ResidenceType></ContactData></LeadData>";


    String success = "{\"_success\":\"BaeOK\",\"TransactionId\":\"99EAF189-5D2D-4D1F-A2CD-7A993EA8A0F7\",\"Errors\":[],\"Success\":true,\"IsWarning\":false}";
        String unSuccess = "{\"_success\":\"BaeNOK\",\"TransactionId\":null,\"Errors\":[{\"Reason\":\"Errors on Address verification.\",\"Param\":\"\",\"ExtraInfo\":\"The address you provided may be incomplete or invalid, please verify its validity. If the information is correct, press submit to proceed..\"}],\"Success\":false,\"IsWarning\":true}";
        String perError = "{\"_success\":\"BaeNOK\",\"TransactionId\":null,\"Errors\":[{\"Reason\":\"Errors on EmailAddress verification.\",\"Param\":\"\",\"ExtraInfo\":\"Email domain invalid.\"}],\"Success\":false,\"IsWarning\":false}";

    @Test
    public void testName() throws Exception {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Response.class, new ResponseDeserializer());
        gsonBuilder.registerTypeAdapter(Errors.class, new ErrorsDeserializer());
        final Gson gson = gsonBuilder.create();

        String w = success;
        final Response response = gson.fromJson(w, Response.class);
        System.out.println(w);
        System.out.println(response);

        System.out.println("----------Back to JSON------------------");

        final String json = gson.toJson(response);
        System.out.println(json);

        assertThat(response, allOf(
                hasProperty("_success", equalTo("BaeOK")),
                //hasProperty("transactionId", equalTo("99EAF189-5D2D-4D1F-A2CD-7A993EA8A0F7")),
                hasProperty("success", is(true)),
                hasProperty("isWarning", is(false))
        ));

        assertThat(response.getTransactionId().length(), is(36));
        Map<String, String> result = new ObjectMapper().readValue(success, HashMap.class);

//        assertThat(result, allOf(
//                        hasEntry("isWarning", "false"),
//                        hasEntry("_success", "BaeOK")
//
//        ));

        assertThat(result, hasEntry(equalTo("TransactionId"), equalTo("99EAF189-5D2D-4D1F-A2CD-7A993EA8A0F7")));

        assertThat(result, hasEntry("TransactionId", "99EAF189-5D2D-4D1F-A2CD-7A993EA8A0F7"));

        assertThat(result, hasKey("IsWarning"));
        assertThat(result, hasEntry("Errors", "[]"));

        assertThat(result, hasKey("_success"));

        assertThat(result, hasValue("BaeOK"));
        //assertThat(result, hasItems((new Object("BaeOK", "ddd")));


    }

    @Test
    public void testAName() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Object json = mapper.readValue(success, Object.class);
        String indented = mapper.defaultPrettyPrintingWriter().writeValueAsString(json);
        System.out.println(indented);
    }
}
