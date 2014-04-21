package tipsandtricks.DRAFT_ScreenshotOnFail;

/**
 * Created by Funker on 21.04.14.
 */

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.lang.*;
import java.net.*;

//http://bharath-marrivada.blogspot.com/2011/04/selenium-screenshot-testng-capture.html

public class TestNGCustom extends TestListenerAdapter {
    private int Count = 0;

    //Take screen shot only for failed test case
    @Override
    public void onTestFailure(ITestResult tr) {
        ScreenShot();
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        //ScreenShot();
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        //ScreenShot();
    }

    private void ScreenShot() {
        try {

            String NewFileNamePath;

            /*
            //Code to get screen resolution
            //Get the default toolkit
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            //Get the current screen size
            Dimension scrnsize = toolkit.getScreenSize();
            //Print the screen size
            System.out.println ("Screen size : " + scrnsize);
            */

            //Get the dir path
            File directory = new File(".");
            //System.out.println(directory.getCanonicalPath());

            //get current date time with Date() to create unique file name
            DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
            //get current date time with Date()
            Date date = new Date();
            //System.out.println(dateFormat.format(date));

            //To identify the system
            InetAddress ownIP = InetAddress.getLocalHost();
            //System.out.println("IP of my system is := "+ownIP.getHostAddress());

            NewFileNamePath = directory.getCanonicalPath() + "\\ScreenShots\\" + dateFormat.format(date) + "_" + ownIP.getHostAddress() + ".png";
            System.out.println(NewFileNamePath);

            //Capture the screen shot of the area of the screen defined by the rectangle
            Robot robot = new Robot();
            BufferedImage bi = robot.createScreenCapture(new Rectangle(1280, 1024));
            ImageIO.write(bi, "png", new File(NewFileNamePath));
            Count++;//Assign each screen shot a number
            NewFileNamePath = "<a href=\"http://draft.blogger.com/+NewFileNamePath+\">ScreenShot" + Count + "</a>";
            //Place the reference in TestNG web report
            Reporter.log(NewFileNamePath);


        } catch (AWTException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
