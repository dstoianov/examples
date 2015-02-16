package com.revimedia.tests.builder.javascript;

import com.revimedia.tests.builder.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

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


    public static void main(String[] args) {
        String s = "zipUS";
        String ss = "input|zipUS|name|address|email";
        boolean isInput1 = s.matches("input|zipUS|name|address|email");
        boolean isInput2 = s.toLowerCase().matches("input|zipUS|name|address|email");

        System.out.println("Hello World!");
    }

}
