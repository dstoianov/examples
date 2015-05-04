package com.revimedia.tests.builder.json;

import com.google.common.base.Stopwatch;
import com.google.gson.Gson;
import com.revimedia.tests.builder.javascript.JSHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Funker on 12.02.2015.
 */
public class JsonTest {
    protected WebDriver driver;
    protected JSHelper jsHelper = null;

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
//        driver = new PhantomJSDriver();
//        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
//        JSHelper jsHelper = new JSHelper(driver);
        jsHelper = JSHelper.getInstance(driver);
        driver.get("http://development.stagingrevi.com/auto/mfs");
        jsHelper.waitForAjaxComplete();

    }

    @Test
    public void testJson() throws Exception {
//        Stopwatch timer = Stopwatch.createStarted();
        Stopwatch timer = new Stopwatch();
        timer.start();

        Gson gson = new Gson();
        Object settings = gson.fromJson(readFile("settings.json"), Object.class);
        Object fields = gson.fromJson(readFile("fields.json"), Object.class);
        Object steps = gson.fromJson(readFile("steps.json"), Object.class);

        JsonCampaign campaign = new JsonCampaign(driver, steps, fields);
        campaign.buildAllPages();

        List<JsonPage> pages = campaign.getPages();

        System.out.println("Json read, parsing and build campaign took: " + timer.stop());

        campaign.fillInAllPages(getContactData());


    }

    private static BufferedReader readFile(String s) throws FileNotFoundException {
        FileReader fileReader = new FileReader("src/test/java/com/revimedia/tests/builder/json/" + s);
        BufferedReader br = new BufferedReader(fileReader);
        return br;
    }

    private static Map<String, String> getContactData() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("FirstName", "Dorian");
        map.put("LastName", "Dummy");
        map.put("Gender", "Female");
        map.put("BirthDate", "Apr 26, 1982");
        map.put("PhoneNumber", "5608039491");
        map.put("Address", "7500 Dallas Parkway");
        map.put("EmailAddress", "sit.amet.massa@consequatenimdiam.ca");
        map.put("ZipCode", "75024");
        map.put("City", "PLANO");
        map.put("State", "TX");
        return map;
    }

}
