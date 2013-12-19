package org.jsoup.test;

/**
 * Created by User on 12/18/13.
 */
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

/**
 * Example program to list links from a URL.
 */
public class ListLinks {
    public static void main(String[] args) throws IOException {
       // Validate.isTrue(args.length == 1, "usage: supply url to fetch");
        //String url = "http://google.com.ua/";
        //String url = "http://forum.od.ua/forumdisplay.php?f=172";
        String url = "http://bit.ly/JD1ADO";


        print("Fetching %s...", url);

        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");
        Elements media = doc.select("[src]");
        Elements imports = doc.select("link[href]");
        //Element body = doc.body();


        Elements myCell = doc.select("#highlight-text");
        Elements price = doc.select("#highlight-text > .price");
        Elements address = doc.select("#highlight-text > a > span");
        Elements adBody = doc.select("#highlight-text");

        for (Element link : adBody) {
            print(" * Text:  %s \nPrice: %s> ", link.text(), link.select(".price").text());
        }

        //System.out.println(body);
        print("\nMedia: (%d)", media.size());
//        for (Element src : media) {
//            if (src.tagName().equals("img"))
//                print(" * %s: <%s> %sx%s (%s)",
//                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
//                        trim(src.attr("alt"), 20));
//            else
//                print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
//        }

        print("\nImports: (%d)", imports.size());
        for (Element link : imports) {
            print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"), link.attr("rel"));
        }

        print("\nLinks: (%d)", links.size());
        for (Element link : links) {
            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
        }
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
}
