package test.tasks;

/**
 * Created by Funker on 02.08.2014.
 */

import java.lang.reflect.Field;

public class ReflectionSample {

    public static void main(String[] args) {

        Fridge fridge = new Fridge(2000, 220, true);

        Tattoo tattoo = new Tattoo("Vladimirsky Central", "Sorry my mommy", "Die garbages!");

        System.err.println("fridge.power: " + getFieldValue(fridge, "power"));
        System.err.println("fridge.voltage: " + getFieldValue(fridge, "voltage"));
        System.err.println("fridge.circuit: " + getFieldValue(fridge, "circuit"));

        System.err.println("tattoo.string: " + getFieldValue(tattoo, "string"));
        System.err.println("tattoo.another: " + getFieldValue(tattoo, "another"));
        System.err.println("tattoo.theother: " + getFieldValue(tattoo, "theother"));
    }

    private static String getFieldValue(Object object, String fieldName) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            Object value = field.get(object);
            return String.valueOf(value);
        } catch (Exception e) {
            return null;
        }
    }

    static class Fridge {
        private int power;
        private int voltage;
        private boolean circuit;

        Fridge() {
        }

        Fridge(int power, int voltage, boolean circuit) {
            this.power = power;
            this.voltage = voltage;
            this.circuit = circuit;
        }

        public int getPower() {
            return power;
        }

        public void setPower(int power) {
            this.power = power;
        }

        public int getVoltage() {
            return voltage;
        }

        public void setVoltage(int voltage) {
            this.voltage = voltage;
        }

        public boolean isCircuit() {
            return circuit;
        }

        public void setCircuit(boolean circuit) {
            this.circuit = circuit;
        }
    }

    static class Tattoo {
        private String string;
        private String another;
        private String theother;

        Tattoo() {
        }

        Tattoo(String theother, String another, String string) {
            this.theother = theother;
            this.another = another;
            this.string = string;
        }

        public String getString() {
            return string;
        }

        public void setString(String string) {
            this.string = string;
        }

        public String getAnother() {
            return another;
        }

        public void setAnother(String another) {
            this.another = another;
        }

        public String getTheother() {
            return theother;
        }

        public void setTheother(String theother) {
            this.theother = theother;
        }
    }
}
