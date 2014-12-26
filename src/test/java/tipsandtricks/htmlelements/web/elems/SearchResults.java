package tipsandtricks.htmlelements.web.elems;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

/**
 * @author Artem Eroshenko eroshenkoam
 *         5/6/13, 5:13 PM
 */

@Name("Search Results")
@FindBy(className = "serp-list")
public class SearchResults extends HtmlElement {

    @SuppressWarnings("unused")
    @FindBy(xpath = ".//*[contains(@class, 'serp-item_plain_yes')]")
    private List<WebElement> searchItems;

    public List<WebElement> getSearchItems() {
        return searchItems;
    }
}
