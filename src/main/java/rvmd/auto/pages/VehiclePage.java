package rvmd.auto.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * User: stoianod
 * Date: 4/9/14
 */
public class VehiclePage extends Page {

    public VehiclePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[contains(@class, 'Year')]//select")
    private WebElement ddVehicleBuildYear;

    @FindBy(xpath = "//div[contains(@class, 'Make')]//select")
    private WebElement ddVehicleMake;

    @FindBy(xpath = "//div[contains(@class, 'Model')]//select")
    private WebElement ddVehicleModel;

    @FindBy(xpath = "//button") //button class="bq-control bq-type-simple">
    private WebElement btnContinue;

    public VehiclePage fillInAllFields() {
        selectByValue(ddVehicleBuildYear, "2005");
        selectByValue(ddVehicleMake, "FORD");
        selectByValue(ddVehicleModel, "GT");
        return this;
    }


    public CompareAndSavePage clickOnContinue() {
        btnContinue.click();
        return new CompareAndSavePage(driver);
    }
}
