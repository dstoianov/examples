package parametrized.FactoryTest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

/**
 * User: stoianod
 * Date: 4/9/14
 */
public class FactoryTest {
    //http://habrahabr.ru/post/121234/


    @DataProvider
    public Object[][] tablesData() {
        return new Object[][]{
                {"FIRST_TABLE"},
                {"SECOND_TABLE"},
                {"THIRD_TABLE"},
        };
    }

    @Factory(dataProvider = "tablesData")
    public Object[] createTest(String table) {
        return new Object[]{new GenericTableTest(table)};
    }


    @Parameters("table")
    @Factory
    public Object[] createParameterizedTest(@Optional("SOME_TABLE") String table) {
        return new Object[]{new GenericTableTest(table)};
    }

}