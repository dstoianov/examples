package com.tipsandtricks.WebDriverWaitCustomExpectedConditions;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;

/**
 * Created by Funker on 10.10.2015.
 */

public class SelectContainsText implements ExpectedCondition<Boolean> {
    private String textToFind;
    private By findBy;

    public SelectContainsText(final By comboFindBy, final String textToFind) {
        this.findBy = comboFindBy;
        this.textToFind = textToFind;

    }

    public Boolean apply(WebDriver driver) {
        WebElement comboBox2 = driver.findElement(this.findBy);

            /* apply with xpath - no probs with stale element
            WebElement comboWithText = comboBox2.findElement(By.xpath("option[.='" + textToFind + "']"));

            return true;
            */

        // warning issue with stale element if dom changes mid loop
        List<WebElement> comboOptions = comboBox2.findElements(By.tagName("option"));

        try {
            for (WebElement element : comboOptions) {
                if (element.getText().equals(this.textToFind)) {
                    return true;
                }
            }
        } catch (StaleElementReferenceException e) {
            // if the combo changes in the middle of the apply call
            // we might encounter a StaleElementReferenceException
            // catch it and continue waiting
            return false;
        }


        return false;
    }

    public String toString() {
        return "select " + this.findBy + "to contain " + this.textToFind;
    }


}
