package main.java.pertemuan7.Tugas.util;

import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseConnection {
    private final static String DB_URL = "jdbc:mysql://localhost:3306/pp2tugas_223040038";
    private final static String USER = "root";
    private final static String PASS = "password";

    public static DatabaseConnection instance;
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return con;
    }
}
