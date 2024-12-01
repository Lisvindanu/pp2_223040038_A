package main.java.pertemuan7.Tugas.Test;

import main.java.pertemuan7.Tugas.dao.LoginUserDao;
import main.java.pertemuan7.Tugas.dao.LoginUserDaoInterface;
import main.java.pertemuan7.Tugas.model.LoginUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginUsersTest {
    private LoginUserDaoInterface loginUserDao;

    @BeforeEach
    public void setUp() {
        loginUserDao = new LoginUserDao();
    }

    @Test
    public void testLoginUser() throws SQLException {
        LoginUsers newUser = new LoginUsers();
        newUser.setNama("naruto");
        newUser.setEmail("Naruto@gmail.com");
        newUser.setPassword("passwordAman");

        int res = loginUserDao.registerUser(newUser);

        assertTrue(res > 0, "User registration should be successful.");
        System.out.println("User registered successfully with email: " + newUser.getEmail());


    }
}
