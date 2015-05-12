package ua.com.antenka.test.helpers;

import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Created by User on 1/21/14, 11:11 PM.
 */
public class DataProviders {

    public static void main(String[] args) throws IOException {
        //List<String> myList = scannerReader();
        List<String> myList = bReader();

        for (String element : myList) {
            System.out.println(element);
        }

    }

    public static List<String> scannerReader() {
        List<String> list = new ArrayList<String>();
        Scanner s = null;
        try {
            //s = new Scanner(new File("./src/test/java/ua/com/antenka/test/helpers/data.csv.txt"));
            s = new Scanner(new File("./src/test/java/ua/com/antenka/test/helpers/midle_name.txt"));
            while (s.hasNext()) {
                list.add(s.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            s.close();
        }
        return list;
    }


    public static List<String> bReader() throws IOException {
        BufferedReader in = null;
        List<String> myList = new ArrayList<String>();
        try {
            in = new BufferedReader(new FileReader("./src/test/java/ua/com/antenka/test/helpers/midle_name.txt"));
            String str;
            while ((str = in.readLine()) != null) {
                myList.add(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
        return myList;
    }


    @DataProvider
    public static Iterator<Object[]> getUser() {
        List<Object[]> list = new ArrayList<Object[]>();
        File file = new File("./src/test/java/ua/com/antenka/test/helpers/data_Kharkiv_edited.csv.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            br.readLine(); // don't read the HEADER, skipped read header
            String s = br.readLine();
            while (s != null) {
                String[] parts = s.split(",");
                User user = new User(parts[0], parts[1], parts[2]);
                list.add(new Object[]{user});
                s = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list.iterator();
    }


}
