package unit;

/**
 * Created by Funker on 04.08.2015.
 */
//conductor.com Developer in Test //http://jobs.dou.ua/companies/conductor/vacancies/17849/

public class ConductorTest {

    // online coding http://collabedit.com/q29t5

    public static void main(final String[] argv) {

        System.out.println("5 is " + fibonacci(5));

        int[][] a = {{1, 2}, {1, 2}, {1, 2}, {1, 2}, {1, 2}};
        int[][] b = {{1, 2}, {1, 2}, {1, 2}, {1, 2}, {1, 2}};

        System.out.println("is equal a and b arrays -> " + equal(a, b));

        String input = "This is a string";
        System.out.println("input   = " + input);
        System.out.println("output1 = " + revertWords(input));

        System.out.println("Factorial 5 = " + factorial(5));
    }

    public static int fibonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    public static boolean equal(int[][] a, int[][] b) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++)
                // here => 1 == 2 ?
                if (a[i][j] == b[i][j]) {
                    continue;
                } else {
                    return false;
                }
        }
        // result will be like here
        return true;
    }


    public static String revertWords(String input) {
        String[] split = input.split(" ");
        String result = "";
        for (int i = split.length - 1; i >= 0; i--) {
//            result = result + split[i] + " ";
            result += split[i] + " ";
        }
        return result.trim();
    }


    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
//        return (n == 0) ? 1 : n * factorial(n - 1);
    }

    public static int factorial2(int n) {
        int ret = 1; // this  will be the result
        for (int i = 1; i <= n; i++) {
            ret *= i;
        }
        return ret;
    }


}
