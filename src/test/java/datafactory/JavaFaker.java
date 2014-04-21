package datafactory;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

/**
 * Created by Funker on 21.04.14.
 */
public class JavaFaker {

    @Test
    public void testName() throws Exception {
        Faker faker = new Faker();

        System.out.println(faker.name()); // Miss Samanta Schmidt
        System.out.println(faker.firstName()); // Emory
        System.out.println(faker.lastName()); // Barton

        System.out.println(faker.streetAddress(false)); // 60018 Sawayn Brooks Suite 449


        System.out.println(faker.phoneNumber());
    }
}
