import java.sql.*;

public class DBConnector {

    private static String URL ="jdbc:mysql://localhost:3306/spritrechner";

    private static String USER = "root";

    private static String PASSWORD = "1234";

    public static Connection connect() throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }
}
