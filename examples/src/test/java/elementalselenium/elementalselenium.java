package elementalselenium;

import google.code.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by dstoianov on 2015-06-30.
 */
public class elementalselenium extends BaseTest {


    @Test
    public void testName() throws Exception {

        driver.get("http://elementalselenium.com/tips");

        List<WebElement> blocks = driver.findElements(By.cssSelector(".summary .large-12"));
        List<ESBean> esBeans = new ArrayList<>();

        for (WebElement e : blocks) {
            String number = e.findElement(By.cssSelector(".number")).getText();
            String title = e.findElement(By.cssSelector(".title")).getText();
            String href = e.findElement(By.cssSelector(".title")).findElement(By.tagName("a")).getAttribute("href");
            String tags = e.findElement(By.cssSelector(".tags")).getText();
            List<WebElement> metaList = e.findElements(By.cssSelector(".small-meta"));
            StringBuilder sb = new StringBuilder();
            for (WebElement m : metaList) {
                sb.append(m.getText()).append(" ");
            }
            esBeans.add(new ESBean(Integer.parseInt(number), title, href, tags, sb.toString().trim()));
        }

        Collections.sort(esBeans);

        List<ESBean> result = new ArrayList<>();
        for (ESBean b : esBeans) {
            driver.get(b.getHref());
            String html = driver.findElement(By.cssSelector(".content .large-9")).getAttribute("innerHTML").trim();
            b.setBody(html);
            result.add(b);
        }
        createFile(result);
    }

    public void createFile(List<ESBean> beanList) {

        StringBuilder sb = new StringBuilder();

        sb.append("<html><body>\n");
        sb.append("<h1>").append("Elemental <em>Se</em>lenium").append("</h1>\n");
        sb.append("<hr color=\"purple\">\n");

        sb.append("<h2>").append("Tips Contents").append("</h2>\n");

        for (ESBean b : beanList) {
            sb.append(b.getNumber()).append(". ").append(b.getTitle()).append("<br>\n");
        }

        sb.append("<hr color=\"purple\">\n");

        for (ESBean b : beanList) {
            sb.append("<h2>").append(b.getNumber()).append(". ").append(b.getTitle()).append("</h2>\n");
            sb.append("<h3>").append(b.getMeta()).append("</h3>\n");
            sb.append("<h4>").append(b.getTags()).append("</h4>\n");
            sb.append("<HR WIDTH=\"40%\" ALIGN=\"LEFT\">\n");
            sb.append(b.getBody()).append("<br>\n");
            sb.append("<hr>\n");
        }

        sb.append("</body></html>\n");

        try {
            FileUtils.writeStringToFile(new File("elemental_selenium.htm"), sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
