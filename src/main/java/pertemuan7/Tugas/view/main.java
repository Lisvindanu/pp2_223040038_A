package main.java.pertemuan7.Tugas.view;

import main.java.pertemuan7.Tugas.dao.LoginUserDao;
import main.java.pertemuan7.Tugas.listener.LoginUserListener;
import main.java.pertemuan7.Tugas.service.LoginUserServices;

import javax.swing.*;

public class main {
    public static void main(String[] args) {
        // Create the form and service
        LoginUserForm loginUserForm = new LoginUserForm();
        LoginUserServices loginUserServices = new LoginUserServices(new LoginUserDao());

        // Create the listener and attach it to the form
        LoginUserListener loginUserListener = new LoginUserListener(loginUserForm, loginUserServices);

        // Add listener to the login button (this will trigger the action)
        loginUserForm.getLoginButton().addActionListener(loginUserListener);

        // Set up the JFrame to display the LoginForm
        JFrame frame = new JFrame("Login Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null); // Center the window
        frame.add(loginUserForm);  // Add the LoginUserForm panel
        frame.setVisible(true);
    }
}
