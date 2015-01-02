package tipsandtricks.hamcrestCustomeMatcher;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * User: eoff (eoff@yandex-team.ru)
 * Date: 19.12.13
 */
public class DocumentLoadedMatcher extends TypeSafeMatcher<WebDriver> {

    @Factory
    public static DocumentLoadedMatcher contentLoaded() {
        return new DocumentLoadedMatcher();
    }

    @Override
    protected boolean matchesSafely(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (Boolean) js.executeScript(
                "if (window.jQuery) { " +
                        "    return jQuery.active == 0 && document.readyState === 'complete'" +
                        "} else {" +
                        "    return document.readyState === 'complete'" +
                        "}"
        );
    }

    @Override
    protected void describeMismatchSafely(WebDriver driver, Description mismatchDescription) {
        mismatchDescription.appendText("Content not loaded");
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Content loaded");
    }
}
