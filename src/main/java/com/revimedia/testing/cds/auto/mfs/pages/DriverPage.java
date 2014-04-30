package com.revimedia.testing.cds.auto.mfs.pages;

import com.revimedia.testing.cds.Page;
import com.revimedia.testing.cds.auto.staticdata.StaticDataAutoMFS;
import com.revimedia.testing.configuration.dto.Contact;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * User: stoianod
 * Date: 4/9/14
 */
public class DriverPage extends Page {

    public DriverPage(WebDriver driver) {
        super(driver);
    }

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


    public DriverPage fillInAllFields(Contact contact, StaticDataAutoMFS staticData) {
        clearAndType(txtZipCode, contact.getZipCode());
        selectByValue(ddInsuranceCompany, staticData.getInsuranceCompany());
        selectByValue(ddInsuredSince, staticData.getInsuredSince());
        selectByValue(ddResidenceType, staticData.getResidenceType());
        selectByValue(ddMaritalStatus, staticData.getMaritalStatus());
        selectByValue(ddCreditRating, staticData.getCreditRating());
        return this;
    }

    public VehiclePage clickOnContinue() {
        btnContinue.click();
        return new VehiclePage(driver);
    }

    public DriverPage fillInZipCode(String text) {
        clearAndType(txtZipCode, text);
        return this;
    }
}
