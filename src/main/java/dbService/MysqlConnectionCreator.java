package dbService;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

/**
 * Created by vanik on 11.04.14.
 */
public class MysqlConnectionCreator {
    private static Connection connection;
    private static String CONNECTION_URL ="jdbc:mysql://localhost:3306/qualityTestDB?user=root&password=110708";
    public static Connection getConnection() {
        try {
            Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(CONNECTION_URL);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
