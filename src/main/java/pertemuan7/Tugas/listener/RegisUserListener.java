package main.java.pertemuan7.Tugas.listener;

import main.java.pertemuan7.Tugas.view.RegisUserForm;
import main.java.pertemuan7.Tugas.service.RegisUserServices;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RegisUserListener implements ActionListener {
    private RegisUserForm regisUserForm;
    private RegisUserServices regisUserServices;

    public RegisUserListener(RegisUserForm regisUserForm, RegisUserServices regisUserServices) {
        this.regisUserForm = regisUserForm;
        this.regisUserServices = regisUserServices;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = regisUserForm.getName();
        String email = regisUserForm.getEmail();
        String password = regisUserForm.getPassword();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            regisUserForm.showMessage("Please fill in all fields.");
            return;
        }

        // Call the registerUser method
        try {
            String result = regisUserServices.registerUser(name, email, password);
            regisUserForm.showMessage(result);  // Show result in the form
        } catch (IOException ex) {
            regisUserForm.showMessage("Error: " + ex.getMessage());
        }
    }
}
