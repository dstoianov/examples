package com.tipsandtricks.html.elements.example.tests;

import com.tipsandtricks.html.elements.example.pages.MainPage;
import com.tipsandtricks.html.elements.example.pages.SearchPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Artem Eroshenko eroshenkoam
 *         5/6/13, 2:51 PM
 */
public class SearchingByRequestTest {

    private final int DEFAULT_RESULTS_COUNT;
    public WebDriver driver = new ChromeDriver();

    public SearchingByRequestTest() {
        DEFAULT_RESULTS_COUNT = 10;
    }

    @Before
    public void loadStartPage() {
        driver.get("http://www.yandex.com");
    }

    @Test
    public void afterSearchingUserShouldSeSearchResults() {
        MainPage mainPage = new MainPage(driver);
        SearchPage page = mainPage.searchFor("Yandex");
        assertThat(page.getSearchResults(), is(notNullValue()));
        assertThat(page.getSearchResults().getSearchItems(), hasSize(DEFAULT_RESULTS_COUNT));
    }

    @After
    public void killWebDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}