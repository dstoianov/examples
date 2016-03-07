package javaLambda;

import java.util.function.Function;

/**
 * Created by Funker on 22.02.2016.
 */
public class UseFunctionInterfaceDemo {

    public static void main(String[] args) {
        Function<Integer, Integer> factorial = (n) -> {
            int result = 1;
            for (int i = 1; i <= n; i++) {
                result = i * result;
            }
            return result;
        };

        System.out.println("Factorial digit 3 equal " + factorial.apply(3));
        System.out.println("Factorial digit 5 equal " + factorial.apply(5));

    }


}
