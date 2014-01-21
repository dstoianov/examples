package ua.com.antenka.test.helpers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by User on 1/21/14, 11:11 PM.
 */
public class DataProvider {

    public static void main(String[] args) throws IOException {
        //List<String> myList = scannerReader();
        List<String> myList = bReader();

        for (String element : myList) {
            System.out.println(element);
        }

    }

    public static List<String> scannerReader(){
        List<String> list = new ArrayList<String>();
        Scanner s = null;
        try {
            //s = new Scanner(new File("./src/test/java/ua/com/antenka/test/helpers/data.csv.txt"));
            s = new Scanner(new File("./src/test/java/ua/com/antenka/test/helpers/midle_name.txt"));
            while (s.hasNext()){
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
        return  myList;
    }



}
