package jsonTests.gsonResponse;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * Created by Funker on 19.11.2014, 1:36.
 */
public class DisclaimerText implements Getters {

    private JavascriptExecutor js;
    private String disclaimerText = "return Bq.Config.disclaimerText";

    private Auto auto;
    private Home home;
    private Health health;
    private AutoFinance autoFinance;
    private HomeSecurity homeSecurity;
    private Life life;
    private Automotive automotive;
    private Pet pet;
    private PetFullQuote petFullQuote;

    public DisclaimerText(WebDriver driver) {
        this.js = (JavascriptExecutor) driver;
    }

    public Auto getAutoExpected() {
        if (auto == null) {
            auto = new Auto();
        }
        return auto;
    }

    public Home getHomeExpected() {
        if (home == null) {
            home = new Home();
        }
        return home;
    }

    public Health getHealthExpected() {
        if (health == null) {
            health = new Health();
        }
        return health;
    }

    public AutoFinance getAutoFinanceExpected() {
        if (autoFinance == null) {
            autoFinance = new AutoFinance();
        }
        return autoFinance;
    }

    public HomeSecurity getHomeSecurityExpected() {
        if (homeSecurity == null) {
            homeSecurity = new HomeSecurity();
        }
        return homeSecurity;
    }

    public Life getLifeExpected() {
        if (life == null) {
            life = new Life();
        }
        return life;
    }

    public Automotive getAutomotiveExpected() {
        if (automotive == null) {
            automotive = new Automotive();
        }
        return automotive;
    }

    public Pet getPetExpected() {
        if (pet == null) {
            pet = new Pet();
        }
        return pet;
    }

    public PetFullQuote getPetFullQuoteExpected() {
        if (petFullQuote == null) {
            petFullQuote = new PetFullQuote();
        }
        return petFullQuote;
    }

    private String clean(String unsafe) {
        Whitelist whitelist = Whitelist.none();
        whitelist.addTags(new String[]{"a", "div", "span"});
        whitelist.addAttributes("a", "href", "target");
        whitelist.addAttributes("div", "class");
        whitelist.addAttributes("span", "class");
        String safe = Jsoup.clean(unsafe, whitelist);
        return safe.replaceAll("\n", "").replaceAll("> <", "><");
    }

    private String executeScript(String script) {
        String s = (String) js.executeScript(script);
        return s.replaceAll("\'", "\"");
    }

    public boolean isPageLoaded() {
        return js.executeScript("return document.readyState").equals("complete");
    }

    @Override
    public String getTcpa() {
        return clean(executeScript("return $('.bq-tcpa-container').html();"));
    }

    @Override
    public String getBestq() {
        return clean(executeScript("return $('.bq-disclaimer-container').html();"));
    }

    class Auto implements Getters {
        @Override
        public String getTcpa() {
            return executeScript(disclaimerText + ".auto.tcpa");
        }

        @Override
        public String getBestq() {
            return executeScript(disclaimerText + ".auto.bestq");
        }
    }

    class AutoFinance implements Getters {
        @Override
        public String getTcpa() {
            return executeScript(disclaimerText + ".autofinance.tcpa");
        }

        @Override
        public String getBestq() {
            return executeScript(disclaimerText + ".autofinance.bestq");
        }
    }

    class Health implements Getters {
        @Override
        public String getTcpa() {
            return executeScript(disclaimerText + ".health.tcpa");
        }

        @Override
        public String getBestq() {
            return executeScript(disclaimerText + ".health.bestq");
        }
    }

    class Home implements Getters {
        @Override
        public String getTcpa() {
            return executeScript(disclaimerText + ".home.tcpa");
        }

        @Override
        public String getBestq() {
            return executeScript(disclaimerText + ".home.tcpa");
        }
    }

    class HomeSecurity implements Getters {
        @Override
        public String getTcpa() {
            return executeScript(disclaimerText + ".homesecurity.tcpa");
        }

        @Override
        public String getBestq() {
            return executeScript(disclaimerText + ".homesecurity.bestq");
        }
    }

    class Life implements Getters {
        @Override
        public String getTcpa() {
            return executeScript(disclaimerText + ".life.tcpa");
        }

        @Override
        public String getBestq() {
            return executeScript(disclaimerText + ".life.bestq");
        }
    }

    class Automotive implements Getters {
        @Override
        public String getTcpa() {
            return executeScript(disclaimerText + ".automotive.tcpa");
        }

        @Override
        public String getBestq() {
            return executeScript(disclaimerText + ".automotive.bestq");
        }
    }

    class Pet implements Getters {
        @Override
        public String getTcpa() {
            return executeScript(disclaimerText + ".pet.tcpa");
        }

        @Override
        public String getBestq() {
            return executeScript(disclaimerText + ".pet.bestq");
        }
    }

    class PetFullQuote implements Getters {
        @Override
        public String getTcpa() {
            return executeScript(disclaimerText + ".petfullquote.tcpa");
        }

        @Override
        public String getBestq() {
            return executeScript(disclaimerText + ".petfullquote.bestq");
        }
    }

}
