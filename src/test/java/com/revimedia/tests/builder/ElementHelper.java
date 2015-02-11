package com.revimedia.tests.builder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Funker on 10.02.2015.
 */
public class ElementHelper {

    public static void set(WebDriver driver, Element element, String value) throws Exception {
        String type = getType(element);
        String css = String.format(".bq-name-%s %s", element.getName(), type);
        WebElement wElement = driver.findElement(By.cssSelector(css));
        if (type.equalsIgnoreCase("input")) {
            System.out.println(String.format("'%s' type --> '%s', element name '%s'", element.getTitle(), value, element.getName()));
            wElement.click();
            wElement.clear();
            wElement.sendKeys(value);
        } else if (type.equalsIgnoreCase("select")) {
            System.out.println(String.format("'%s' select --> '%s', element name '%s'", element.getTitle(), value, element.getName()));
            new Select(wElement).selectByVisibleText(value);
        } else {
            throw new Exception(String.format("Unknown type of element '%s'", type));
        }

    }


    private static String getType(Element element) throws Exception {
        String type = element.getType();
        if (type.equalsIgnoreCase("input") || type.equalsIgnoreCase("zipUS") || type.equalsIgnoreCase("name")
                || type.equalsIgnoreCase("address") || type.equalsIgnoreCase("email") || type.equalsIgnoreCase("email")) {
            return "input";
        } else if (type.equalsIgnoreCase("select")) {
            return type;
        } else {
            throw new Exception(String.format("Unknown type of element '%s'", type));
        }
    }

}
