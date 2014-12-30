package tipsandtricks.htmlelements.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import tipsandtricks.htmlelements.web.elems.SearchArrow;

;

/**
 * @author Artem Eroshenko eroshenkoam
 *         5/6/13, 5:14 PM
 */
public class MainPage {

    private WebDriver driver;

    //    @FindBy(className = "b-morda-search-form")
    private SearchArrow searchArrow;

    public MainPage(final WebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
        this.driver = driver;
    }

    public SearchPage searchFor(String request) {
        this.searchArrow.search(request);
        return new SearchPage(driver);
    }
}