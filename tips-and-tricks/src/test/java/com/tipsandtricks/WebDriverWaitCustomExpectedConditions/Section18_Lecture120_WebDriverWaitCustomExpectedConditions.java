package com.tipsandtricks.WebDriverWaitCustomExpectedConditions;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
//https://github.com/eviltester/webDriverExperiments/blob/master/src/test/java/com/seleniumsimplified/webdriver/Section18_Lecture120_WebDriverWaitCustomExpectedConditions.java

public class Section18_Lecture120_WebDriverWaitCustomExpectedConditions {

    private static WebDriver driver;
    private WebDriverWait wait;
    private String basicAjaxSite = "http://www.compendiumdev.co.uk/selenium/basic_ajax.html";

    @BeforeClass
    public static void setUp() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("network.proxy.type", 0);
        driver = new FirefoxDriver(profile);
    }

    @Before
    public void navigateToSite() {
        driver.get(basicAjaxSite);

        wait = new WebDriverWait(driver, 10, 50);
    }


    @Test
    public void doEverythingALot() {

        for (int x = 0; x < 100; x++) {
            System.out.println("loop : " + x);
            System.out.println("customExpectedConditionUsingAClass");
            navigateToSite();
            customExpectedConditionUsingAClass();
            System.out.println("customExpectedConditionUsingAnonymousClassDirectly");
            navigateToSite();
            customExpectedConditionUsingAnonymousClassDirectly();
            System.out.println("customExpectedConditionUsingMethodThatWrapsTheExpectedCondition");
            navigateToSite();
            customExpectedConditionUsingMethodThatWrapsTheExpectedCondition();
        }

    }


    @Test
    public void customExpectedConditionUsingAClass() {
        //selects Server in the first combo box
        selectServerInCombo1();

        //custom wait using a named class
        wait.until(new SelectContainsText(By.id("combo2"), "Java"));
        //new WebDriverWait(driver,10,50).until(new SelectContainsText(By.id("combo2"), "Java"));

        //select Java in the second combo box and then clicks submit and checks the correct id on the resulting page
        selectJavaInCombo2SubmitAndCheckPageShowsJavaSelected();
    }

    @Test
    public void customExpectedConditionUsingAnonymousClassDirectly() {
        //selects Server in the first combo box
        selectServerInCombo1();

        //custom wait which returns an object that implements the ExpectedCondition interface
        //again have to override apply method
        //can't use a constructor because its an anonymous class
        wait.until(new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver driver) {
                return driver.findElement(By.cssSelector("option[value='23']")).isDisplayed();
            }
        });

        //select Java in the second combo box and then clicks submit and checks the correct id on the resulting page
        selectJavaInCombo2SubmitAndCheckPageShowsJavaSelected();
    }


    @Test
    public void customExpectedConditionUsingMethodThatWrapsTheExpectedCondition() {
        selectServerInCombo1();

        //custom wait using a named class
        WebElement javaOption = wait.until(optionWithJavaValueDisplayed("23"));

        javaOption.click();

        selectJavaInCombo2SubmitAndCheckPageShowsJavaSelected();
    }

    //this method must return an object which implements ExpectedCondition interface
    //with the method a new ExpectedCondition object is instantiated and returned
    private ExpectedCondition<WebElement> optionWithJavaValueDisplayed(final String value) {

        return new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.cssSelector("option[value='" + value + "']"));
            }

        };
    }


    private void selectJavaInCombo2SubmitAndCheckPageShowsJavaSelected() {
        driver.findElement(By.cssSelector("#combo2 > option[value='23']")).click();

        //submit the form
        driver.findElement(By.name("submitbutton")).submit();

        wait.until(titleIs("Processed Form Details"));

        //ensure that value language is id 23
        Assert.assertEquals(driver.findElement(By.id("_valuelanguage_id")).getText(), "23");
    }

    private void selectServerInCombo1() {
        // intermittency on wait
        By option3 = By.cssSelector("#combo1 > option[value='3']");
        //wait.until(ExpectedConditions.elementToBeClickable(option3));
        driver.findElement(option3).click();
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

}

