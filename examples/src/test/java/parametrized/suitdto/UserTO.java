package parametrized.suitdto;

/**
 * User: stoianod
 * Date: 4/8/14
 */
public class UserTO {
    String firstName;
    String lastName;
    int age;

    UserTO(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}