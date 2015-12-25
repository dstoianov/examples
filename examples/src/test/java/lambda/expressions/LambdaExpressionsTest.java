package lambda.expressions;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by dstoianov on 2015-12-25.
 */
public class LambdaExpressionsTest {

    public List<EmployeeBean> employeeList = null;

    @BeforeMethod
    public void setUp() {
        employeeList = new ArrayList<>();
        employeeList.add(new EmployeeBean(1, "Praveen", 123));
        employeeList.add(new EmployeeBean(2, "Aseer", 1234));
        employeeList.add(new EmployeeBean(3, "Ravi", 12345));

        System.out.println("***********Before Sort***************");
        for (EmployeeBean e : employeeList) {
            System.out.println(e);
        }
    }

    @Test
    public void testStandardWayOfSorting() {

        Comparator<EmployeeBean> byName = new Comparator<EmployeeBean>() {
            @Override
            public int compare(EmployeeBean o1, EmployeeBean o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };

        System.out.println("***********After Sort***************");

        Collections.sort(employeeList, byName);
        for (EmployeeBean e : employeeList) {
            System.out.println(e);
        }

    }


    @Test
    public void testJava8WayOfSorting() {

//        Comparator<EmployeeBean> byName = (EmployeeBean e1, EmployeeBean e2) -> e1.getName().compareTo(e2.getName());
        Comparator<EmployeeBean> byName = (e1, e2) -> e1.getName().compareTo(e2.getName());

        System.out.println("***********After Sort***************");

        Collections.sort(employeeList, byName);

        for (EmployeeBean e : employeeList) {
            System.out.println(e);
        }

    }
}
