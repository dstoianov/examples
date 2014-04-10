package parametrized.suitdto;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * User: stoianod
 * Date: 4/8/14
 */
public class TestWithTO {

    @Test(dataProvider = "create")
    public void testName1(String url, UserTO user) throws Exception {
        System.out.println("url: " + url + ", user: " + user);
    }

    @Test(dataProvider = "create")
    public void testName2(String url, UserTO user) throws Exception {
        System.out.println("url: " + url + ", user: " + user);
    }


    @DataProvider(name = "create")
    public static Object[][] createData() {
        return new Object[][]{
                {"url1", getRandomUser()},
                {"url2", getRandomUser()},
                {"url3", getRandomUser()}
        };
    }


    static UserTO getRandomUser() {
       return new UserTO(RandomStringUtils.randomAlphabetic(8), RandomStringUtils.randomAlphabetic(8), new Random().nextInt(100));
    }


}
