package tipsandtricks.htmlelements.web.elems;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

/**
 * @author Artem Eroshenko eroshenkoam
 *         5/6/13, 5:13 PM
 */

@Name("Search form")
@FindBy(xpath = "//form")
public class SearchArrow extends HtmlElement {

    @Name("Search request input")
    @FindBy(id = "text")
    private TextInput requestInput;

    @Name("Search button")
    @FindBy(className = "button")
    private Button searchButton;

    public void search(String request) {
        requestInput.sendKeys(request);
        searchButton.click();
    }
}
