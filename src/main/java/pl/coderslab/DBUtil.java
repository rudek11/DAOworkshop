package pl.coderslab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String DB_USER = "rudek11";
    private static final String DB_PASSWORD = "password";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/WorkShopDao?serverTimezone=UTC";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

    }
}