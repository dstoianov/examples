package modal.windows;

import autoitx4java.AutoItHelper;
import autoitx4java.AutoItX;
import com.jacob.com.LibraryLoader;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: stoianod
 * Date: 11/11/13
 * Time: 1:14 PM
 * To change this template use File | Settings | File Templates.
 */


public class ModalUploadTests extends Base {

    @Test
    public void UploadFileWithPOSTTest() throws InterruptedException {

        driver.get("http://www.httpwatch.com/httpgallery/methods/");
        driver.findElement(By.name("F1")).click();

        File file = new File("lib", "jacob-1.17-x64.dll"); //path to the jacob dll
        System.setProperty(LibraryLoader.JACOB_DLL_PATH, file.getAbsolutePath());

        AutoItX x = new AutoItX();
        String uploadClassWindow = "[REGEXPTITLE:(File Upload|Choose File to Upload|Open);CLASS:#32770]";
        x.winWaitActive(uploadClassWindow, "", 2);
        if (x.winExists(uploadClassWindow)) {
            //x.sleep(200);
            x.ControlSetText(uploadClassWindow, "", "Edit1", "C:\\Dev\\Lib\\LICENSE.TXT");
            x.sleep(200);
            //x.controlClick(uploadClassWindow, "", "[CLASS:Button; TEXT:&Open; INSTANCE:1]");
           x.controlClick(uploadClassWindow, "", "Button1");
            x.sleep(200);
           // x.send("{ENTER}", false);
        }

        driver.findElement(By.xpath("//input[@name='B4']")).click();
        Thread.sleep(8000);
    }
    @Test
    public void HTTPAuthenticationTest() throws InterruptedException {
           driver.get("http://www.httpwatch.com/httpgallery/authentication/");
           driver.findElement(By.xpath("//input[@id='displayImage']")).click();


        File file = new File("lib", "jacob-1.17-x64.dll"); //path to the jacob dll
        System.setProperty(LibraryLoader.JACOB_DLL_PATH, file.getAbsolutePath());

        AutoItX x = new AutoItX();

        String chromeWindow = "[CLASS:Chrome_WidgetWin_1]";
       //x.winActivate(chromeWindow);
        x.winWaitActive(chromeWindow, "", 1);
        if (x.winExists(chromeWindow)) {
            x.send("httpwatch");
            x.send("{TAB}", false);
            x.send("dhjhfj");
            x.send("{ENTER}", false);

        }
        Thread.sleep(5000);
    }

    @Test
    public void  UploadNewTest() throws InterruptedException {
        driver.get("http://www.httpwatch.com/httpgallery/methods/");
        driver.findElement(By.name("F1")).click();


        String modalWindow = "[REGEXPTITLE:(File Upload|Choose File to Upload|Open);CLASS:#32770]";

        AutoItHelper ait = new AutoItHelper(modalWindow);
        ait.winWaitAndActivate(modalWindow, "", 3);

        ait.setText("Edit1", "C:\\Dev\\Lib\\test_file.sss");
        ait.send("{ENTER}", false);
        //ait.click("[TEXT:&Open]");
       // up.click("Button1");


        driver.findElement(By.xpath("//input[@name='B4']")).click();
        // check that file was uploaded
        int i = driver.findElement(By.xpath("//*[@id='table31']")).hashCode();

        Assert.assertTrue(driver.findElement(By.xpath("//*[@id='table31']")).isEnabled(), "Table is not Enabled");
        Thread.sleep(8000);
    }



}
