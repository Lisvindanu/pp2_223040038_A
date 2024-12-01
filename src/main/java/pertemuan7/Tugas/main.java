package main.java.pertemuan7.Tugas;

import main.java.pertemuan7.Tugas.service.RegisUserServices;
import main.java.pertemuan7.Tugas.view.RegisUserForm;

import javax.swing.*;

public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Initialize the services
                RegisUserServices regisUserServices = new RegisUserServices();

                // Initialize the form and pass the services
                RegisUserForm regisUserForm = new RegisUserForm(regisUserServices);

                // Set up the JFrame
                JFrame frame = new JFrame("User Registration");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 300);
                frame.add(regisUserForm);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
