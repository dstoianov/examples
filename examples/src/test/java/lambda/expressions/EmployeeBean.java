package lambda.expressions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by dstoianov on 2015-12-25.
 */
public class EmployeeBean {
    private int id;
    private int salary;
    private String name;

    public EmployeeBean(int id, String name, int salary) {
        this.id = id;
        this.salary = salary;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + salary;
    }

    public static void main(String[] args) {

        List<EmployeeBean> employeeList = new ArrayList<>();
        employeeList.add(new EmployeeBean(1, "Praveen", 123));
        employeeList.add(new EmployeeBean(2, "Aseer", 1234));
        employeeList.add(new EmployeeBean(3, "Ravi", 12345));

        System.out.println("***********Before Sort***************");
        for (EmployeeBean e : employeeList)
            System.out.println(e);
        System.out.println("***********After Sort***************");

        Comparator<EmployeeBean> byName = new Comparator<EmployeeBean>() {
            @Override
            public int compare(EmployeeBean o1, EmployeeBean o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };

        Collections.sort(employeeList, byName);
        for (EmployeeBean e : employeeList) {
            System.out.println(e);
        }
    }
}
