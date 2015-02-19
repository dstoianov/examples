package test.tasks;

public class Solution {

    public static void main(String[] args) throws Exception {

        String hello = reverse("Hello");
        System.out.println(hello);
        reverseString("Hello World");

        printX(9);
        printCross(9);
    }

    private static void printX(int num) {
        int startCur = 0;
        int endCur = num - 1;
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                if (startCur == endCur && j == i) {
                    System.out.print("X");
                } else if (startCur == j || endCur == j) {
                    System.out.print("X");
                } else {
                    System.out.print(" ");
                }
            }
            startCur++;
            endCur--;
            System.out.println();
        }
    }

    public static void reverseString(String str) {
        System.out.println(String.format(
                "Original string is '%s', reversed string is '%s'", str, reverseByReflex(str)));
    }

    public static String reverseByReflex(String str) {
        if ((null == str) || (str.length() <= 1)) {
            return str;
        }
        return reverseByReflex(str.substring(1)) + str.charAt(0);
    }

    public static String reverse(String s) {
        String reverse = "";
        int length = s.length();

        for (int i = length - 1; i >= 0; i--) {
            reverse = reverse + s.charAt(i);
        }
        return reverse;
    }


    public static void printCross(int size) {
        for (int row = 0; row < size; row++) {

            for (int col = 0; col < size; col++) {

                if (row == col || row + col == size - 1) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
