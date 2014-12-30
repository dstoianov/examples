package tipsandtricks.custom.element;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;

/**
 * Created by dstoianov on 2014-12-26.
 */
public interface Element extends WebElement, WrapsElement, Locatable {
    boolean elementWired();
}
