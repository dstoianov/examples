package com.revimedia.tests.builder.newbuilder.test.multithread;

import org.testng.annotations.Test;

/**
 * Created by Funker on 19.08.2015.
 */
public class YandexTest extends BaseTest {


    @Test
    public void testName1() throws Exception {
        sleep();
        driver.get("https://www.yandex.ru/");
        sleep();
    }

    @Test
    public void testName2() throws Exception {
        sleep();
        driver.get("https://www.yandex.com/");
        sleep();
    }

    @Test
    public void testName3() throws Exception {
        sleep();
        driver.get("https://www.yandex.com.ua/");
        sleep();
    }
}
