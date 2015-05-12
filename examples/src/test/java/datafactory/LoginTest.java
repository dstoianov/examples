package datafactory;

// import org.databene.feed4testng.FeedTest;

import org.testng.annotations.Test;

// import javax.validation.constraints.Pattern;

/**
 * Created by Funker on 18.04.14.
 */
public class LoginTest {//extends FeedTest {

    @Test(dataProvider = "feeder")
    public void testLogin(String name, String password) {
        System.out.println("name:" + name + " password:" + password);
    }


//    @Test(dataProvider = "feeder")
//    public void testSmoke(@Pattern(regex = "[A-Z][a-z]{3,8}") String name) {
//        System.out.println("name:" + name);
//    }


}
