package com.revimedia.testing.cds.auto.mfs.pages;

import com.revimedia.testing.cds.Page;
import com.revimedia.testing.cds.auto.staticdata.ExtraDataAutoMFS;
import com.revimedia.testing.configuration.dto.Contact;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * User: Denis Stoianov
 * Date: 4/9/14
 */
public class DriverPage extends Page {
    protected final Logger log = Logger.getLogger(this.getClass());
    // .//input[ancestor::div]
    @FindBy(xpath = "//div[contains(@class, 'ZipCode')]//input")
    private WebElement txtZipCode;
    @FindBy(xpath = "//div[contains(@class, 'InsuranceCompany')]//select")
    private WebElement ddInsuranceCompany;
    @FindBy(xpath = "//div[contains(@class, 'InsuredSince')]//select")
    private WebElement ddInsuredSince;
    @FindBy(xpath = "//div[contains(@class, 'ResidenceType')]//select")
    private WebElement ddResidenceType;
    @FindBy(xpath = "//div[contains(@class, 'MaritalStatus')]//select")
    private WebElement ddMaritalStatus;
    @FindBy(xpath = "//div[contains(@class, 'CreditRating')]//select")
    private WebElement ddCreditRating;
    @FindBy(xpath = "//button") //button class="bq-control bq-type-simple">
    private WebElement btnContinue;

    public DriverPage(WebDriver driver) {
        super(driver);
        log.info("Driver Page is Loaded, STEP #1");
    }

    // Fill in all fields
    // Need to split by groups, because not all fields available after prepop
    public DriverPage fillInAllFields(Contact contact, ExtraDataAutoMFS staticData) {
        // Not available after prepop
        waitUntilDisplayed(txtZipCode);
        clearAndType(txtZipCode, contact.getZipCode());
        setInsuranceCompany(staticData);
        // Available after prepop
        fillInTheRestFields(staticData);
        return this;
    }

    public VehiclePage clickOnContinue() {
        waitUntilToBeClickable(btnContinue);
        btnContinue.click();
        return new VehiclePage(driver);
    }

    public DriverPage fillInZipCode(String text) {
        clearAndType(txtZipCode, text);
        return this;
    }

    public String getInsuranceCompanyValue() {
        return getSelectedValueFromDropDown(ddInsuranceCompany);
    }

    public boolean isZipCodeFieldDisplayed() {
        return txtZipCode.isDisplayed();
    }

    public DriverPage fillInTheRestFields(ExtraDataAutoMFS staticData) {
        if (!staticData.getInsuranceCompany().equalsIgnoreCase("Currently not insured")) {
            waitUntilDisplayed(ddInsuredSince);
            selectByValue(ddInsuredSince, staticData.getInsuredSince());
        }
        selectByValue(ddResidenceType, staticData.getResidenceType());
        selectByValue(ddMaritalStatus, staticData.getMaritalStatus());
        selectByValue(ddCreditRating, staticData.getCreditRating());
        return this;
    }

    public DriverPage setInsuranceCompany(ExtraDataAutoMFS staticData) {
        // Prepoped with value
        selectByValue(ddInsuranceCompany, staticData.getInsuranceCompany());
        return this;
    }

}
