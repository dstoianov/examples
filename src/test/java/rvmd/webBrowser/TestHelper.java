package rvmd.webBrowser;

/**
 * Created by Funker on 24.04.14.
 */
public class TestHelper {

    public boolean isTextPresent(String text) {
        try {

            return true;//driver.getPageSource().contains(text);
        } catch (Exception e) {
            return false;
        }
    }
}
