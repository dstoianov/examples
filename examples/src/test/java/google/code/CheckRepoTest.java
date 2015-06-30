package google.code;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Funker on 29.06.2015.
 */
public class CheckRepoTest extends BaseTest {

    String url = "https://code.google.com/u/barancev/";

    @Test
    public void testName() throws Exception {
        driver.get(url);
        Thread.sleep(1000);

        List<WebElement> list = driver.findElements(By.cssSelector(".id a"));
        List<DataBean> beans = new ArrayList<>();
        List<DataBean> beansFinal = new ArrayList<>();
        for (WebElement e : list) {
            String href = e.getAttribute("href");
            String text = e.getText();
            beans.add(new DataBean(text, href));
        }

        for (DataBean b : beans) {
            driver.get(b.getUrl() + "source/list");
//            List<WebElement> elements = driver.findElements(By.cssSelector(".pagination"));
            List<WebElement> elements = driver.findElements(By.cssSelector("#resultstable tr [nowrap='nowrap']"));
            if (elements.size() == 0) {
                b.setDate("");
                beansFinal.add(b);
            } else {
                String target = elements.get(0).getText().split(" - ")[0].trim();
                DateFormat df = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
                Date result = df.parse(target);
                String reportDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(result);
                b.setDate(reportDate);
                beansFinal.add(b);
            }
        }

        Collections.sort(beansFinal);

        for (DataBean d : beansFinal) {
            System.out.println(String.format("%s - %s - %s", d.getDate(), d.getName(), d.getUrl()));
        }

    }


}
