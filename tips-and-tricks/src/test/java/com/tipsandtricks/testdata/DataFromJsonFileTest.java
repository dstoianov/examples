package com.tipsandtricks.testdata;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class DataFromJsonFileTest {

    private Data data;

    @BeforeTest
    public void beforeTest() throws IOException {
        data = Data.get("./src/test/java/com/tipsandtricks/testdata/data.json");
    }


    @Test
    public void testName() {
        System.out.println(data);
    }
}
