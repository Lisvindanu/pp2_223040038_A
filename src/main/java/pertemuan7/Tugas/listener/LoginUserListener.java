package main.java.pertemuan7.Tugas.listener;

import main.java.pertemuan7.Tugas.view.LoginUserForm;
import main.java.pertemuan7.Tugas.model.LoginUsers;
import main.java.pertemuan7.Tugas.service.LoginUserServices;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginUserListener implements ActionListener {
    private LoginUserForm loginUserForm;
    private LoginUserServices loginUserServices;

    public LoginUserListener(LoginUserForm loginUserForm, LoginUserServices loginUserServices) {
        this.loginUserForm = loginUserForm;
        this.loginUserServices = loginUserServices;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String email = loginUserForm.getEmail();
        String password = loginUserForm.getPassword();

        // Check if email and password are provided
        if (email.isEmpty() || password.isEmpty()) {
            loginUserForm.showMessage("Please enter both email and password.");
            return;
        }

        try {
            // Attempt to login the user
            LoginUsers user = loginUserServices.loginUser(email, password);

            // If user is found, login is successful
            if (user != null) {
                loginUserForm.showMessage("Login successful.");
                // Proceed with further actions (e.g., navigating to the user dashboard)
            } else {
                loginUserForm.showMessage("Invalid email or password.");
            }
        } catch (SQLException ex) {
            // Handle any SQL errors during login
            loginUserForm.showMessage("Error: " + ex.getMessage());
        }
    }
}
