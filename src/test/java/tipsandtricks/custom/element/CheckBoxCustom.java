package tipsandtricks.custom.element;

import org.openqa.selenium.WebElement;

/**
 * Created by dstoianov on 2014-12-26.
 */
public class CheckBoxCustom extends ElementImpl implements CheckBox {

    public CheckBoxCustom(WebElement element) {
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

    @Override
    public boolean elementWired() {
        return false;
    }
}
