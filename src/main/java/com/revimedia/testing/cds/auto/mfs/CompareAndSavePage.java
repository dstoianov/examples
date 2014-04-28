package com.revimedia.testing.cds.auto.mfs;

import com.revimedia.testing.cds.Page;
import com.revimedia.testing.configuration.dto.Contact;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * User: stoianod
 * Date: 4/9/14
 */
public class CompareAndSavePage extends Page {

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


    public CompareAndSavePage fillInAllFields(Contact contact) {
        clearAndType(txtFirstName, contact.getFirstName());
        clearAndType(txtLastName, contact.getLastName());
        selectRandomByIndex(ddEducation);
        if (contact.getGender().equalsIgnoreCase("Male")) {
            rbtnMale.click();
        } else rbtnFemale.click();

        selectDate(ddMonth, ddDay, ddYear, contact.getBirthDate());

        clearAndType(txtStreetAddress, contact.getAddress());
        clearAndType(txtPhoneNumber, contact.getPhoneNumber());
        clearAndType(txtEmail, contact.getEmailAddress());
        return this;
    }


    public String getZipStateAndCity() {
        //txtZipCode.findElement()
        return null;
    }

    public void submitForm() {
        waitForAjaxComplete();
        btnGetMyQuotes.click();
    }
}
