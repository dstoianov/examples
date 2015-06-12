package sql;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by dstoianov on 2015-06-12.
 */
public class SQLiteJDBC {


    public static void main(String args[]) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            URL resource = SQLiteJDBC.class.getClassLoader().getResource("test_v3.sqlite3");

//            c = DriverManager.getConnection("jdbc:sqlite:test2.db");
            c = DriverManager.getConnection("jdbc:sqlite:" + resource.toURI().getPath());
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
//            String sql = "DELETE from COMPANY where ID=2;";
//            stmt.executeUpdate(sql);
//            c.commit();


            //select * from companies c WHERe c.country_code='DEU' LIMIT 10;
            ResultSet rs = stmt.executeQuery("SELECT * FROM ROUNDS_UKRAINE_BY_CITY;");

            int columnCount = rs.getMetaData().getColumnCount();

            while (rs.next()) {

                String company_country_code = rs.getString("company_country_code");
                String company_region = rs.getString("company_region");
                int count = rs.getInt("_COUNT_");


                System.out.println(company_country_code + " | " + company_region + " | " + count);

            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
}
