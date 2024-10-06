package Pertemuan3.Latihan4;

import javax.swing.*;
import java.awt.*;

public class HelloGridBagLayout extends JFrame {
    JLabel headerLabel = new JLabel("Layout in action : GridBagLayout", JLabel.CENTER);
    JPanel controlPanel = new JPanel();
    JPanel panel = new JPanel();
    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();

    HelloGridBagLayout() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        controlPanel.setLayout(new FlowLayout());

        panel.setBackground(Color.gray);
        panel.setSize(300,300);
        panel.setLayout(layout);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JButton("Button 1"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(new JButton("Button 2"), gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 20;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JButton("Button 3"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(new JButton("Button 4"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        panel.add(new JButton("Button 5"), gbc);

        controlPanel.add(panel);
        this.setLayout(new GridLayout(2,1));
        this.add(headerLabel);
        this.add(controlPanel);
        this.setSize(400,400);
        this.setVisible(true);
    }
}
