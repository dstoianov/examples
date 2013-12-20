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
import java.util.ArrayList;
import java.util.List;

/**
 * Example program to list links from a URL.
 */
public class RentApartmentGrabber {

    List<RentDataTO> list = new ArrayList<RentDataTO>();

    @Test
    public void AvisoTest() {
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
                String adMessage =  adText.substring(adText.indexOf(adAddress) + adAddress.length());
                String adURL = adOne.select("a").attr("href").toString();

                RentDataTO rentData = new RentDataTO();
                rentData.setSource("aviso");
                rentData.setAddress(adAddress);
                rentData.setText(adMessage);
                rentData.setPrice(adPrice);
                rentData.setDate(adDate);
                rentData.setUrl(adURL);
                rentData.setOther("test field other");
                list.add(rentData);

//                System.out.println("----------------------------------------------------------------------------");
//                print( "1) Address: %s " +
//                     "\n2) Text:  %s " +
//                     "\n3) Price: %s " +
//                     "\n4) Date: %s " +
//                     "\n5) URL: %s", adAddress, adMessage, adPrice, adDate, adURL);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        print("Were added to the base %s ads from Aviso", list.size());
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
                if (adOne.select("h3").toString().equals("")) { continue; }
                String adAddress1 = adOne.select("p.color-9").text();
                String adAddress =  adAddress1.substring(adAddress1.indexOf("Одесса") + "Одесса".length());
                String adMessage = adOne.select("span.fbold").text();
                String adPrice = adOne.select("strong.c000").text();
                String adDate = adOne.select("p.margintop10").first().text();
                String adURL = adOne.select("h3 > a").attr("href").toString();

                RentDataTO rentData = new RentDataTO();
                rentData.setSource("slando");
                rentData.setAddress(adAddress);
                rentData.setText(adMessage);
                rentData.setPrice(adPrice);
                rentData.setDate(adDate);
                rentData.setUrl(adURL);
                rentData.setOther("test field other");
                list.add(rentData);

//                System.out.println("----------------------------------------------------------------------------");
//                print( "1) Address: %s " +
//                        "\n2) Text:  %s " +
//                        "\n3) Price: %s " +
//                        "\n4) Date: %s " +
//                        "\n5) URL: %s", adAddress, adMessage, adPrice, adDate, adURL);
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
        print("Were added to the base %s ads from Slando", list.size());
    }



    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }
}
