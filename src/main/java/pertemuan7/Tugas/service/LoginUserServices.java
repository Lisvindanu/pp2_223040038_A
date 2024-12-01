package main.java.pertemuan7.Tugas.service;

import main.java.pertemuan7.Tugas.dao.LoginUserDaoInterface;
import main.java.pertemuan7.Tugas.model.LoginUsers;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.SQLException;

public class LoginUserServices {
    private LoginUserDaoInterface loginUserDao;
    private BCryptPasswordEncoder encoder;

    public LoginUserServices(LoginUserDaoInterface loginUserDao) {
        this.loginUserDao = loginUserDao;
        this.encoder = new BCryptPasswordEncoder();
    }

    // Logic for login
    public LoginUsers loginUser(String email, String password) throws SQLException {
        LoginUsers user = loginUserDao.loginUser(email, password);

        if(user != null) {
            if(encoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public boolean cekEmail(String email) throws SQLException {
        return loginUserDao.cekEmail(email);
    }
}
