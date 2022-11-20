package DataBase;

import java.sql.*;

public class DBConnector {

    private static String URL ="jdbc:mysql://localhost:3306/spritrechner";

    private static String USER = "root";

    private static String PASSWORD = "1234";

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
