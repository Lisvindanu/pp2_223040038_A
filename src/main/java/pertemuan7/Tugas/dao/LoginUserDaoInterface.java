package main.java.pertemuan7.Tugas.dao;

import main.java.pertemuan7.Tugas.model.LoginUsers;

import java.sql.SQLException;

public interface LoginUserDaoInterface {
    int registerUser(LoginUsers user) throws SQLException;
    LoginUsers loginUser(String email, String password) throws SQLException;
    boolean cekEmail(String email) throws SQLException;

}
