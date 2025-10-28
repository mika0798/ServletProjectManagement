package com.project.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;
    private static String Driver =  "com.mysql.cj.jdbc.Driver";
    private static String Url = "jdbc:mysql://localhost:3307/crm";
    private static String User = "root";
    private static String Password = "admin123";

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
