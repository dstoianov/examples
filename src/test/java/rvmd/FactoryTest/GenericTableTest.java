package rvmd.FactoryTest;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * User: stoianod      http://habrahabr.ru/post/121234/
 * Date: 4/9/14
 */
public class GenericTableTest extends Assert {

    private String table;

    public GenericTableTest(String table) {
        this.table = table;
    }

    @Test
    public void testTable() {
        System.out.println(table);
        // do some testing staff here
    }
}
