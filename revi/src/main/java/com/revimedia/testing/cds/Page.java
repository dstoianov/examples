package com.revimedia.testing.cds;

import com.revimedia.testing.cds.constants.Messages;
import com.revimedia.testing.configuration.ActionLogger;
import com.revimedia.testing.configuration.config.Config;
import com.revimedia.testing.configuration.helpers.DataHelper;
import com.revimedia.testing.configuration.utils.JsUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.revimedia.testing.cds.PageConstants.*;
import static com.revimedia.testing.cds.constants.Disclaimers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;

/**
 * User: Denis Stoianov
 * Date: 4/9/14
 */
public class Page {
    protected static final Logger log = Logger.getLogger(Page.class);
    private static final int DEFAULT_TIMEOUT = 10;
    private static final ActionLogger logAction = new ActionLogger();
    public WebDriver driver;
    private JavascriptExecutor js;
    private WebDriverWait wait;
    private JsUtils jsUtils;

    public Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        this.jsUtils = new JsUtils(driver);
        PageFactory.initElements(driver, this);
    }

    private static Object executeJavascript(WebDriver driver, String script) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript(script);
    }

    public static void waitForIframes(final WebDriver webDriver, final int expectedCount) {
        Boolean webDriverWaiting = false;
        log.info("Wating for iframes to load");
        while (!webDriverWaiting) {
            log.info("waiting...");
            webDriverWaiting = new WebDriverWait(webDriver, 10).until(new ExpectedCondition<Boolean>() {

                /**
                 * @param driver {@link org.openqa.selenium.WebDriver} to be used
                 * @return true if all required iframes are present, false otherwise
                 */
                @Override
                public Boolean apply(final WebDriver driver) {
                    final List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
                    final Integer size = iframes.size();
                    log.info("number of current iframes [" + size + "]");
                    if (size >= expectedCount) {
                        for (final WebElement iframe : iframes) {
                            log.info("iframe ID: [" + iframe.getAttribute("id") + "]");
                        }
                    }
                    return (size >= expectedCount);
                }
            });
        }
        log.info("Iframes loaded completely");
    }

    public void clearAndType(WebElement element, String text) {
        logAction.verbose("Clear and Type", element, text);
        int i = 0;
        do {
            i++;
            element.click();
            element.clear();
            element.sendKeys(text);
        } while (!element.getAttribute("value").equals(text) && i < 3);
        element.click();
    }

    public void selectByValue(WebElement webElement, String text) {
//        waitForSelectFill(driver, webElement);
        logAction.verbose("select by text", webElement, text);
        new Select(webElement).selectByVisibleText(text);
        //waitForAjaxComplete();
    }

    public void selectRandomByIndex(WebElement webElement) {
        waitForAjaxComplete();
        Select select = new Select(webElement);
        List<WebElement> elements = select.getOptions();
        select.selectByIndex(DataHelper.randInt(1, elements.size() - 1));
    }

    public void selectRandomByIndexFromSecondOptions(WebElement webElement) {
        waitForAjaxComplete();
        Select select = new Select(webElement);
        List<WebElement> elements = select.getOptions();
        select.selectByIndex(DataHelper.randInt(2, elements.size() - 1));
    }

    public String getPageText() {
        return driver.findElement(By.xpath(PAGE_CONTENT)).getText();
    }

    public List<WebElement> getAllErrors() {
        return driver.findElements(By.xpath(PAGE_ERRORS));
    }

    public List<String> getErrorsOnBottomPageAsList() {
        List<WebElement> errorsOnBottomPage = driver.findElements(By.xpath(PAGE_ERRORS_BOTTOM_CONTAINER));
        List<String> listSorted = new ArrayList<>();
        for (int i = 0; i < errorsOnBottomPage.size(); i++) {
            listSorted.add(errorsOnBottomPage.get(i).getText());
        }
        Collections.sort(listSorted);
        return listSorted;
    }

    public void waitForAjaxComplete() {
        log.info("waiting for ajax completion");
        final JsUtils js = new JsUtils(driver);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return js.isAjaxComplete();
            }
        });
        log.info("All ajax calls are complete");
    }

    public void waitForSelectFill(WebElement selectElement) {
        log.info("waiting for select fill in some data");
        final Select select = new Select(selectElement);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return select.getOptions().size() > 1;
            }
        });
        log.info("select is filled in");
    }

    public void selectDate(WebElement ddMonth, WebElement ddDay, WebElement ddYear, String birthDate) {
        log.info("Filling in date: " + birthDate);
        //Mar 12, 1987
        int blank = birthDate.indexOf(" ");
        int comma = birthDate.indexOf(",");
        String month = birthDate.substring(0, 3);
        String day = birthDate.substring(birthDate.indexOf(" ") + 1, comma);
        String year = birthDate.substring(comma + 2);
        selectByValue(ddMonth, month);
        selectByValue(ddDay, day);
        selectByValue(ddYear, year);
    }

    public void fillInBirthDate(List<WebElement> el, String birthDate) {
        log.info("Filling in date: " + birthDate);
        //Mar 12, 1987
        int blank = birthDate.indexOf(" ");
        int comma = birthDate.indexOf(",");
        String month = birthDate.substring(0, 3);
        String day = birthDate.substring(birthDate.indexOf(" ") + 1, comma);
        String year = birthDate.substring(comma + 2);
        selectByValue(el.get(0), month);
        selectByValue(el.get(1), day);
        selectByValue(el.get(2), year);
    }

    public void verifyPrivacyPolicyAndTermsOfUseLinks() {
        log.info("Verifying Privacy Policy And Terms Of Use(or Conditions) Links...");
        assertThat("PRIVACY POLICY link is absent on Page", isElementPresent(By.linkText(PRIVACY_POLICY)), is(true));
        assertThat("TERM OF USE link is absent on Page", isElementPresent(By.linkText(TERM_OF_USE)), is(true));
//        JsUtils jsUtils = new JsUtils(driver);
        assertThat(jsUtils.getPPAndTU(), equalToIgnoringWhiteSpace(PP_TU));
        log.info("Links are presets and text is matched as expected");
        log.info("-------------------------------------------------");
    }

    public void verifyPrivacyPolicyAndTermsAndConditionalLinks() {
        log.info("Verifying Privacy Policy And Terms Of Use(or Conditions) Links...");
        assertThat("PRIVACY POLICY link is absent on Page", isElementPresent(By.linkText(PRIVACY_POLICY)), is(true));
        assertThat("TERM AND CONDITIONS link is absent on Page", isElementPresent(By.linkText(TERM_AND_CONDITIONS)), is(true));
//        JsUtils jsUtils = new JsUtils(driver);
        assertThat(jsUtils.getPPAndTC().replace("&nbsp;&nbsp;&nbsp;", " "), equalToIgnoringWhiteSpace(PP_TC));
        log.info("Links are presets and text is matched as expected");
        log.info("-------------------------------------------------");
    }

    public void verifyTCPADisclaimerAndLinks(String tcpa_disclaimer) {
        log.info("Verifying TCPA Disclaimer And Links...");
        assertThat("INSURANCE COMPANIES link is absent on Page", isElementPresent(By.linkText(INSURANCE_COMPANIES)), is(true));
//        JsUtils jsUtils = new JsUtils(driver);
        assertThat(jsUtils.getTCPADisclaimer().replaceAll(" ", ""), equalToIgnoringWhiteSpace(tcpa_disclaimer.replaceAll(" ", "")));
        log.info("Link is preset and text is matched as expected");
        log.info("----------------------------------------------");
    }

    public void verifyTCPADisclaimerAndLinksAuto(String tcpa_disclaimer) {
        log.info("Verifying TCPA Disclaimer And Links...");
        assertThat("INSURANCE COMPANIES link is absent on Page", isElementPresent(By.linkText(INSURANCE_COMPANIES_AUTO)), is(true));
//        JsUtils jsUtils = new JsUtils(driver);
        assertThat(jsUtils.getTCPADisclaimer().replaceAll(" ", ""), equalToIgnoringWhiteSpace(tcpa_disclaimer.replaceAll(" ", "")));
        log.info("Link is preset and text is matched as expected");
        log.info("----------------------------------------------");
    }

    public void verifyDisclaimerAndLinks(String disclaimer) {
        log.info("Verifying Disclaimer And Links...");
        assertThat("PRIVACY POLICY link is absent on Page", isElementPresent(By.linkText(PRIVACY_POLICY)), is(true));
        assertThat("TERM AND CONDITIONS link is absent on Page", isElementPresent(By.linkText(TERM_AND_CONDITIONS)), is(true));
//        JsUtils jsUtils = new JsUtils(driver);
        assertThat(jsUtils.getDisclaimer(), equalToIgnoringCase(disclaimer));
        log.info("Links are presets and text is matched as expected");
        log.info("-------------------------------------------------");
    }

    public void verifyPrivacyPolicyAndTermsOfUseLinks(String value) {
        log.info("Verifying Privacy Policy And Terms Of Use(or Conditions) Links...");
        assertThat("PRIVACY POLICY link is absent on Page", isElementPresent(By.linkText(PRIVACY_POLICY)), is(true));
        assertThat("TERM OF USE link is absent on Page", isElementPresent(By.linkText(TERM_OF_USE)), is(true));
        assertThat(jsUtils.getPPAndTU(), equalToIgnoringWhiteSpace(value));
        log.info("Links are presets and text is matched as expected");
        log.info("-------------------------------------------------");
    }

    public boolean isElementPresent(By locator) {
        try {
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(Config.IMPLICITLY_WAIT, TimeUnit.SECONDS);
        }
    }

    public boolean isElementPresent2(By locator) {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        boolean result = driver.findElements(locator).size() > 0;
        driver.manage().timeouts().implicitlyWait(Config.IMPLICITLY_WAIT, TimeUnit.SECONDS);
        return result;
    }

    public boolean isElementPresent3(By locator) {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        List<WebElement> list = driver.findElements(locator);
        driver.manage().timeouts().implicitlyWait(Config.IMPLICITLY_WAIT, TimeUnit.SECONDS);
        if (list.size() == 0) {
            return false;
        } else {
            return list.get(0).isDisplayed();
        }
    }

    public String getSelectedValueFromDropDown(WebElement element) {
        return (new Select(element)).getFirstSelectedOption().getText();
    }

    public List<String> getAllValuesFromDropDown(WebElement element) {
        List<String> result = new ArrayList<>();
        List<WebElement> options = new Select(element).getOptions();
        for (WebElement option : options) {
            result.add(option.getText());
        }
        result.remove(0);// remove <option value="null"> --select-- </option>
        log.info("\n----------------Displayed values in Drop Down -----------------\n" + result.toString() + "\n");
        return result;
    }

    public void open(String url) {
        driver.manage().deleteAllCookies();
        log.info("Navigate to the URL: " + url);
        driver.get(url);
        driver.get(url);
        waitForPageLoaded();
    }

    public void waitUntilDisplayed(final WebElement element) {
        logAction.verbose("waiting for displayed", element);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void checkErrorsOnPage() {
        String pageText = getPageText();
        if (!(pageText.contains(Messages.FINAL_PAGE_MESSAGE_TEXT_1) || pageText.contains(Messages.FINAL_PAGE_MESSAGE_TEXT_2))) {
            List<String> errorsOnBottomPageAsList = getErrorsOnBottomPageAsList();
            if (errorsOnBottomPageAsList.size() > 0) {
                throw new Error("Has error in one of the fields" +
                        "\n\t\t\t error message is: '" + errorsOnBottomPageAsList.toString() + "'");
            }
        }
    }

    public void waitListings() {
        long begin = new Date().getTime();
        log.info("Wait until listings are loading into frame");
        waitForIframes(driver, 1);
        driver.switchTo().frame("endpage-iframe");
        List<WebElement> elements = driver.findElements(By.xpath(".//ul[@class='bwapsListListings']/li"));
        log.info("All Listings are loaded, and count are'" + elements.size() + "'");
        log.info("Elapsed time milliseconds is '" + (new Date().getTime() - begin) + "'");
    }

    public void waitForPageLoaded() {
        long begin = new Date().getTime();
        log.info("waiting for Page is Loaded");
        final JsUtils js = new JsUtils(driver);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return js.isPageLoaded();
            }
        });
        log.info("Elapsed time milliseconds is '" + (new Date().getTime() - begin) + "'");
    }


    public void waitUntilToBeClickable(final WebElement element) {
        logAction.verbose("waiting for element to be clickable", element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
