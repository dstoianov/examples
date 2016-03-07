package checkIfSpace;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

/**
 * Created by Funker on 07.03.2016.
 */
public class CheckSpaceTest {


    @Test
    public void testName() throws Exception {

        String text = "$ 22,769 1";
        String result = "";

        String s = correctValue(text);

        System.out.println(s);
    }


    private static String correctValue(String value) {
        String text = correctSpaces(value);
        return (StringUtils.countMatches(text, " ") > 1) ? StringUtils.replaceOnce(text, " ", ".") : text;
    }

    private static String correctSpaces(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (Character.isWhitespace(text.charAt(i)) || Character.isSpaceChar(text.charAt(i))) {
                result.append(" ");
            } else {
                result.append(text.charAt(i));
            }
        }
        return result.toString();
    }

}
