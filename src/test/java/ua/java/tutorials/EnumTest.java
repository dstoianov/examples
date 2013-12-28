package ua.java.tutorials;

//import org.junit.Test;

import org.testng.annotations.Test;

public class EnumTest {

    @Test
    public void RunEnumTest() {
        System.out.println(3+5);
        System.out.println(UserStatus.ACTIVE.getStatusCode());
        System.out.println(UserStatus.INACTIVE.getStatusCode());
    }

    @Test
    public void PlanetTest() {
        double earthWeight = 175;
        double mass = earthWeight / Planet.EARTH.surfaceGravity();
        for (Planet p : Planet.values())
            System.out.printf("Your weight on %s is %f%n", p, p.surfaceWeight(mass));
    }


    @Test
    public void PlanetTest2() {
        //double getMass = Planet.EARTH.surfaceGravity();
        for (Planet p : Planet.values()) {
            System.out.println(p.getMass());
            System.out.println(p.getRadius());
        }
        // for (Plane p : Planet)

        //   System.out.printf("Your weight on %s is %f%n", p, p.surfaceWeight(getMass));
    }


}
