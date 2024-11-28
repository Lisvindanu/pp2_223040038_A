package main.java.pertemuan7.db;

import java.sql.Connection;
import java.sql.DriverManager;


public class MysqlConnection {
    private final static String DB_URL = "jdbc:mysql://localhost:3306/pp2_223040038";
    private final static String USER = "root";
    private final static String PASS = "password";

    public static MysqlConnection instance;
    public static MysqlConnection getInstance() {
        if (instance == null) {
            instance = new MysqlConnection();
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
