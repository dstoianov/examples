package unit;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Funker on 07.01.2015.
 */
public class BulgarianGirl {

    public static void main(String[] args) throws IOException {

        InputStream src = new URL("http://www.bottegaverde.at/").openStream();

        String stream = getStringFromInputStream(src);

        List<String> links = getLinks(stream);

        writeToFile(links);
    }

    private static List<String> getLinks(String HTMLPage) {
        Pattern linkPattern = Pattern.compile("(<a[^>]+>.+?</a>)", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher pageMatcher = linkPattern.matcher(HTMLPage);
        List<String> links = new ArrayList<String>();
        while (pageMatcher.find()) {
            links.add(pageMatcher.group());
        }
        return links;
    }

    // convert InputStream to String
    private static String getStringFromInputStream(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }


    private static void writeToFile(List<String> links) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter("lalala.txt"));
            for (String link : links) {
                out.write(link + "\n");
            }
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());

        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
