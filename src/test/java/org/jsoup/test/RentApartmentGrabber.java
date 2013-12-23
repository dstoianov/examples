package org.jsoup.test;

/**
 * Created by User on 12/18/13.
 */

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Example program to list links from a URL.
 */
public class RentApartmentGrabber {
    public static final String FILE_NAME = "src/test/resources/rent_list.xlsx";
    public static List<RentDataTO> list = new ArrayList<RentDataTO>();

    @Test
    public void AvisoTest() {
        //String url = "http://forum.od.ua/forumdisplay.php?f=172";
        String url = "http://bit.ly/JD1ADO";
        int before = list.size();
        print("Fetching %s...", url);
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
            Elements adAll = doc.select("#highlight-text");

            for (Element adOne : adAll) {
                if (adOne.select(".price").toString().equals("")) {
                    continue;
                }
                String adText = adOne.text();
                String adPrice = adOne.select(".price").text().substring(21);
                String adDate = adOne.select("div > span").text();
                String adAddress = adOne.select("a > span").text();
                String adMessage = adText.substring(adText.indexOf(adAddress) + adAddress.length());
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
        print("Were added to the base %s ads from Aviso", list.size() - before);
    }

    @Test
    public void SlandoTest() {

        String url = "http://bit.ly/1gJhQkV";
        int before = list.size();
        print("Fetching %s...", url);
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
            Elements adAll = doc.select("#offers_table > tbody > tr");

            for (Element adOne : adAll) {
                if (adOne.select("h3").toString().equals("")) {
                    continue;
                }
                String adAddress1 = adOne.select("p.color-9").text();
                String adAddress = adAddress1.substring(adAddress1.indexOf("Одесса") + "Одесса".length());
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
        print("Were added to the base %s ads from Slando", list.size() - before);
    }


    @Test
    public void ForumOdUaTest() {
        String url = "http://forum.od.ua/forumdisplay.php?f=172";

        int before = list.size();
        print("Fetching %s...", url);
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
            Elements adAll = doc.select("#highlight-text");

            for (Element adOne : adAll) {
                if (adOne.select(".price").toString().equals("")) {
                    continue;
                }
                String adText = adOne.text();
                String adPrice = adOne.select(".price").text().substring(21);
                String adDate = adOne.select("div > span").text();
                String adAddress = adOne.select("a > span").text();
                String adMessage = adText.substring(adText.indexOf(adAddress) + adAddress.length());
                String adURL = adOne.select("a").attr("href").toString();

                RentDataTO rentData = new RentDataTO();
                rentData.setSource("forum.od.ua");
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
        print("Were added to the base %s ads from Forum.od.ua", list.size() - before);
    }


    @Test
    public void testTTT() throws Exception {
        ArrayList<RentDataTO> rentDataTOs = new ArrayList<RentDataTO>();

        for (int i = 0; i < 12; i++) {
            RentDataTO to = new RentDataTO();
            to.setAddress("adress" + i);
            to.setDate("date" + i);
            to.setOther("other" + i);
            to.setPrice("price" + i);
            to.setSource("source" + i);
            to.setUrl("url" + i);
            to.setText("text" + i);
            rentDataTOs.add(to);
        }
        putRentDataTOs(rentDataTOs, FILE_NAME);
    }

    private void putRentDataTOs(ArrayList<RentDataTO> rentDataTOs, String fileName)  {
        FileInputStream file = null;
        try {
            file = new FileInputStream(new File(fileName));

            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(1);

            for (int j = 0; j < rentDataTOs.size(); j++) {
                Row row = sheet.createRow(j);
                List<String> fields = rentDataTOs.get(j).getFields();
                for (int i = 0; i < fields.size(); i++) {
                    row.createCell(i).setCellValue(fields.get(i));
                }
            }
            file.close();
            FileOutputStream out = new FileOutputStream(new File(fileName));
            workbook.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeClass
    public void initProxy (){
        //System.setProperty("http.proxyHost", "192.168.0.1");
       // System.setProperty("http.proxyPort", "8080");
    }


    @AfterClass
    public static void WriteXlsx() {
        try {
            FileInputStream file = new FileInputStream(new File(FILE_NAME));
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet sheet = workbook.getSheetAt(0);
            int lastrow = sheet.getLastRowNum() + 1;
            //Create a blank sheet
            //XSSFSheet sheet = workbook.createSheet("Rent Data");

//            RentDataTO rentData = new RentDataTO();
//            rentData.setSource("slando");
//            rentData.setAddress("Adress");
//            rentData.setText("MEssage");
//            rentData.setPrice("price");
//            rentData.setDate("Data");
//            rentData.setUrl("test url");
//            rentData.setOther("test field other 999");
//            list.add(rentData);

            RentDataTO rentObj;

            for (int rownum = 0; rownum < list.size(); rownum++) {
                Row row = sheet.createRow(rownum + lastrow);
                rentObj = list.get(rownum);

                row.createCell(0).setCellValue(rentObj.getSource());
                row.createCell(1).setCellValue(rentObj.getAddress());
                row.createCell(2).setCellValue(rentObj.getText());
                row.createCell(3).setCellValue(rentObj.getPrice());
                row.createCell(4).setCellValue(rentObj.getDate());
                row.createCell(5).setCellValue(rentObj.getUrl());
                row.createCell(6).setCellValue(rentObj.getOther());
            }

            file.close();
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File(FILE_NAME));
            workbook.write(out);
            out.close();
            System.out.println("rent_list.xlsx written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }
}
