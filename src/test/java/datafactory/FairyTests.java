package datafactory;

import org.jfairy.Fairy;
import org.jfairy.producer.company.Company;
import org.jfairy.producer.person.Person;
import org.testng.annotations.Test;

import static org.jfairy.producer.person.PersonProperties.withCompany;

/**
 * Created by Funker on 19.04.14.
 */
public class FairyTests {

    @Test
    public void testName() throws Exception {

        Fairy fairy = Fairy.create();//.create();
        Person person = fairy.person();


        System.out.println(person.fullName());            // Chloe Barker
        System.out.println(person.email());               // barker@yahoo.com
        System.out.println(person.telephoneNumber());     // 690-950-802

//        Company company = fairy.company();
//        System.out.println(company.name());          // Robuten Associates
//        System.out.println(company.url());           // http://www.robuteniaassociates.com
//
//        Person salesman = fairy.person(withCompany(company));
//        System.out.println(salesman.fullName());     // Juan Camacho
//        System.out.println(salesman.companyEmail());

    }
}
