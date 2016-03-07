package javaLambda;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Funker on 22.02.2016.
 */
public class StreamDemo {

    List<Integer> list = Arrays.asList(1, 12, 2, 4, 45, 5, 6, 7, 8, 33, 29, 9);
    List<String> listStr = Arrays.asList("dfsd@dddf12", "dfsd@daddf3", "dfs@dddf7", "dfsd@daddf2");

    @Test
    public void testName() throws Exception {

        list.stream().sorted().forEach(n -> System.out.printf("Value is '%s'\n", n));

        listStr.stream()
                .map(s -> s.split("@"))
                .flatMap(Arrays::stream)
                .filter(s -> s.contains("a"))
                .forEach(System.out::println);


//                .collect(Collectors.toList());
    }


    @Test
    public void testNames() throws Exception {
        String s = "http://frontend-qa-3.wkda-test.de/booking/5ea2ed1f445ba66369ae77fc8271bab1/_Mozilla/5.0";


        boolean hasLead = s.matches("[a-z0-9]{10}");

    }
}
