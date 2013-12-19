package org.jsoup.test;

/**
 * Created by User on 12/18/13.
 */

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Example program to list links from a URL.
 */
public class RentApartmentGrabber {


    @Test
    public void AvisoTest() {
        //String url = "http://google.com.ua/";
        //String url = "http://forum.od.ua/forumdisplay.php?f=172";
        String url = "http://bit.ly/JD1ADO";


        print("Fetching %s...", url);
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
            Elements adAll = doc.select("#highlight-text");

            for (Element adOne : adAll) {
                if (adOne.select(".price").toString().equals("")) { continue; }
                String adText = adOne.text();
                String adPrice = adOne.select(".price").text().substring(21);
                String adDate = adOne.select("div > span").text();
                String adAddress = adOne.select("a > span").text();

                int i = adText.indexOf(adAddress);
                String adText2 =  adText.substring(i + adAddress.length());

               String adURL = adOne.select("a").attr("href").toString();

                System.out.println("----------------------------------------------------------------------------");
                print( "1) Address: %s " +
                     "\n2) Text:  %s " +
                     "\n3) Price: %s " +
                     "\n4) Date: %s " +
                     "\n5) URL: %s", adAddress, adText2, adPrice, adDate, adURL);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void SlandoTest() {

        String url = "http://bit.ly/1gJhQkV";


        print("Fetching %s...", url);
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
            Elements adAll = doc.select("#offers_table > tbody > tr");

            for (Element adOne : adAll) {
                if (adOne.select(".price").toString().equals("")) { continue; }
                String adText = adOne.text();
                String adPrice = adOne.select(".price").text().substring(21);
                String adDate = adOne.select("div > span").text();
                String adAddress = adOne.select("a > span").text();

                int i = adText.indexOf(adAddress);
                String adText2 =  adText.substring(i + adAddress.length());

                String adURL = adOne.select("a").attr("href").toString();

                System.out.println("----------------------------------------------------------------------------");
                print( "1) Address: %s " +
                        "\n2) Text:  %s " +
                        "\n3) Price: %s " +
                        "\n4) Date: %s " +
                        "\n5) URL: %s", adAddress, adText2, adPrice, adDate, adURL);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width - 1) + ".";
        else
            return s;
    }
}
