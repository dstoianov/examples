package com.revimedia.tests.cds.DraftTests;

import java.lang.reflect.Field;

/**
 * Created by Funker on 23.05.14.
 */


public class TestClass {

    public static void main(String[] args) throws Exception {
        String[] fields = {"field1", "field2", "field3"};
        String[] fields2 = {"field6", "field7", "field8"};
        String[] fields3 = {"field6", "field7", "field10"};

        Test1 te1 = new Test1("12", "14", "87", "gg", "yy");
        Test2 te2 = new Test2("12", "14", "87", "gg", "ee");

        te1.equals(fields, te2, fields2);
        te1.equals(fields, te2, fields3);
    }

    abstract static class Equals {
        public boolean equals(String[] thisFields, Object that, String[] thatFields) throws Exception {
            if (thisFields.length != thatFields.length) throw new Exception("Arrays should have the same length");
            try {
                for (int i = 0; i < thisFields.length; i++) {
                    Field thisField = this.getClass().getDeclaredField(thisFields[i]);
                    thisField.setAccessible(true);

                    Field thatField = that.getClass().getDeclaredField(thatFields[i]);
                    thatField.setAccessible(true);


                    if (!thisField.get(this).equals(thatField.get(that))) return false;
                }
            } catch (NoSuchFieldException e) {
                return false;
            } catch (IllegalAccessException e) {
                return false;
            }
            return true;
        }
    }

    static class Test1 extends Equals {
        private String field1;
        private String field2;
        private String field3;
        private String field4;
        private String field5;

        Test1(String field1, String field2, String field3, String field4, String field5) {
            this.field1 = field1;
            this.field2 = field2;
            this.field3 = field3;
            this.field4 = field4;
            this.field5 = field5;
        }
    }

    static class Test2 extends Equals {
        private String field6;
        private String field7;
        private String field8;
        private String field9;
        private String field10;

        Test2(String field6, String field7, String field8, String field9, String field10) {
            this.field6 = field6;
            this.field7 = field7;
            this.field8 = field8;
            this.field9 = field9;
            this.field10 = field10;
        }
    }
}

