package tipsandtricks.custom.elementV2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tipsandtricks.custom.elementV2.decorator.CustomFieldDecoratorLists;
import tipsandtricks.custom.elementV2.impl.Button;
import tipsandtricks.custom.elementV2.impl.CheckBox;
import tipsandtricks.custom.elementV2.impl.Name;
import tipsandtricks.custom.elementV2.impl.Select;

import java.util.List;

/**
 * Created by Funker on 08.01.2015.
 */
public class ExamplePage {

    private WebDriver driver;

    @Name("Name for CheckBox 1")
    @FindBy(xpath = "(//input)[1]")
    public CheckBox checkBox;

    @Name("Name for CheckBox List 1")
    @FindBy(xpath = "//input")
    public List<CheckBox> checkBoxList;

    @Name("Name for list WebElements")
    @FindBy(xpath = "//input")
    public List<WebElement> webElementsList;

    @Name("Name for single WebElement")
    @FindBy(xpath = "(//input)[2]")
    public WebElement checkBox2;

    @Name("Name for Button")
    @FindBy(xpath = "//button")
    public Button button;

    @Name("Name for Select 1")
    @FindBy(xpath = "//*[@name='pulldown1']")
    private Select select;


    public ExamplePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new CustomFieldDecoratorLists(driver), this);
    }

    public Button getButton() {
        return button;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public WebElement getCheckBoxAsWebElement() {
        return checkBox2;
    }

    public List<CheckBox> getCheckBoxList() {
        return checkBoxList;
    }

    public Select getSelect() {
        return select;
    }

    public List<WebElement> getWebElementsList() {
        return webElementsList;
    }
}
