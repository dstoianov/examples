package com.tipsandtricks.mockito;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Funker on 10.02.2015.
 */
public class MockitoTests {

    private static final String TEXT = "text";
    private static final String ERROR_TEXT = "error text";
    private WebDriver driver = MockFactory.mockDriver();

    @Test
    public void shouldSeeThatHasTextMatchersWorkTheSameWay() {
        HtmlElement element = mock(HtmlElement.class);
        WebElement wrappedElement = mock(WebElement.class);

        when(element.getText()).thenReturn(TEXT);
        when(element.getWrappedElement()).thenReturn(wrappedElement);
        when(wrappedElement.getText()).thenReturn(ERROR_TEXT);


        assertThat(element.getText(), is(TEXT));
        assertThat(element.getWrappedElement().getText(), is(ERROR_TEXT));
    }

    @Test
    public void isElementDisplayed() {
        WebElement element = MockFactory.mockDisplayedElement(By.className("some fake css"), driver);
        assertThat(element.isDisplayed(), is(true));
    }

    @Test
    public void testName() throws Exception {
        assertThat("some text", endsWith("texte"));

    }
}
