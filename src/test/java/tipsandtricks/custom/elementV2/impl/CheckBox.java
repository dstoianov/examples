package tipsandtricks.custom.elementV2.impl;

import org.openqa.selenium.WebElement;

/**
 * Created by Funker on 07.01.2015.
 */

public class CheckBox extends Element {

    public CheckBox(WebElement webElement) {
        super(webElement);
    }

    public boolean isChecked() {
        return webElement.isSelected();
    }

    public void setChecked(boolean value) {
        if (value != isChecked()) {
            webElement.click();
        }
    }
}
