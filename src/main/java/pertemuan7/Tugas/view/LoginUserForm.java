package main.java.pertemuan7.Tugas.view;

import javax.swing.*;
import java.awt.*;

import main.java.pertemuan7.Tugas.dao.LoginUserDao;
import main.java.pertemuan7.Tugas.listener.LoginUserListener;
import main.java.pertemuan7.Tugas.service.LoginUserServices;
import net.miginfocom.swing.MigLayout;

public class LoginUserForm extends JPanel {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginUserForm() {
        setLayout(new MigLayout("wrap 1", "[grow]", "[]10[]10[]10[]"));

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);

        loginButton = new JButton("Login");
        loginButton.addActionListener(e -> new LoginUserListener(this, new LoginUserServices(new LoginUserDao())).actionPerformed(e));

        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
    }

    // Getter methods for form data
    public String getEmail() {
        return emailField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    // Method to show messages
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public JButton getLoginButton() {
        return loginButton;
    }
}
