package tipsandtricks.custom.element.elements.impl;

import org.openqa.selenium.WebElement;
import tipsandtricks.custom.element.elements.CheckBox;

/**
 * Created by Funker on 07.01.2015.
 */
public class CheckBoxImpl extends ElementImpl implements CheckBox {

    public CheckBoxImpl(WebElement element) {
        super(element);
    }

    public void toggle() {
        getWrappedElement().click();
    }

    public void check() {
        if (!isChecked()) {
            toggle();
        }
    }

    public void uncheck() {
        if (isChecked()) {
            toggle();
        }
    }

    public boolean isChecked() {
        return getWrappedElement().isSelected();
    }


}
