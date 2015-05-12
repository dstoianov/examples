package com.tipsandtricks.findby.chaine;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Funker on 13.05.2015.
 */
public class Example {

    public WebDriver driver = new FirefoxDriver();

    @FindBys({@FindBy(tagName = "a"), @FindBy(tagName = "img")})
    List<WebElement> images;

    @Test
    public void testListToURL() {
        ByChained byChained = new ByChained(
                By.cssSelector("djfhdj"),
                By.tagName("div"),
                By.xpath("dfdh")
        );

        driver.findElement(byChained);
    }
}
