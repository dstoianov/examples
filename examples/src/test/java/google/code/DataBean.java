package google.code;

import java.util.Comparator;

/**
 * Created by Funker on 29.06.2015.
 */
public class DataBean implements Comparable<DataBean> {

    private String name;
    private String url;
    private String date;


    public DataBean(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


//    public int compareTo(Fruit compareFruit) {
//        int compareQuantity = ((Fruit) compareFruit).getQuantity();
//        ascending order
//        return this.quantity - compareQuantity;
//        descending order
    //return compareQuantity - this.quantity;
//    }


    @Override
    public int compareTo(DataBean o) {
        String compareDate = ((DataBean) o).getDate();
        //ascending order
        return this.date.compareTo(compareDate);
        //descending order
//        return compareDate.compareTo(this.date);
    }


    public static Comparator<DataBean> DataBeanDateComparator = new Comparator<DataBean>() {
        public int compare(DataBean d1, DataBean d2) {
            String fruitName1 = d1.getDate().toUpperCase();
            String fruitName2 = d2.getDate().toUpperCase();
            //ascending order
            return fruitName1.compareTo(fruitName2);
            //descending order
            //return fruitName2.compareTo(fruitName1);
        }

    };
}
