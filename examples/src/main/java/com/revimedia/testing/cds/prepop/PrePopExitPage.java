package com.revimedia.testing.cds.prepop;

import com.revimedia.testing.cds.Page;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;

/**
 * Created by dstoianov on 5/6/2014, 6:47 PM.
 */
public class PrePopExitPage extends Page {
    protected final Logger log = Logger.getLogger(this.getClass());

    public PrePopExitPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1")
    private WebElement lblH1;

    @FindBy(xpath = "//*[@id='exitPop']//a")
    private WebElement lnkPhoneNumber;

    @FindBy(id = "btnStay")
    private WebElement btnStayAndReceiveFREEQuotes;


    public void reloadPageWithPrePopTrue() {
        log.info("Reload current page with \"prepop = true\" parameters...");
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.substring(currentUrl.length() - 1).equals("/")) {
            currentUrl = currentUrl.substring(0, currentUrl.length() - 1);
        }
        driver.get(currentUrl + "/?exit=true");
        waitForAjaxComplete();
    }

    public void prePopShowUp() {
        log.info("Pre Pop Showing up...");
        try {
            Robot robot = new Robot();
            org.openqa.selenium.Dimension size = driver.manage().window().getSize();
            for (int i = 150; i > 70; i--) {
                robot.mouseMove(size.getWidth() / 2, i);
                Thread.sleep(30);
            }
//        robot.mouseMove(size.getWidth() / 2, size.getHeight() / 2);
//        robot.mouseMove(size.getWidth() / 2, 110); //IE10 only in windows maximize
//        robot.mouseMove(size.getWidth() / 2, 105); //FF and Chrome
//        robot.mouseMove(size.getWidth() / 2, 70); // FF and Chrome
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getHeader() {
        return lblH1.getText();
    }

    public String getPhoneTextLink() {
        return lnkPhoneNumber.getText();
    }

    public void closePopUp() {
        btnStayAndReceiveFREEQuotes.click();
    }
}
