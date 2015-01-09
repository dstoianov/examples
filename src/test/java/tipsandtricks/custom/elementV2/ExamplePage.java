package tipsandtricks.custom.elementV2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tipsandtricks.custom.elementV2.core.CustomFieldDecoratorLists;
import tipsandtricks.custom.elementV2.impl.Button;
import tipsandtricks.custom.elementV2.impl.CheckBox;
import tipsandtricks.custom.elementV2.impl.Select;

import java.util.List;

/**
 * Created by Funker on 08.01.2015.
 */
public class ExamplePage {

    @FindBy(xpath = "(//input)[1]")
    public CheckBox checkBox;
    @FindBy(xpath = "//input")
    public List<CheckBox> checkBoxList;
    @FindBy(xpath = "(//input)[2]")
    public WebElement checkBox2;
    @FindBy(xpath = "//button")
    public Button button;
    private WebDriver driver;
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
}
