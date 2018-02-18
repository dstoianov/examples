package com.tipsandtricks.html.elements.example.pages;

import com.tipsandtricks.html.elements.example.elems.SearchArrow;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

/**
 * @author Artem Eroshenko eroshenkoam
 *         5/6/13, 5:14 PM
 */
public class MainPage {

    private WebDriver driver;

    @FindBy(css = ".search2")
    private SearchArrow searchArrow;

    public MainPage(final WebDriver driver) {
        HtmlElementLoader.populatePageObject(this, driver);
        this.driver = driver;
    }

    public SearchPage searchFor(String request) {
        this.searchArrow.searchFor(request);
        return new SearchPage(driver);
    }
}