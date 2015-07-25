package com.revimedia.tests.builder.newbuilder.helper;

import com.revimedia.tests.builder.exception.FrameworkException;
import com.revimedia.tests.builder.javascript.JSHelper;
import com.revimedia.tests.builder.newbuilder.core.CampaignBuilder;
import com.revimedia.tests.builder.newbuilder.core.Page;
import com.revimedia.tests.builder.newbuilder.dto.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Funker on 11.07.2015.
 */
public class ElementHelper {

    private static final Logger log = LoggerFactory.getLogger(ElementHelper.class);
    private WebDriver driver;
    protected JSHelper jsHelper;

    public ElementHelper(CampaignBuilder campaignBuilder) {
        this.driver = campaignBuilder.getDriver();
        this.jsHelper = new JSHelper(driver);
    }

    public void set(Element element, String value) {

        String type = element.getType().toLowerCase();
        boolean isInput = type.matches("input|zipUS|name|address|phoneUS|email".toLowerCase());
        WebElement webElement;

        if (isInput) {
            log.info("'{}' type --> '{}', element name '{}'", element.getTitle(), value, element.getName());
            String css = String.format(".bq-name-%s %s", element.getName(), "input");
            webElement = $(By.cssSelector(css));
            webElement.click();
            webElement.clear();
            webElement.sendKeys(value);
        } else if (type.equalsIgnoreCase("select") || type.equalsIgnoreCase("polk")) {
            log.info("'{}' select --> '{}', element name '{}'", element.getTitle(), value, element.getName());
            String css = String.format(".bq-name-%s %s", element.getName(), "select");
            webElement = $(By.cssSelector(css));

            if (type.equalsIgnoreCase("polk")) {
                selectByVisibleText(webElement, value);
                jsHelper.waitForAjaxComplete();
                $(By.cssSelector(".bq-add-no")).click();
//            } else if (value != null) {
//                selectByVisibleText(webElement, value);
            } else {
                selectByVisibleText(webElement, value);
            }
        } else if (type.equalsIgnoreCase("radio")) {
            log.info("'{}' click --> '{}', element name '{}'", element.getTitle(), value, element.getName());
            String css = String.format(".bq-name-%s .bq-%s", element.getName(), value);
            webElement = $(By.cssSelector(css));
            webElement.click();
        } else if (type.equalsIgnoreCase("composite") && element.getName().equalsIgnoreCase("BirthDate")) {
            log.info("'{}' set --> '{}', element name '{}'", element.getTitle(), value, element.getName());
            String css = String.format(".bq-name-%s-%s", type, element.getName());
            webElement = $(By.cssSelector(css));
            selectDate(webElement, value);
        } else {
            throw new FrameworkException(String.format("Unknown type of element '%s', element name '%s'", type, element.getName()));
        }

    }

    private void setRadio(WebElement element, String value) {
        String innerHTML = element.getAttribute("innerHTML");
        //
        WebElement radio = element.findElement(By.xpath("..//"));
        WebElement element1 = radio.findElement(By.cssSelector(String.format("[value='%s']", value)));

        element1.click();
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


    private void selectByVisibleText(WebElement webElement, Element element) {
        if (element.getValue() != null) {
            log.info("'{}' select --> '{}', element name '{}'", element.getTitle(), element.getValue(), element.getName());
            new Select(webElement).selectByVisibleText(element.getValue());
        } else {
            Select select = new Select(webElement);
            int max = select.getOptions().size();
            int min = 1;
            int i = min + (int) (new Random().nextFloat() * (max - min));
            select.selectByIndex(i);
            log.info("'{}' select random by index --> '{}', element name '{}'", element.getTitle(), i, element.getName());
        }
    }


    private void selectByVisibleText(WebElement webElement, String text) {
        if (text != null) {
            new Select(webElement).selectByVisibleText(text);
        } else {
            Select select = new Select(webElement);
            int max = select.getOptions().size();
            int min = 1;
            int i = min + (int) (new Random().nextFloat() * (max - min));
            select.selectByIndex(i);
        }
    }


    public void setSelectRandom(Element element) {
//        List<Object> sets = element.getSets();
//        if (element.getName().equalsIgnoreCase("Make")){
//            int i = sets.size() - 1;
//        }
//        int i = (new Random()).nextInt(sets.size()-1);
//        Object o = sets.get(i);
//        String value = null;
//        if (o instanceof String) {
//            value = o.toString();
//        } else if (o instanceof Map) {
//            value = BeanUtils.getProperty(o, "label");
//            if (value == null) {
//                value = BeanUtils.getProperty(o, "Value");
//            }
//        } else {
//            System.out.println(String.format("Unknown instance of object '%s'", o.getClass()));
//        }

        String css = String.format(".bq-name-%s %s", element.getName(), "select");
        WebElement e = driver.findElement(By.cssSelector(css));
        String option = getOption(e);

        System.out.println(String.format("'%s' select --> '%s', element name '%s'", element.getTitle(), option, element.getName()));
        selectByVisibleText(e, option);
    }


/*    public static void setInput(WebDriver driver, Element element, String value) {
        System.out.println(String.format("'%s' type --> '%s', element name '%s'", element.getTitle(), value, element.getName()));
        String css = String.format(".bq-name-%s %s", element.getName(), "input");
        WebElement e = driver.findElement(By.cssSelector(css));
        e.click();
        e.clear();
        e.sendKeys(value);
    }*/

/*    public static void setRadio(WebDriver driver, Element element, String value) {
        System.out.println(String.format("'%s' click --> '%s', element name '%s'", element.getTitle(), value, element.getName()));
        String css = "";
        if (element.getName().equalsIgnoreCase("AddExtraCar")) {
            css = String.format(".bq-cloning-adds .bq-add-%s", value);
        } else {
            css = String.format(".bq-name-%s .bq-%s", element.getName(), value);
        }
        WebElement e = driver.findElement(By.cssSelector(css));
        e.click();
    }*/

/*    public void setBirthDay(WebDriver driver, Element element, String value) {
        System.out.println(String.format("'%s' set --> '%s', element name '%s'", element.getTitle(), value, element.getName()));
        String css = String.format(".bq-name-%s-%s", element.getType(), element.getName());
        WebElement e = driver.findElement(By.cssSelector(css));
        selectDate(e, value);
    }*/

    private String getOption(WebElement e) {
        List<String> options = getAllOptionsFromSelect(e);
//        if (options.size() <= 0) {
//            new Exception("Has no any options in the drop down for select");
//        }
        String option = options.get((new Random().nextInt(options.size() - 1)));
        return option;
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
//        .bq-step1
            $(By.tagName("body")).click();
            log.info("Click 'Next' page");
            WebElement element = $(By.cssSelector(".bq-control.bq-type-simple"));
            element.click();
            jsHelper.waitForAjaxComplete();
        }
    }

    public void open(String guid) {
        String url = "http://development.stagingrevi.com/offer/?ovi=" + guid;
        log.info("\tOpen url " + url);
        driver.get(url);
        jsHelper.waitForAjaxComplete();
    }

    private boolean isOnThisPage(int stepNumber) {
        String currentUrl = driver.getCurrentUrl();
        int pageNumber = Integer.parseInt(currentUrl.substring(currentUrl.indexOf("#page/") + 6));
        return pageNumber == stepNumber;
    }

    private WebElement $(By by) {
//        log.info("\tFind element '{}'", by.toString());
        return driver.findElement(by);
    }

    private List<WebElement> $$(By by) {
//        log.info("\tFind elements '{}'", by.toString());
        return driver.findElements(by);
    }

    public void sleep(long i) {
        System.out.println("Waiting for a " + i / 1000 + " sec...");
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
