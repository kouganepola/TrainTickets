package traintickets.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static Connection connection = null;

    private DBConnector() {
    }

    public static Connection getDBConnection() throws ClassNotFoundException, SQLException {

        if (connection == null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:33000/odemetrics", "root", "root");

        }

        return connection;
    }
}
