package unit;

import java.util.Random;

/**
 * Created by dstoianov on 2015-01-20.
 */
public class NewStoreTest {

    public static void main(final String[] argv) {
        int[] A = new int[]{3, 5, 6, 3, 3, 5};
//        A[0] = 3;
//        A[1] = 5;
//        A[2] = 6;
//        A[3] = 3;
//        A[4] = 3;
//        A[5] = 5;

        System.out.println(solutionTask1("a0Ba"));
        System.out.println(solutionTask1("a0ba"));
        System.out.println(solutionTask1("a0BadweDSdc"));

        System.out.println("task3");

        System.out.println(solutionTask3(A));
        System.out.println(solutionTask3(createArray(10)));
        System.out.println(solutionTask3(createArray(500000)));
    }

    public static int solutionTask1(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))) {
//                return i; // incorrect, i thought that i need to return the index of element
                return s.substring(i).length();
            }
        }
        return -1;
    }


    public static long solutionTask3(int[] A) {
        long pairs = 0;
        for (int i = 0; i < A.length; i++) {
            int elem = A[i];
            for (int j = i + 1; j < A.length; j++) {
                int next = A[j];
                if (elem == next) {
                    pairs++;
                }
            }
        }
        if (pairs < 1000000000) {
            return pairs;
        } else {
//            return pairs; // accidental error
            return 1000000000; // correct answer
        }
    }


    private static int[] createArray(int length) {
        int[] ints = new int[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            ints[i] = random.nextInt(9);
        }
        return ints;
    }

}
