package main.java.pertemuan7.Tugas.service;

import main.java.pertemuan7.Tugas.dao.UserDaoInterface;
import main.java.pertemuan7.Tugas.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private UserDaoInterface userDao;

    public UserService(UserDaoInterface userDao) {
        this.userDao = userDao;
    }

    public int addUser(User user) throws SQLException {
        return userDao.addUser(user);
    }

    public List<User> getAllUsers() throws SQLException {
        return userDao.getAllUser();
    }


    public User getUserById(int id) throws SQLException {
        return userDao.getUserById(id);
    }

    public int updateUser(User user) throws SQLException {
        return userDao.updateUser(user);
    }

    public int deleteUser(int id) throws SQLException {
        return userDao.deleteUser(id);
    }
}
