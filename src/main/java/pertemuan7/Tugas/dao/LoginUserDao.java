package main.java.pertemuan7.Tugas.dao;

import main.java.pertemuan7.Tugas.model.LoginUsers;
import main.java.pertemuan7.Tugas.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginUserDao implements LoginUserDaoInterface{

    @Override
    public boolean cekEmail(String email) throws SQLException {
        String query = "Select id from login_users where email = ?";
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    @Override
    public LoginUsers loginUser(String email, String password) throws SQLException {
       String query = "Select * from login_users where email = ? and password = ?";
       try(Connection conn = DatabaseConnection.getInstance().getConnection();
       PreparedStatement stmt = conn.prepareStatement(query)) {
           stmt.setString(1, email);
           stmt.setString(2, password);
           ResultSet rs = stmt.executeQuery();
           if(rs.next()) {
               return mapResultSetToLoginUsers(rs);
           }
       }
       return null;
    }

    @Override
    public int registerUser(LoginUsers user) throws SQLException {
        String query = "Insert into login_users(nama, email, password) values(?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getNama());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            return stmt.executeUpdate();
        }
    }

    private LoginUsers mapResultSetToLoginUsers(ResultSet rs) throws SQLException {
        return new LoginUsers(
                rs.getInt("id"),
                rs.getString("nama"),
                rs.getString("email"),
                rs.getString("password")
        );
    }
}
