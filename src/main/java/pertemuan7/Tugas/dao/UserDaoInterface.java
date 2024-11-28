package main.java.pertemuan7.Tugas.dao;

import main.java.pertemuan7.Tugas.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDaoInterface {
   int addUser(User user) throws SQLException;
   List<User> getAllUser() throws SQLException;
   User getUserById(int id) throws SQLException;
   int updateUser(User user) throws SQLException;
   int deleteUser(int id) throws SQLException;
}
