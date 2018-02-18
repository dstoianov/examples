package com.tipsandtricks.html.elements.example.elems;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

/**
 * @author Artem Eroshenko eroshenkoam
 *         5/6/13, 5:13 PM
 */
public class SearchArrow extends HtmlElement {

    @FindBy(css = ".input__input")
    public TextInput requestInput;

    @FindBy(css = "button")
    public Button searchButton;

    public void searchFor(String request) {
        requestInput.clear();
        requestInput.sendKeys(request);
        searchButton.click();
    }
}
