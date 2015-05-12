package ua.com.antenka.test.helpers;

/**
 * Created by User on 1/22/14, 11:13 PM.
 */
public class User {

    private String name;
    private String familyName;
    private String phone;

    public User(String name, String familyName, String phone) {
        this.name = name;
        this.familyName = familyName;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getPhone() {
        return phone;
    }
}
