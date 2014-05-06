package com.revimedia.testing.cds.auto.mfs.pages;

import com.revimedia.testing.cds.Page;
import com.revimedia.testing.cds.auto.staticdata.StaticDataAutoMFS;
import com.revimedia.testing.configuration.dto.Contact;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * User: stoianod
 * Date: 4/9/14
 */
public class CompareAndSavePage extends Page {
    protected final Logger log = Logger.getLogger(this.getClass());

    public CompareAndSavePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[contains(@class, 'FirstName')]//input")
    private WebElement txtFirstName;

    @FindBy(xpath = "//div[contains(@class, 'LastName')]//input")
    private WebElement txtLastName;

    @FindBy(xpath = "//div[contains(@class, 'Education')]//select")
    private WebElement ddEducation;


    @FindBy(xpath = ".//input[@value='Male']")
    private WebElement rbtnMale;

    @FindBy(xpath = ".//input[@value='Female']")
    private WebElement rbtnFemale;


    @FindBy(xpath = "//div[contains(@class, 'Month')]//select")
    private WebElement ddMonth;

    @FindBy(xpath = "//div[contains(@class, 'Day')]//select")
    private WebElement ddDay;

    @FindBy(xpath = "//div[contains(@class, 'Year')]//select")
    private WebElement ddYear;

    @FindBy(xpath = "//div[contains(@class, 'Address')]//input")
    private WebElement txtStreetAddress;

    @FindBy(xpath = "//div[contains(@class, 'ZipCode')]//input")
    private WebElement txtZipCode;

    @FindBy(xpath = "//div[contains(@class, 'PhoneNumber')]//input")
    private WebElement txtPhoneNumber;

    @FindBy(xpath = "//div[contains(@class, 'EmailAddress')]//input")
    private WebElement txtEmail;


    @FindBy(xpath = "//button")
    private WebElement btnGetMyQuotes; //bq-control bq-type-simple

    @FindBy(xpath = "//button") //button class="bq-control bq-type-simple">
    private WebElement btnContinue;


    public CompareAndSavePage fillInAllFields(Contact contact, StaticDataAutoMFS staticData) {
        clearAndType(txtFirstName, contact.getFirstName());
        clearAndType(txtLastName, contact.getLastName());
        selectByValue(ddEducation, staticData.getEducation());
        //selectRandomByIndex(ddEducation);
        if (contact.getGender().equalsIgnoreCase("Male")) {
            rbtnMale.click();
        } else rbtnFemale.click();
        selectDate(ddMonth, ddDay, ddYear, contact.getBirthDate());
        clearAndType(txtStreetAddress, contact.getAddress());
        clearAndType(txtPhoneNumber, contact.getPhoneNumber());
        clearAndType(txtEmail, contact.getEmailAddress());
        waitForAjaxComplete();
        return this;
    }


    public String getZipStateAndCity() {
        return driver.findElement(By.xpath("//div[contains(@class, 'ZipCode')]")).getText();
    }

    public void submitForm() {
        waitForAjaxComplete();
        btnGetMyQuotes.click();
        if (btnGetMyQuotes.isDisplayed()) {
            // TODO: workaround for eBureau Verification
            // TODO: need to redo this, think about more elegant solution for eBureau Verification
            btnGetMyQuotes.click();
        }
    }
}
