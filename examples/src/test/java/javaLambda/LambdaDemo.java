package javaLambda;

import javaLambda.inter.*;
import org.testng.annotations.Test;

/**
 * Created by Funker on 21.02.2016.
 */
public class LambdaDemo {

    @Test
    public void testName() throws Exception {
        MyNumber myNumber;

        myNumber = () -> 123.45;
        System.out.println("Fix value: " + myNumber.getValue());

        myNumber = () -> Math.random() * 100;
        System.out.println("Random value : " + myNumber.getValue());
        System.out.println("Random value : " + myNumber.getValue());
    }

    @Test
    public void testIsOdd() throws Exception {
//        Numeric isEven = (n) -> (n % 2) == 0;
//        Numeric isEven = (int n) -> (n % 2) == 0;
        Numeric isEven = n -> (n % 2) == 0;

        int m = 10;
        if (isEven.test(m)) {
            System.out.println("Digits is even " + m);
        }
        m = 9;
        if (!isEven.test(m)) {
            System.out.println("Digits is odd " + m);
        }

    }

    @Test
    public void testName3() throws Exception {
        Numeric2 isEven2 = (n, d) -> (n % d) == 0;

    }

    @Test
    public void testBlockLambda() throws Exception {
        NumericFunc factorial = (n) -> {
            int result = 1;

            for (int i = 1; i <= n; i++) {
                result = result * i;
            }
            return result;
        };

        System.out.println("Factorial 5 " + factorial.func(5));
        System.out.println("Factorial 3 " + factorial.func(3));
    }


    @Test
    public void testGeneralInterface() throws Exception {
        SomeFunc<String> reverse = (str) -> {
            String result = "";
            for (int i = str.length() - 1; i >= 0; i--) {
                result += str.charAt(i);
            }
            return result;
        };
        String s = "I love you";
        System.out.printf("Revers of string '%s' is '%s'", s, reverse.func(s));

        SomeFunc<Integer> factorial = (n) -> {
            int result = 1;
            for (int i = 1; i <= n; i++) {
                result = result * i;
            }
            return result;
        };


    }
}
