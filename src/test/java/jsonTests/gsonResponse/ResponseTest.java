package jsonTests.gsonResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.revimedia.testing.configuration.response.Errors;
import com.revimedia.testing.configuration.response.ErrorsDeserializer;
import com.revimedia.testing.configuration.response.Response;
import com.revimedia.testing.configuration.response.ResponseDeserializer;
import org.bouncycastle.jcajce.provider.asymmetric.dsa.DSASigner;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
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
import java.lang.reflect.Type;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by Funker on 03.05.14.
 */
public class ResponseTest {

    public String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><LeadData Target=\"Lead.CDSInsert\" RequestTime=\"2014-05-09 23:50:11\" TimeZone=\" 0300\" UserAgent=\"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.131 Safari/537.36\" SessionLength=\"4902\"><AffiliateData Id=\"1\" OfferId=\"\" SubId=\"\" Sub2Id=\"\" Source=\"\" SurveyPath=\"AutoFrameSteps\" TransactionId=\"\" LeadId=\"8AE0A844-41B6-FC4E-6C61-C2C2638217DB\" VerifyAddress=\"true\"/><QuoteRequest QuoteType=\"Auto\"><Drivers><Driver><FirstName>Carly</FirstName><LastName>Crawford</LastName><Gender>Female</Gender><BirthDate>1983-05-18</BirthDate><LicenseState>CA</LicenseState><AgeLicensed>16</AgeLicensed><YearsAtEmployer>5</YearsAtEmployer><RequiresSR22Filing>No</RequiresSR22Filing><LicenseEverSuspendedRevoked>No</LicenseEverSuspendedRevoked><CreditRating>Excellent</CreditRating><Occupation>Employeed</Occupation><LicenseStatus>Current</LicenseStatus><Education>Some College</Education><MaritalStatus>Single</MaritalStatus><RelationshipToApplicant>self</RelationshipToApplicant></Driver></Drivers><Vehicles><Vehicle><AnnualMiles>12500</AnnualMiles><CollisionDeductible>500</CollisionDeductible><ComphrensiveDeductible>500</ComphrensiveDeductible><Garage>No Cover</Garage><OneWayDistance>10-19</OneWayDistance><PrimaryUse>Commute To/From Work</PrimaryUse><Ownership>Yes</Ownership><WeeklyCommuteDays>5</WeeklyCommuteDays><Year>2002</Year><Make>BUICK</Make><Model>Regal LS</Model></Vehicle></Vehicles><Insurance><CurrentPolicy><ExpirationDate>2014-06-09</ExpirationDate><InsuredSince>2009-05-09</InsuredSince><InsuranceCompany>Liberty Mutual</InsuranceCompany></CurrentPolicy><RequestedPolicy><PropertyDamage/><CoverageType>Standard Protection</CoverageType><BodilyInjury/></RequestedPolicy></Insurance></QuoteRequest><ContactData><FirstName>Carly</FirstName><LastName>Crawford</LastName><PhoneNumber>5605693704</PhoneNumber><Address>121 Gray Avenue</Address><EmailAddress>massa@et.ca</EmailAddress><ZipCode>93101</ZipCode><City>SANTA BARBARA</City><IPAddress>192.168.0.1</IPAddress><State>CA</State><YearsAtResidence>1</YearsAtResidence><MonthsAtResidence>0</MonthsAtResidence><ResidenceType>With my parents</ResidenceType></ContactData></LeadData>";

    String jsonTxt = "{\"Items\":[{\"Key\":\"1 Series 128i\",\"Value\":\"1 Series 128i\"},{\"Key\":\"1 Series 135i\",\"Value\":\"1 Series 135i\"},{\"Key\":\"3 Series 320i\",\"Value\":\"3 Series 320i\"},{\"Key\":\"3 Series 320i xDrive\",\"Value\":\"3 Series 320i xDrive\"},{\"Key\":\"3 Series 328i\",\"Value\":\"3 Series 328i\"},{\"Key\":\"3 Series 328i xDrive\",\"Value\":\"3 Series 328i xDrive\"},{\"Key\":\"3 Series 335i\",\"Value\":\"3 Series 335i\"},{\"Key\":\"3 Series 335i xDrive\",\"Value\":\"3 Series 335i xDrive\"},{\"Key\":\"3 Series 335is\",\"Value\":\"3 Series 335is\"},{\"Key\":\"3 Series ActiveHybrid 3\",\"Value\":\"3 Series ActiveHybrid 3\"},{\"Key\":\"5 Series 528i\",\"Value\":\"5 Series 528i\"},{\"Key\":\"5 Series 528i xDrive\",\"Value\":\"5 Series 528i xDrive\"},{\"Key\":\"5 Series 535i\",\"Value\":\"5 Series 535i\"},{\"Key\":\"5 Series 535i Gran Turismo\",\"Value\":\"5 Series 535i Gran Turismo\"},{\"Key\":\"5 Series 535i xDrive\",\"Value\":\"5 Series 535i xDrive\"},{\"Key\":\"5 Series 535i xDrive Gran Turismo\",\"Value\":\"5 Series 535i xDrive Gran Turismo\"},{\"Key\":\"5 Series 550i\",\"Value\":\"5 Series 550i\"},{\"Key\":\"5 Series 550i Gran Turismo\",\"Value\":\"5 Series 550i Gran Turismo\"},{\"Key\":\"5 Series 550i xDrive\",\"Value\":\"5 Series 550i xDrive\"},{\"Key\":\"5 Series 550i xDrive Gran Turismo\",\"Value\":\"5 Series 550i xDrive Gran Turismo\"},{\"Key\":\"5 Series ActiveHybrid 5\",\"Value\":\"5 Series ActiveHybrid 5\"},{\"Key\":\"6 Series 640i\",\"Value\":\"6 Series 640i\"},{\"Key\":\"6 Series 640i Gran Coupe\",\"Value\":\"6 Series 640i Gran Coupe\"},{\"Key\":\"6 Series 650i\",\"Value\":\"6 Series 650i\"},{\"Key\":\"6 Series 650i Gran Coupe\",\"Value\":\"6 Series 650i Gran Coupe\"},{\"Key\":\"6 Series 650i xDrive\",\"Value\":\"6 Series 650i xDrive\"},{\"Key\":\"6 Series 650i xDrive Gran Coupe\",\"Value\":\"6 Series 650i xDrive Gran Coupe\"},{\"Key\":\"7 Series 740i\",\"Value\":\"7 Series 740i\"},{\"Key\":\"7 Series 740Li\",\"Value\":\"7 Series 740Li\"},{\"Key\":\"7 Series 740Li xDrive\",\"Value\":\"7 Series 740Li xDrive\"},{\"Key\":\"7 Series 750i\",\"Value\":\"7 Series 750i\"},{\"Key\":\"7 Series 750i xDrive\",\"Value\":\"7 Series 750i xDrive\"},{\"Key\":\"7 Series 750Li\",\"Value\":\"7 Series 750Li\"},{\"Key\":\"7 Series 750Li xDrive\",\"Value\":\"7 Series 750Li xDrive\"},{\"Key\":\"7 Series 760Li\",\"Value\":\"7 Series 760Li\"},{\"Key\":\"7 Series ActiveHybrid 7\",\"Value\":\"7 Series ActiveHybrid 7\"},{\"Key\":\"7 Series ALPINA B7 LWB\",\"Value\":\"7 Series ALPINA B7 LWB\"},{\"Key\":\"7 Series ALPINA B7 LWB xDrive\",\"Value\":\"7 Series ALPINA B7 LWB xDrive\"},{\"Key\":\"7 Series ALPINA B7 SWB\",\"Value\":\"7 Series ALPINA B7 SWB\"},{\"Key\":\"7 Series ALPINA B7 SWB xDrive\",\"Value\":\"7 Series ALPINA B7 SWB xDrive\"},{\"Key\":\"M3\",\"Value\":\"M3\"},{\"Key\":\"M5\",\"Value\":\"M5\"},{\"Key\":\"M6\",\"Value\":\"M6\"},{\"Key\":\"X1 sDrive28i\",\"Value\":\"X1 sDrive28i\"},{\"Key\":\"X1 xDrive28i\",\"Value\":\"X1 xDrive28i\"},{\"Key\":\"X1 xDrive35i\",\"Value\":\"X1 xDrive35i\"},{\"Key\":\"X3 xDrive28i\",\"Value\":\"X3 xDrive28i\"},{\"Key\":\"X3 xDrive35i\",\"Value\":\"X3 xDrive35i\"},{\"Key\":\"X5 M\",\"Value\":\"X5 M\"},{\"Key\":\"X5 xDrive35d\",\"Value\":\"X5 xDrive35d\"},{\"Key\":\"X5 xDrive35i\",\"Value\":\"X5 xDrive35i\"},{\"Key\":\"X5 xDrive35i Premium\",\"Value\":\"X5 xDrive35i Premium\"},{\"Key\":\"X5 xDrive35i Sport Activity\",\"Value\":\"X5 xDrive35i Sport Activity\"},{\"Key\":\"X5 xDrive50i\",\"Value\":\"X5 xDrive50i\"},{\"Key\":\"X6 M\",\"Value\":\"X6 M\"},{\"Key\":\"X6 xDrive35i\",\"Value\":\"X6 xDrive35i\"},{\"Key\":\"X6 xDrive50i\",\"Value\":\"X6 xDrive50i\"},{\"Key\":\"Z4 sDrive28i\",\"Value\":\"Z4 sDrive28i\"},{\"Key\":\"Z4 sDrive35i\",\"Value\":\"Z4 sDrive35i\"},{\"Key\":\"Z4 sDrive35is\",\"Value\":\"Z4 sDrive35is\"}]}";
    String jsonTxt2 = "{\"Items\":[{\"Key\":\"ALFA ROMEO\",\"Value\":\"ALFA ROMEO\"},{\"Key\":\"AMC\",\"Value\":\"AMC\"},{\"Key\":\"AUDI\",\"Value\":\"AUDI\"},{\"Key\":\"BMW\",\"Value\":\"BMW\"},{\"Key\":\"BUICK\",\"Value\":\"BUICK\"},{\"Key\":\"CADILLAC\",\"Value\":\"CADILLAC\"},{\"Key\":\"CHEVROLET\",\"Value\":\"CHEVROLET\"},{\"Key\":\"CHRYSLER\",\"Value\":\"CHRYSLER\"},{\"Key\":\"DATSUN\",\"Value\":\"DATSUN\"},{\"Key\":\"DODGE\",\"Value\":\"DODGE\"},{\"Key\":\"FORD\",\"Value\":\"FORD\"},{\"Key\":\"GMC\",\"Value\":\"GMC\"},{\"Key\":\"HONDA\",\"Value\":\"HONDA\"},{\"Key\":\"JAGUAR\",\"Value\":\"JAGUAR\"},{\"Key\":\"JEEP\",\"Value\":\"JEEP\"},{\"Key\":\"LINCOLN\",\"Value\":\"LINCOLN\"},{\"Key\":\"MAZDA\",\"Value\":\"MAZDA\"},{\"Key\":\"MERCEDES-BENZ\",\"Value\":\"MERCEDES-BENZ\"},{\"Key\":\"MERCURY\",\"Value\":\"MERCURY\"},{\"Key\":\"OLDSMOBILE\",\"Value\":\"OLDSMOBILE\"},{\"Key\":\"PLYMOUTH\",\"Value\":\"PLYMOUTH\"},{\"Key\":\"PONTIAC\",\"Value\":\"PONTIAC\"},{\"Key\":\"PORSCHE\",\"Value\":\"PORSCHE\"},{\"Key\":\"RENAULT\",\"Value\":\"RENAULT\"},{\"Key\":\"SAAB\",\"Value\":\"SAAB\"},{\"Key\":\"SUBARU\",\"Value\":\"SUBARU\"},{\"Key\":\"TOYOTA\",\"Value\":\"TOYOTA\"},{\"Key\":\"VOLKSWAGEN\",\"Value\":\"VOLKSWAGEN\"},{\"Key\":\"VOLVO\",\"Value\":\"VOLVO\"}]}";
    String bmw1981 = "{\"Items\":[{\"Key\":\"3 Series 320i\",\"Value\":\"3 Series 320i\"},{\"Key\":\"5 Series 528i\",\"Value\":\"5 Series 528i\"},{\"Key\":\"6 Series 633CSi\",\"Value\":\"6 Series 633CSi\"},{\"Key\":\"7 Series 733i\",\"Value\":\"7 Series 733i\"}]}";
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
    public void testJSONPrettyPrint() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Object json = mapper.readValue(jsonTxt, Object.class);
        String indented = mapper.defaultPrettyPrintingWriter().writeValueAsString(json);
        System.out.println(indented);
    }


    @Test
    public void testJSONToList() throws Exception {

        Map<String, Object> result = new ObjectMapper().readValue(jsonTxt, HashMap.class);
        List<Map<String, String>> items = (List<Map<String, String>>) result.get("Items");
        List<String> model2 = new ArrayList<String>();
        for (Map<String, String> item : items) {
            model2.add(item.get("Value"));
        }


        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();
        Map<String, List<Map<String, String>>> jsonMap = gson.fromJson(bmw1981, type);
        List<String> res = new ArrayList<String>();

        for (Map<String, String> item : jsonMap.get("Items")) {
            res.add(item.get("Value"));
        }

    }
}
