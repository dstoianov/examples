package grid.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

/**
 * Created with IntelliJ IDEA.
 * User: stoianod
 * Date: 11/6/13
 * Time: 12:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class Base {

    public WebDriver driver;

    @BeforeMethod
    public void setUp() throws MalformedURLException {

        DesiredCapabilities capability = DesiredCapabilities.chrome();
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);

        //driver = (driver == null) ? new FirefoxDriver() : driver;
        //driver = (driver == null) ? new ChromeDriver() : driver;
        //driver = new ChromeDriver();
        //driver = new InternetExplorerDriver();
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        getBrowserName();
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

//    @Parameters("custom_param")
//    private void customParameter(@Optional String custom_param){
//        System.out.println("My test param is: " + custom_param);
//
//    }


    public void doSomething() {
        driver.get("http://rozetka.com.ua/");

        //customParameter();

//        WebElement signOut1 = driver.findElement(By.xpath("//a[@name='signout']"));
//        if  (signOut1.isDisplayed()){
//            signOut1.click();
//        }

        //WebElement el = driver.findElement(By.name("signin")); //.click();
        if (!isElementDisplayed(By.name("signin"))) {
            findElement(By.xpath("//a[@name='signout']")).click();
        } else driver.findElement(By.name("signin")).click();

        WebElement login = driver.findElement(By.xpath("(//*[@class='auth-f']//input)[1]"));
        login.clear();
        login.sendKeys("funker@land.ru");

        WebElement pass = driver.findElement(By.xpath("(//*[@class='auth-f']//input)[2]"));
        pass.clear();
        pass.sendKeys("222222");

        driver.findElement(By.xpath("//*[@class='auth-f']//button")).click();


        WebElement signOut = findElement(By.xpath("//a[@name='signout']"));

        assertThat(signOut.isDisplayed(), is(true));
        signOut.click();
        assertThat(findElement(By.name("signin")).isDisplayed(), is(true));
    }

    private WebElement findElement(final By by) {
        return (new WebDriverWait(driver, 10)).until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver d) {
                return d.findElement(by);
            }
        });
    }

    public boolean isElementDisplayed(By by) {
        try {
            driver.findElement(by).isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        } catch (StaleElementReferenceException e) {
            return false;
        }
    }

    public String getBrowserName() {
        Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = caps.getBrowserName();
        String browserVersion = caps.getVersion();
        System.out.println("Browser Name is: " + browserName + " and version is: " + browserVersion);
        return browserName;
    }
}
