package stepdefinations;

import java.sql.*;

public class DBUtils {

    public static Connection connection;

    public static void connectDB() throws Exception {

        String url = "jdbc:mysql://localhost:3306/testdb";
        String username = "root";
        String password = "Ashwini@13";

        connection = DriverManager.getConnection(url, username, password);
        System.out.println("Connected to DB");
    }

    public static ResultSet getLoginData() throws Exception {

        String query = "SELECT username, password FROM users LIMIT 1";

        Statement stmt = connection.createStatement();
        return stmt.executeQuery(query);
    }

    public static void closeDB() throws Exception {
        connection.close();
        System.out.println("DB Closed");
    }
}