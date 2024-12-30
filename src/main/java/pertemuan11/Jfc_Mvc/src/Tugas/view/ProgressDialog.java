package main.java.pertemuan11.Jfc_Mvc.src.Tugas.view;

import javax.swing.*;
import java.awt.*;

public class ProgressDialog extends JDialog {
    private final JProgressBar progressBar;
    private final JLabel statusLabel;

    public ProgressDialog(Frame parent, String title) {
        super(parent, title, true);

        // Create components
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);

        statusLabel = new JLabel("Preparing...", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        // Layout
        setLayout(new BorderLayout(10, 10));
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(statusLabel, BorderLayout.NORTH);
        panel.add(progressBar, BorderLayout.CENTER);

        add(panel);
        setSize(300, 100);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }

    public void updateProgress(int value) {
        progressBar.setValue(value);
    }

    public void updateStatus(String status) {
        statusLabel.setText(status);
    }
}