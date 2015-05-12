package com.revimedia.testing.cds.auto.mfs.pages;

import com.revimedia.testing.cds.Page;
import com.revimedia.testing.cds.auto.staticdata.ExtraDataAutoMFS;
import com.revimedia.testing.cds.constants.Messages;
import com.revimedia.testing.configuration.dto.Contact;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * User: Denis Stoianov
 * Date: 4/9/14
 */
public class CompareAndSavePage extends Page {
    protected final Logger log = Logger.getLogger(this.getClass());
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
    @FindBy(xpath = "//div[contains(@class, 'BirthDate')]//select")
    private List<WebElement> ddBirthDate;
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

    public CompareAndSavePage(WebDriver driver) {
        super(driver);
        log.info("Compare & SAVE Page is Loaded, STEP #3");
    }

    public CompareAndSavePage fillInAllFields(Contact contact, ExtraDataAutoMFS staticData) {
        clearAndType(txtFirstName, contact.getFirstName());
        clearAndType(txtLastName, contact.getLastName());
        clearAndType(txtPhoneNumber, contact.getPhoneNumber());
        clearAndType(txtStreetAddress, contact.getAddress());
        selectByValue(ddEducation, staticData.getEducation());
        if (contact.getGender().equalsIgnoreCase("Male")) {
            rbtnMale.click();
        } else rbtnFemale.click();
        selectDate(ddMonth, ddDay, ddYear, contact.getBirthDate());
        clearAndType(txtEmail, contact.getEmailAddress());
        //waitForAjaxComplete();
        return this;
    }

    public String getZipStateAndCity() {
        return driver.findElement(By.xpath("//div[contains(@class, 'ZipCode')]")).getText();
    }

    public void clickSubmit() {
        btnGetMyQuotes.click();
        waitForAjaxComplete();
    }

    public void submitForm() {
        btnGetMyQuotes.click();
        waitForAjaxComplete();
        if (getPageText().contains(Messages.EBUREAU_VERIFICATION)) {
            btnGetMyQuotes.click();
        }
        checkErrorsOnPage();
        waitListings();
    }

    public String getFirstNameValue() {
        return txtFirstName.getAttribute("value");
    }

    public String getLastNameValue() {
        return txtLastName.getAttribute("value");
    }

    public String getGenderValue() {
        return (Boolean.parseBoolean(rbtnMale.getAttribute("checked")) ? "Male" : "Female");
    }

    public String getStreetAddressValue() {
        return txtStreetAddress.getAttribute("value");
    }

    public String getZipCodeValue() {
        return txtZipCode.getAttribute("value");
    }

    public String getPhoneNumberValue() {
        return txtPhoneNumber.getAttribute("value");
    }

    public String getEmailValue() {
        return txtEmail.getAttribute("value");
    }

    public String getDateOfBirthValue() {
        // Apr 21, 1984
        String m = getSelectedValueFromDropDown(ddMonth);
        String d = getSelectedValueFromDropDown(ddDay);
        String y = getSelectedValueFromDropDown(ddYear);
        return m + " " + d + ", " + y;
    }

    public CompareAndSavePage fillInInvalidStreetAddressField(String s) {
        clearAndType(txtStreetAddress, s);
        txtFirstName.click(); // move focus to first name field
        waitForAjaxComplete();
        return this;
    }

    public CompareAndSavePage fillInTheRestFields(Contact contact, ExtraDataAutoMFS staticData) {
        selectByValue(ddEducation, staticData.getEducation());
        selectDate(ddMonth, ddDay, ddYear, contact.getBirthDate());
        return this;
    }

}
