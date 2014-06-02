package com.revimedia.testing.cds.auto.mfs.pages;

import com.revimedia.testing.cds.Page;
import com.revimedia.testing.cds.auto.staticdata.StaticDataAutoMFS;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * User: stoianod
 * Date: 4/9/14
 */
public class VehiclePage extends Page {
    protected final Logger log = Logger.getLogger(this.getClass());

    public VehiclePage(WebDriver driver) {
        super(driver);
        log.info("Vehicle Details Page is Loaded, STEP #2");
    }

    @FindBy(xpath = "//div[contains(@class, 'Year')]//select")
    private WebElement ddVehicleBuildYear;

    @FindBy(xpath = "//div[contains(@class, 'Make')]//select")
    private WebElement ddVehicleMake;

    @FindBy(xpath = "//div[contains(@class, 'Model')]//select")
    private WebElement ddVehicleModel;

    @FindBy(xpath = "//input[@type='radio']")
    private List<WebElement> rbtnExtraCar;

    @FindBy(xpath = "//button") //button class="bq-control bq-type-simple">
    private WebElement btnContinue;

    public VehiclePage fillInAllFields(StaticDataAutoMFS staticData) {
        selectByValue(ddVehicleBuildYear, staticData.getYear());
//        waitForSelectFill(ddVehicleMake);
        waitForAjaxComplete();
        selectByValue(ddVehicleMake, staticData.getMake());
        //      waitForSelectFill(ddVehicleModel);
        waitForAjaxComplete();
        selectByValue(ddVehicleModel, staticData.getModel());

        // TODO: workaround until 'cloning vehicle' will be implemented for auto/p campaign
        if (!driver.getCurrentUrl().contains("/auto/s/")) {
            rbtnExtraCar.get(1).click();
        }
        return this;
    }


    public CompareAndSavePage clickOnContinue() {
        btnContinue.click();
        return new CompareAndSavePage(driver);
    }

    public List<String> getAllMakedCarsList() {
        return getAllValuesFromDropDown(ddVehicleMake);
    }

    public List<String> getAllModelsList() {
        return getAllValuesFromDropDown(ddVehicleModel);
    }
}
