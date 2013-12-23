package org.jsoup.test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 12/20/13.
 */
public class RentDataTO {

    private String source;
    private String address;
    private String text;
    private String price;
    private String date;
    private String url;
    private String other;

    public RentDataTO() {
    }

    public RentDataTO(String source, String address, String text, String price, String date, String url, String other) {
        this.source = source;
        this.address = address;
        this.text = text;
        this.price = price;
        this.date = date;
        this.url = url;
        this.other = other;
    }

    public List<String> getFields() {
        ArrayList<String> values = new ArrayList<String>();
        try {
            for (Field field : this.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                values.add((String) field.get(this));
            }
        } catch (IllegalAccessException e) {
            throw new Error("An error occurred!!!");
        }
        return values;
    }



    @Override
    public String toString() {
        return "RentDataTO{" +
                "\nsource='" + source + '\'' +
                ", \naddress='" + address + '\'' +
                ", \ntext='" + text + '\'' +
                ", \nprice='" + price + '\'' +
                ", \ndate='" + date + '\'' +
                ", \nurl='" + url + '\'' +
                ", \nother='" + other + '\'' +
                '}';
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
