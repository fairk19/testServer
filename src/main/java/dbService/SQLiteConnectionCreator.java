package dbService;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by vanik on 11.04.14.
 */
public class SQLiteConnectionCreator {
    private static Connection connection;
    public static Connection getConnection() {
        try {
            Driver driver = (Driver) Class.forName("org.sqlite.JDBC").newInstance();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection("jdbc:sqlite::memory:");
            String query = "create table if not exists Users(id integer primary key, nickname varchar(20) not null unique, password char(64) not null," +
                    " registration_date timestamp not null, rating int unsigned not null default 1500, win_quantity int unsigned not null default 0, lose_quantity int unsigned not null default 0,unique(nickname));";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
