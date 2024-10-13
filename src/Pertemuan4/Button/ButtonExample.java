package Pertemuan4.Button;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonExample implements ActionListener {
    private JFrame frame = new JFrame();
    private JButton button = new JButton();

    public ButtonExample() {

       frame = new JFrame();
       frame.setTitle("ButtonExample");

       button = new JButton("Click Me");
       button.setBounds(100, 100, 100, 50);
       button.addActionListener(this);

       frame.add(button);
       frame.setSize(300,200);
       frame.setLayout(null);
       frame.setVisible(true);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Tombol Ditekan");
        JOptionPane.showMessageDialog(frame, "Tombol Ditekan");
    }
}
