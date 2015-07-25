package unit;

import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Funker on 24.07.2015.
 */
public class EpamTest {


/*    Implement method do check if a string is a palindrom

    Kayak - true
    foo - false
    A - true
   "" - false
      }*/


    @Test
    public void testPositive() throws Exception {
        assertThat(isPalindrom("Kayaks"), is(false));
        assertThat(isPalindrom("Kayak"), is(true));
        assertThat(isPalindrom("foo"), is(false));
        assertThat(isPalindrom("A"), is(true));
        assertThat(isPalindrom(""), is(false));
    }

    private boolean isPalindrom(String s) {
        boolean result = false;
        if (s.length() == 1) {
            return true;
        }


        for (int i = 0; i < s.length(); i++) {
            char begin = s.toLowerCase().charAt(i);
            char end = s.toLowerCase().charAt(s.length() - i - 1);
            result = (begin == end);
            if (!(begin == end)) {
                return false;
            }
        }
        return result;
    }

    boolean isPalindrome(String str) {
        int n = str.length();
        for (int i = 0; i < n / 2; i++) {
            if (str.toLowerCase().charAt(i) != str.toLowerCase().charAt(n - i - 1)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean isPalindrome2(String str) {
        return str.equals(new StringBuffer().append(str).reverse().toString());
    }

}
