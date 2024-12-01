package main.java.pertemuan7.Tugas.view;

import javax.swing.*;

import main.java.pertemuan7.Tugas.listener.RegisUserListener;
import main.java.pertemuan7.Tugas.service.RegisUserServices;
import net.miginfocom.swing.MigLayout;

public class RegisUserForm extends JPanel {
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton registerButton;

    private RegisUserServices regisUserServices;

    public RegisUserForm(RegisUserServices regisUserServices) {
        this.regisUserServices = regisUserServices;
        setLayout(new MigLayout("wrap 2", "[][grow]"));

        // Name
        add(new JLabel("Name:"));
        nameField = new JTextField(20);
        add(nameField);

        // Email
        add(new JLabel("Email:"));
        emailField = new JTextField(20);
        add(emailField);

        // Password
        add(new JLabel("Password:"));
        passwordField = new JPasswordField(20);
        add(passwordField);

        // Register Button
        registerButton = new JButton("Register");
        add(registerButton, "span 2, align center");

        // Add the listener to the button
        registerButton.addActionListener(new RegisUserListener(this, regisUserServices));
    }

    // Getters for the form data
    public String getName() {
        return nameField.getText().trim();
    }

    public String getEmail() {
        return emailField.getText().trim();
    }

    public String getPassword() {
        return new String(passwordField.getPassword()).trim();
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
