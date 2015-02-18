package com.revimedia.tests.builder.javascript;

import com.revimedia.tests.builder.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Funker on 10.02.2015.
 */
public class ElementHelper {

    public static void set(WebDriver driver, Element element, String value) {
        String type = element.getType().toLowerCase();
        boolean isInput = type.matches("input|zipUS|name|address|phoneUS|email".toLowerCase());
        WebElement wElement;
//        String type = getType(element);
//        String css = String.format(".bq-name-%s %s", element.getName(), type).trim();
//        WebElement wElement = driver.findElement(By.cssSelector(css));
        if (isInput) {
            System.out.println(String.format("'%s' type --> '%s', element name '%s'", element.getTitle(), value, element.getName()));
            String css = String.format(".bq-name-%s %s", element.getName(), "input");
            wElement = driver.findElement(By.cssSelector(css));
            wElement.click();
            wElement.clear();
            wElement.sendKeys(value);
            return;
        } else if (type.equalsIgnoreCase("select")) {
            System.out.println(String.format("'%s' select --> '%s', element name '%s'", element.getTitle(), value, element.getName()));
            String css = String.format(".bq-name-%s %s", element.getName(), "select");
            wElement = driver.findElement(By.cssSelector(css));
            selectByVisibleText(wElement, value);
            return;
        } else if (type.equalsIgnoreCase("radio")) {
            System.out.println(String.format("'%s' click --> '%s', element name '%s'", element.getTitle(), value, element.getName()));
            String css = String.format(".bq-name-%s .bq-%s", element.getName(), value);
            wElement = driver.findElement(By.cssSelector(css));
            wElement.click();
            return;
        } else if (type.equalsIgnoreCase("composite") && element.getName().equalsIgnoreCase("BirthDate")) {
            System.out.println(String.format("'%s' set --> '%s', element name '%s'", element.getTitle(), value, element.getName()));
            String css = String.format(".bq-name-%s-%s", type, element.getName());
            wElement = driver.findElement(By.cssSelector(css));
            selectDate(wElement, value);
            return;
        } else {
            new Exception(String.format("Unknown type of element '%s', element name '%s'", type, element.getName()));
        }

    }

    private static void setRadio(WebElement element, String value) {
        String innerHTML = element.getAttribute("innerHTML");
        //
        WebElement radio = element.findElement(By.xpath("..//"));
        WebElement element1 = radio.findElement(By.cssSelector(String.format("[value='%s']", value)));

        element1.click();
    }

    private static void selectDate(WebElement e, String birthDate) {
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

    private static void selectByVisibleText(WebElement webElement, String text) {
        new Select(webElement).selectByVisibleText(text);
    }

    private static String getType(Element element) throws Exception {
        String type = element.getType().toLowerCase();
        boolean isInput = type.matches("input|zipUS|name|address|email|radio".toLowerCase());
        if (isInput) {
            return "input";
        } else if (type.equalsIgnoreCase("select")) {
            return type;
//        } else if (type.equalsIgnoreCase("radio")) {
//            return "";
        } else {
            throw new Exception(String.format("Unknown type of element '%s'", type));
        }
    }


    public static void setSelectRandom(WebDriver driver, Element element) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
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


    public static void setInput(WebDriver driver, Element element, String value) {
        System.out.println(String.format("'%s' type --> '%s', element name '%s'", element.getTitle(), value, element.getName()));
        String css = String.format(".bq-name-%s %s", element.getName(), "input");
        WebElement e = driver.findElement(By.cssSelector(css));
        e.click();
        e.clear();
        e.sendKeys(value);
    }

    public static void setRadio(WebDriver driver, Element element, String value) {
        System.out.println(String.format("'%s' click --> '%s', element name '%s'", element.getTitle(), value, element.getName()));
        String css = "";
        if (element.getName().equalsIgnoreCase("AddExtraCar")) {
            css = String.format(".bq-cloning-adds .bq-add-%s", value);
        } else {
            css = String.format(".bq-name-%s .bq-%s", element.getName(), value);
        }
        WebElement e = driver.findElement(By.cssSelector(css));
        e.click();
    }

    public static void setBirthDay(WebDriver driver, Element element, String value) {
        System.out.println(String.format("'%s' set --> '%s', element name '%s'", element.getTitle(), value, element.getName()));
        String css = String.format(".bq-name-%s-%s", element.getType(), element.getName());
        WebElement e = driver.findElement(By.cssSelector(css));
        selectDate(e, value);
    }

    private static String getOption(WebElement e) {
        List<String> options = getAllOptionsFromSelect(e);
//        if (options.size() <= 0) {
//            new Exception("Has no any options in the drop down for select");
//        }
        String option = options.get((new Random().nextInt(options.size() - 1)));
        return option;
    }

    private static List<String> getAllOptionsFromSelect(WebElement element) {
        List<String> result = new ArrayList<>();
        List<WebElement> options = new Select(element).getOptions();
        for (WebElement option : options) {
            result.add(option.getText());
        }
        result.remove(0);// remove <option value="null"> --select-- </option>
//        log.info("\n----------------Displayed values in Drop Down -----------------\n" + result.toString() + "\n");
        return result;
    }
}
