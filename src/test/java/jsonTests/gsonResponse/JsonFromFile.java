package jsonTests.gsonResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.revimedia.testing.configuration.utils.JsUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by DStoianov on 04/07/2014, 1:17 PM
 */
public class JsonFromFile {

    String file = ".\\src\\test\\java\\jsonTests\\gsonResponse\\Bq.Config.copy.json";
    String json = ".\\src\\test\\java\\jsonTests\\gsonResponse\\Bq.Config.json";
    //String file = ".\\src\\test\\java\\jsonTests\\gsonResponse\\Bq.Config.copy.json";

    // Bq.Config.disclaimerText.auto.tcpa

    @Test
    public void testName1() throws Exception {
        Gson gson = new Gson();
        BufferedReader br = new BufferedReader(new FileReader(file));
        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();
        Map<String, Map<String, Object>> jsonMap = gson.fromJson(br, type);

        Map<String, Object> disclaimerText = jsonMap.get("disclaimerText");
        Map<String, Object> homesecurity = (Map<String, Object>) disclaimerText.get("homesecurity");

        String tcpa = (String) homesecurity.get("tcpa");
        String bestq = (String) homesecurity.get("bestq");

    }

    @Test
    public void testName2() throws Exception {
        Gson gson = new Gson();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            Map<String, Map<String, Object>> hashMap = gson.fromJson(br, HashMap.class);
            Map<String, Object> disclaimerText = hashMap.get("disclaimerText");
            Map<String, Object> homesecurity = (Map<String, Object>) disclaimerText.get("homesecurity");

            String tcpa = (String) homesecurity.get("tcpa");
            String bestq = (String) homesecurity.get("bestq");

            System.out.println(hashMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testName3() throws Exception {
        Gson gson = new Gson();
        try {
            BufferedReader br = new BufferedReader(new FileReader(json));
            Map<String, Object> hashMap = gson.fromJson(br, HashMap.class);
            Map<String, String> homesecurity = (Map<String, String>) hashMap.get("homesecurity");
            String tcpa = (String) homesecurity.get("tcpa");
            String bestq = (String) homesecurity.get("bestq");

            System.out.println(hashMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testName4FromLive() throws Exception {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
        System.setProperty("phantomjs.binary.path", "./src/test/resources/drivers/phantomjs.exe");
        WebDriver driver = new PhantomJSDriver();
//        WebDriver driver = new ChromeDriver();
//        driver.get("http://development.stagingrevi.com/auto/mfs/");
        driver.get("http://development.stagingrevi.com/auto/a/#page/5");

        waitForAjaxComplete(driver);

        DisclaimerText disclaimerText = new DisclaimerText(driver);
        DisclaimerText.Auto autoExpected = disclaimerText.getAutoExpected();
        DisclaimerText.HomeSecurity homeSecurityExpected = disclaimerText.getHomeSecurityExpected();


        assertThat(disclaimerText.getTcpa(), is(autoExpected.getTcpa()));
        assertThat(disclaimerText.getBestq(), is(autoExpected.getBestq()));

        driver.get("http://development.stagingrevi.com/homesecurity/a/#page/4");
        waitForAjaxComplete(driver);

        assertThat(disclaimerText.getTcpa(), is(homeSecurityExpected.getTcpa()));
        assertThat(disclaimerText.getBestq(), is(homeSecurityExpected.getBestq()));

        driver.quit();
    }

    public void waitForPageLoaded(WebDriver driver) {
        long begin = new Date().getTime();
        final JsUtils js = new JsUtils(driver);
        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return js.isPageLoaded();
            }
        });
        System.out.println("Elapsed time milliseconds is '" + (new Date().getTime() - begin) + "'");
    }


    public void waitForAjaxComplete(WebDriver driver) {
        long begin = new Date().getTime();
        final JsUtils js = new JsUtils(driver);
        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return js.isAjaxComplete();
            }
        });
        System.out.println("Elapsed time milliseconds is '" + (new Date().getTime() - begin) + "'");
    }
}
