package tipsandtricks.custom.element.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;
import tipsandtricks.custom.element.core.ImplementedBy;
import tipsandtricks.custom.element.elements.impl.ElementImpl;

/**
 * Created by dstoianov on 2014-12-26.
 */

@ImplementedBy(ElementImpl.class)
public interface Element extends WebElement, WrapsElement, Locatable {
    /**
     * Returns true when the inner element is ready to be used.
     *
     * @return boolean true for an initialized WebElement, or false if we were somehow passed a null WebElement.
     */
    boolean elementWired();

    String getHtml();
}
