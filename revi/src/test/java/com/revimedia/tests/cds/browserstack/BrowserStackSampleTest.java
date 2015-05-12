package com.revimedia.tests.cds.browserstack;

/**
 * Created by dstoianov on 5/26/2014, 12:37 PM.
 */

import com.revimedia.testing.cds.auto.mfs.pages.CompareAndSavePage;
import com.revimedia.testing.cds.auto.mfs.pages.DriverPage;
import com.revimedia.testing.cds.auto.mfs.pages.VehiclePage;
import com.revimedia.testing.cds.auto.staticdata.ExtraDataAutoMFS;
import com.revimedia.testing.cds.constants.Messages;
import com.revimedia.testing.configuration.dto.Contact;
import com.revimedia.testing.configuration.helpers.DataHelper;
import com.revimedia.tests.configuration.dataproviders.AutoDataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class BrowserStackSampleTest {
    public static final String USERNAME = "denysstoianov";
    public static final String AUTOMATE_KEY = "ydcKwxskuYNq7CDrK5WC";
    public static final String URL = "http://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";
    public WebDriver driver;
    public DriverPage driverPage;
    public VehiclePage vehiclePage;
    public CompareAndSavePage compareAndSavePage;

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @BeforeMethod
    public void openPage() {
        driver.get("http://development.stagingrevi.com/auto/mfs");
    }

    @BeforeClass
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();

/*        caps.setCapability("browserName", "iPhone");
        caps.setCapability("platform", "MAC");
        caps.setCapability("device", "iPhone 5S");*/


        caps.setCapability("browser", "IE");
        caps.setCapability("browser_version", "7.0");
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "XP");
        caps.setCapability("resolution", "1024x768");


/*        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "34.0");
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "7");
        caps.setCapability("resolution", "1024x768");*/


        caps.setCapability("browserstack.debug", "true");

        driver = new RemoteWebDriver(new URL(URL), caps);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test(groups = {"eBureau Verification"}, dataProvider = "contactAndStaticDataAutoMFS", dataProviderClass = AutoDataProvider.class)
    public void testShouldBeShowneBureauVerificationMessage(Contact contact, ExtraDataAutoMFS staticData) throws Exception {

        //ACT
        driverPage = new DriverPage(driver);
        vehiclePage = driverPage.fillInAllFields(contact, staticData).clickOnContinue();
        compareAndSavePage = vehiclePage.fillInAllFields(staticData).clickOnContinue();
        compareAndSavePage.fillInAllFields(contact, staticData);
        compareAndSavePage.fillInInvalidStreetAddressField(DataHelper.generateInvalidAddress());

        compareAndSavePage.clickSubmit();

        //Assert
        assertThat(compareAndSavePage.getPageText(), containsString(Messages.EBUREAU_VERIFICATION));
        assertThat(compareAndSavePage.getAllErrors().get(0).getText(), equalTo(Messages.EBUREAU_VERIFICATION));
    }


    @Test
    public void testStack() throws Exception {
        driver.get("http://www.google.com/ncr");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("BrowserStack");
        element.submit();
        System.out.println(driver.getTitle());

    }
}
