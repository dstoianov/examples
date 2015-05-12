package xmltoobject;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.List;

/**
 * User: stoianod
 * Date: 4/17/14
 */

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class Users implements RandomObject {

    @XmlElement(name = "user")
    private List<User> users = null;

    public List<User> getUsers() {
        return users;
    }

    public void setEmployees(List<User> users) {
        this.users = users;
    }

    public User getRandom() {
        Collections.shuffle(users);
        return users.get(0);
        //return this.employees.get(new Random().nextInt(this.employees.size()-1));
    }
}
