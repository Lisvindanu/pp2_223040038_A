package main.java.pertemuan7.Tugas;

import main.java.pertemuan7.Tugas.dao.UserDao;
import main.java.pertemuan7.Tugas.model.User;

import java.sql.SQLException;

public class main {
       public static void main(String[] args) {
        UserDao userDao = new UserDao();

//        // main.java.Test addUser
//        try {
//            User user = new User(0, "main.java.Test User", "1990-01-01", "08123456789", "test@example.com", "Jl. main.java.Test", 5, false, 1, 1);
//            int result = userDao.addUser(user);
//            System.out.println("Add User Result: " + result);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        // main.java.Test getAllUsers
//        try {
//            List<User> users = userDao.getAllUser();
//            System.out.println("All Users: ");
//            for (User user : users) {
//                System.out.println(user);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        // main.java.Test getUserById
        try {
            User user = userDao.getUserById(3); // Pastikan ID ini ada di database
            System.out.println("User with ID 1: " + user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        // main.java.Test deleteUser
//        try {
//            int result = userDao.deleteUser(1); // Pastikan ID ini ada di database
//            System.out.println("Delete User Result: " + result);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
