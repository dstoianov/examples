package rvmd.auto.pages;

import org.databene.benerator.script.BeneratorParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

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


    public DriverPage fillInAllFields(String zipCode) {
        clearAndType(txtZipCode, zipCode);
        selectByValue(ddInsuranceCompany, "AAA");
        selectByValue(ddInsuredSince, "4");
        selectByValue(ddResidenceType, "Other");
        selectByValue(ddMaritalStatus, "Married");
        selectByValue(ddCreditRating, "Good");
        return this;
    }

    public VehiclePage clickOnContinue() {
        btnContinue.click();
        return new VehiclePage(driver);
    }
}
