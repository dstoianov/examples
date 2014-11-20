package com.revimedia.testing.cds.auto.mfs.pages;

import com.revimedia.testing.cds.Page;
import com.revimedia.testing.cds.auto.staticdata.ExtraDataAutoMFS;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * User: Denis Stoianov
 * Date: 4/9/14
 */
public class VehiclePage extends Page {
    protected final Logger log = Logger.getLogger(this.getClass());
    @FindBy(xpath = "//div[contains(@class, 'Year')]//select")
    private WebElement ddVehicleBuildYear;
    @FindBy(xpath = "//div[contains(@class, 'Make')]//select")
    private WebElement ddVehicleMake;
    @FindBy(xpath = "//div[contains(@class, 'Model')]//select")
    private WebElement ddVehicleModel;
    @FindBy(xpath = "//div[contains(@class, 'cloning-adds-buttons')]/div")
    private List<WebElement> rbtnExtraCar;
    @FindBy(xpath = "//button") //button class="bq-control bq-type-simple">
    private WebElement btnContinue;

    public VehiclePage(WebDriver driver) {
        super(driver);
        log.info("Vehicle Details Page is Loaded, STEP #2");
    }

    public VehiclePage fillInAllFields(ExtraDataAutoMFS staticData) {
        selectByValue(ddVehicleBuildYear, staticData.getYear());
        waitForAjaxComplete();
        selectByValue(ddVehicleMake, staticData.getMake());
        waitForAjaxComplete();
        selectByValue(ddVehicleModel, staticData.getModel());

        // TODO: workaround until 'cloning vehicle' will be implemented for auto/p campaign
        if (driver.getCurrentUrl().contains("/auto/mfs/")) {
            rbtnExtraCar.get(1).click();
        }
        return this;
    }


    public CompareAndSavePage clickOnContinue() {
        btnContinue.click();
        return new CompareAndSavePage(driver);
    }

    public List<String> getAllMadeCarsList() {
        return getAllValuesFromDropDown(ddVehicleMake);
    }

    public List<String> getAllModelsList() {
        return getAllValuesFromDropDown(ddVehicleModel);
    }
}
