package parametrized.runsuite;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * User: stoianod
 * Date: 4/8/14
 */
public class Test2 {


    @Parameters({ "url" })
    @Test(dataProvider = "create")
    public void testName1(String url, User user) throws Exception {
        System.out.println("url: " + url + ", user: " + user);
    }


    @DataProvider(name = "create")
    public static Object[][] createData() {
        return new Object[][] {
                {getRandomUser()},
                {getRandomUser()},
                {getRandomUser()}


        };
    }


    static User getRandomUser() {
       return new User(RandomStringUtils.randomAlphabetic(8), RandomStringUtils.randomAlphabetic(8), new Random().nextInt(100));
    }

    static class User {
        String firstName;
        String lastName;
        int age;

        User(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
