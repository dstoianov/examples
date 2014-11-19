package jsonTests.gsonResponse;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * Created by Funker on 19.11.2014, 1:36.
 */
public class DisclaimerText {

    public WebDriver driver;
    public static JavascriptExecutor js;
    private String disclaimerText = "return Bq.Config.disclaimerText";

    public DisclaimerText(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
    }

    public Auto getAuto() {
        return new Auto();
    }

    public class Auto implements Getters {

        @Override
        public String getTcpa() {
            return (String) js.executeScript(disclaimerText + ".auto.tcpa");
        }

        @Override
        public String getBestq() {
            return (String) js.executeScript(disclaimerText + ".auto.bestq");
        }
    }

    public class AutoFinance {
    }

    public class Health {
    }

    public class Home {
    }

    public class HomeSecurity {
    }

    public class Life {
    }

    public class Automotive {
    }

    public class Pet {
    }

    public class PetFullQuote {
    }

}
