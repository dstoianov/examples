package com.tipsandtricks.html.elements.example.pages;

import com.tipsandtricks.html.elements.example.elems.SearchArrow;
import com.tipsandtricks.html.elements.example.elems.SearchResults;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;


/**
 * @author Artem Eroshenko eroshenkoam
 *         5/6/13, 5:15 PM
 */
public class SearchPage {

    private WebDriver driver;

    @FindBy(className = "content__left")
    private SearchResults searchResults;

    private SearchArrow searchArrow;


    public SearchPage(WebDriver driver) {
        HtmlElementLoader.populatePageObject(this, driver);
        this.driver = driver;
    }

    public SearchPage searchFor(String request) {
        this.searchArrow.searchFor(request);
        return this;
    }

    public SearchResults getSearchResults() {
        return this.searchResults;
    }
}
