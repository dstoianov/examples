package parametrized.suite.s2;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlPackage;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.List;

/**
 * User: stoianod
 * Date: 4/8/14
 */
public class Init {

    @Test
    public void bigTest() throws Exception {

        for (String url : ImmutableList.of("url1", "url2", "url3")) {
            XmlSuite suite = new XmlSuite();
            suite.setName("TmpSuite");

            suite.setParameters(ImmutableMap.of("url", url));

            XmlTest test = new XmlTest(suite);
            test.setName("TmpTest");

   /*         List<XmlClass> classes = new ArrayList<XmlClass>();
            classes.add(new XmlClass("peremeter.suite.Initial"));
            classes.add(new XmlClass("peremeter.suite.Test1"));
            classes.add(new XmlClass("peremeter.suite.Test2"));
            classes.add(new XmlClass("peremeter.suite.Test3"));
            test.setXmlClasses(classes) ;*/

            ArrayList<XmlPackage> xmlPackages = new ArrayList<XmlPackage>();
            xmlPackages.add(new XmlPackage("parametrized.suite"));
            test.setPackages(xmlPackages);


            List<XmlSuite> suites = new ArrayList<XmlSuite>();
            suites.add(suite);
            TestNG tng = new TestNG();
            tng.setXmlSuites(suites);
            tng.run();
        }


    }
}
