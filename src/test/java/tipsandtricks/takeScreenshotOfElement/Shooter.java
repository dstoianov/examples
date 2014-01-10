package tipsandtricks.takeScreenshotOfElement;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Shooter {

    private WebDriver driver;

    @Test
    public void getScreenOfElement() throws IOException {
        driver.get("https://www.google.co.uk/");
        WebElement el = driver.findElement(By.xpath(".//*[@id='gb_70']"));

        shootWebElement(el);
    }


    public void shootWebElement(WebElement element) throws IOException {

        File screen = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE);
        Point p = element.getLocation();

        int width = element.getSize().getWidth();
        int height = element.getSize().getHeight();

        BufferedImage img = ImageIO.read(screen);
        BufferedImage dest = img.getSubimage(p.getX(), p.getY(), width, height);

        ImageIO.write(dest, "png", screen);
        String name = element.getText();
        File f = new File("src/test/java/tipsandtricks/takeScreenshotOfElement" + name + "+.png");
        FileUtils.copyFile(screen, f);
    }


    @BeforeTest
    public void setUp() {
        driver = new FirefoxDriver();
        //driver = new ChromeDriver();
        //driver = new InternetExplorerDriver();
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
