package ua.com.antenka.test;

import com.revimedia.testing.configuration.dto.Contact;
import com.revimedia.tests.configuration.dataproviders.AutoDataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ua.com.antenka.test.helpers.DataProviders;
import ua.com.antenka.test.helpers.User;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by User on 1/20/14.
 */
public class BuyTest {   //buy attack

    Long start = null;
    WebDriver driver;
    Long stop = null;

    @Test(dataProvider = "getUser", dataProviderClass = DataProviders.class)
    public void buyAttackTest(User user) {
        do {
            driver.get("http://antenka.com.ua/");
            List<WebElement> menu = driver.findElements(By.xpath("//div[@class='boxIndent']//li/a/span"));
            menu.get(getRnd(menu.size())).click();
        } while (!isElementExist(".//*[@id='vmMainPage']//input[@class='addtocart_button']"));

        List<WebElement> goods = driver.findElements(By.xpath(".//*[@id='vmMainPage']//input[@class='addtocart_button']"));
        goods.get(getRnd(goods.size())).click();

        driver.findElement(By.xpath(".//*[@id='enterCart']")).click();
        driver.findElement(By.xpath(".//*[@id='applyCart']")).click();

        // fill in the form
        driver.findElement(By.id("last_nameClient")).sendKeys(user.getName());
        driver.findElement(By.id("nameClient")).sendKeys(user.getFamilyName());
        driver.findElement(By.id("patronymic")).sendKeys("wwrwr");

        WebElement phone = driver.findElement(By.id("telClient"));
        phone.clear();

        String phone_ = user.getPhone().substring(3);
        phone.sendKeys(phone_);

        Select mailService = new Select(driver.findElement(By.id("courier")));
        List<WebElement> options = mailService.getOptions();
        mailService.selectByIndex(getRnd(options.size()));

        Select paymentMethods = new Select(driver.findElement(By.id("money")));
        paymentMethods.selectByIndex(getRnd(2));

        Select city = new Select(driver.findElement(By.id("sity")));
        List<WebElement> cityCount = city.getOptions();
        city.selectByIndex(getRnd(cityCount.size()));

        //Submit
        driver.findElement(By.id("btnSendForm")).click();
        boolean isOrdered = driver.findElement(By.xpath("//div[@class='module-featured']/h3")).getText().contains("vash zakaz prinyt");
        Assert.assertTrue(isOrdered, "Item is not ordered, failed");
        driver.manage().deleteAllCookies();
    }

    private boolean isElementExist(String xpath) {
        try {
            driver.findElement(By.xpath(xpath));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private WebElement findElement(final By by) {
        return (new WebDriverWait(driver, 10)).until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver d) {
                return d.findElement(by);
            }
        });
    }


    private int getRnd(int i) {
        Random rand = new Random();
        return rand.nextInt(i);
    }

    @BeforeClass
    public void setUp() {
        start = System.currentTimeMillis();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        stop = System.currentTimeMillis();
        print("That took %s seconds", (double) ((stop - start) / 1000));
        //System.out.println("That took " + (double) ((stop - start) / 1000) + " seconds");
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    @Test(dataProvider = "contactData", invocationCount = 1, dataProviderClass = AutoDataProvider.class)
    public void testLuxoftRegistration(Contact contact) throws Exception {

        driver.get("http://www.luxoft.com/lts/logeek/ukraine/registration/");

        WebElement name = driver.findElement(By.id("form_EV_NAME"));
        WebElement lastName = driver.findElement(By.id("form_EV_SURNAME"));
        WebElement email = driver.findElement(By.id("form_EV_EMAIL"));
        WebElement specialization = driver.findElement(By.id("form_EV_SPECIALIZATION"));
        WebElement submit = driver.findElement(By.name("web_form_submit"));

        name.sendKeys(contact.getFirstName());
        lastName.sendKeys(contact.getLastName());
        email.sendKeys(contact.getEmailAddress());
//        email.sendKeys(contact.getEmailAddress());

        submit.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return driver.getPageSource().contains("Thank you for registering");
            }
        });

    }
}
