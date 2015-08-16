package com.revimedia.tests.builder.newbuilder.helper;

import com.revimedia.testing.json2pojo.field.Composite;
import com.revimedia.tests.builder.exception.FrameworkException;
import com.revimedia.tests.builder.javascript.JSHelper;
import com.revimedia.tests.builder.newbuilder.core.CampaignBuilder;
import com.revimedia.tests.builder.newbuilder.core.ConciseAPI;
import com.revimedia.tests.builder.newbuilder.core.Page;
import com.revimedia.tests.builder.newbuilder.dto.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Funker on 11.07.2015.
 */
public class ElementHelper extends ConciseAPI {

    private static final Logger log = LoggerFactory.getLogger(ElementHelper.class);
    private WebDriver driver;
    protected JSHelper jsHelper;
    private static final int DEFAULT_TIMEOUT = 10;
    private WebDriverWait wait;

    public ElementHelper(CampaignBuilder campaignBuilder) {
        this.driver = campaignBuilder.getDriver();
        this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        this.jsHelper = new JSHelper(driver);
    }

    @Override
    public WebDriver getDriver() {
        return driver;
    }

    public void set(Element element) {
        String type = element.getType().toLowerCase();
        boolean isInput = type.matches("input|zipUS|name|address|phoneUS|email".toLowerCase());

        if (isInput) {
            setInput(element);
        } else if (type.equalsIgnoreCase("select") || type.equalsIgnoreCase("polk")) {
            setSelect(element);
        } else if (type.equalsIgnoreCase("radio")) {
            setRadio(element);
        } else if (type.equalsIgnoreCase("composite")) {
            setComposite(element);
        } else {
            throw new FrameworkException(String.format("Unknown type of element '%s', element name '%s'", type, element.getName()));
        }
    }

    private void setComposite(Element element) {
        String css = String.format(".bq-name-%s-%s", element.getType(), element.getName());
        WebElement webElement;
        if (element.getName().equalsIgnoreCase("BirthDate")) {
            log.info("'{}' set --> '{}', element name '{}'", element.getTitle(), element.getDisplayedText(), element.getName());
            selectDate($(css), element.getDisplayedText());
        } else {
            for (Composite c : element.getComposite()) {
                if (!c.getHidden()) {
                    log.info("'{}' set --> '{}', element name '{} - {}'", element.getTitle(), c.getDisplayedText(), element.getName(), c.getName());
                    String cssComposite = String.format("%s .bq-name-%s select", css, c.getName());
                    webElement = $(cssComposite);
                    selectByVisibleText(webElement, c.getDisplayedText());
                }
            }
        }
    }

    private void setSelect(Element element) {
        log.info("'{}' select --> '{}', element name '{}'", element.getTitle(), element.getDisplayedText(), element.getName());
        String css = String.format(".bq-name-%s %s", element.getName(), "select");
        WebElement webElement = $(css);

        if (element.getType().equalsIgnoreCase("polk")) {
            selectByVisibleText(webElement, element.getDisplayedText());
            jsHelper.waitForAjaxComplete();
            $(".bq-add-no").click();
        } else {
            selectByVisibleText(webElement, element.getDisplayedText());
        }
    }

    private void setRadio(Element element) {
        log.info("'{}' click --> '{}', element name '{}'", element.getTitle(), element.getDisplayedText(), element.getName());
        try {
            String css = String.format(".bq-name-%s .bq-%s", element.getName(), element.getDisplayedText());
            $(css).click();
        } catch (NoSuchElementException e) {
            List<WebElement> elements = $$(String.format(".bq-name-%s .bq-label", element.getName()));
            for (WebElement label : elements) {
                if (label.getText().equalsIgnoreCase(element.getDisplayedText())) {
                    label.click();
                    break;
                }
            }
        }
    }

    private void setInput(Element element) {
        log.info("'{}' type --> '{}', element name '{}'", element.getTitle(), element.getDisplayedText(), element.getName());
        String css = String.format(".bq-name-%s %s", element.getName(), "input");
        WebElement webElement = $(css);
        if (!webElement.isEnabled()) {
            log.info("Element '{}' is disabled with text '{}', skip typing...", element.getName(), webElement.getAttribute("value"));
            return;
        }
        webElement.click();
        webElement.clear();
        webElement.sendKeys(element.getDisplayedText());
    }


    private void selectDate(WebElement e, String birthDate) {
        //Mar 12, 1987
        int blank = birthDate.indexOf(" ");
        int comma = birthDate.indexOf(",");
        String month = birthDate.substring(0, 3);
        String day = birthDate.substring(birthDate.indexOf(" ") + 1, comma);
        String year = birthDate.substring(comma + 2);
        selectByVisibleText(e.findElement(By.cssSelector(".bq-name-Month select")), month);
        selectByVisibleText(e.findElement(By.cssSelector(".bq-name-Day select")), day);
        selectByVisibleText(e.findElement(By.cssSelector(".bq-name-Year select")), year);
    }


    private void selectByVisibleText(WebElement webElement, String text) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
        new Select(webElement).selectByVisibleText(text);
    }

    private List<String> getAllOptionsFromSelect(WebElement element) {
        List<String> result = new ArrayList<>();
        List<WebElement> options = new Select(element).getOptions();
        for (WebElement option : options) {
            result.add(option.getText());
        }
        result.remove(0);// remove <option value="null"> --select-- </option>
//        log.info("\n----------------Displayed values in Drop Down -----------------\n" + result.toString() + "\n");
        return result;
    }

    public void nextPage(Page p) {
        if (isOnThisPage(p.getStepNumber())) {
            $("body").click(); // move focus to element
            log.info("Click 'Next' page");
            if (!isOnThisPage(p.getStepNumber() + 1)) {
//                click again if still on this page
                $(".bq-control.bq-type-simple").click();
            }
            jsHelper.waitForAjaxComplete();
        }
    }

    @Override
    public void open(String guid) {
        super.open("http://development.stagingrevi.com/offer/?ovi=" + guid);
        jsHelper.waitForAjaxComplete();
    }

    private boolean isOnThisPage(int stepNumber) {
        String currentUrl = driver.getCurrentUrl();
        int pageNumber = Integer.parseInt(currentUrl.substring(currentUrl.indexOf("#page/") + 6));
        return pageNumber == stepNumber;
    }


}
