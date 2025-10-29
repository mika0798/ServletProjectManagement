package com.project.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;
    private static String Driver =  "com.mysql.cj.jdbc.Driver";
    private static String Url = System.getenv("DB_URL");
    private static String User = System.getenv("DB_USER");
    private static String Password = System.getenv("DB_PASS");

    public static Connection getConnection() {
        try {
            Class.forName(Driver);
            System.out.println("Driver Loaded");
            connection = DriverManager.getConnection(Url,User,Password);
            System.out.println("Connection Established");
        } catch (ClassNotFoundException e) {
            System.out.println("Class " + Driver + " not found");
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return connection;
    }
}
