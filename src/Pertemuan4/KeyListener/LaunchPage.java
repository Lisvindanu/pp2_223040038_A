package Pertemuan4.KeyListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchPage implements ActionListener {

    JFrame frame = new JFrame();
    JLabel label = new JLabel();

    JButton myButton = new JButton("Example Button");
    JButton myButton1 = new JButton("Game");


    LaunchPage() {
        label.setText("Pilih salah satu");
        label.setBounds(100, 50, 200, 20);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        myButton.setBounds(30, 100, 150, 100);
        myButton.setFocusable(true);
        myButton.addActionListener(this);

        myButton1.setBounds(190, 100, 150, 100);
        myButton1.setFocusable(false);
        myButton1.addActionListener(this);

        frame.add(label);
        frame.add(myButton);
        frame.add(myButton1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Pindah Halaman Dulu");
        frame.setSize(400,500);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null); //tengahin frame


        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == myButton) {
            frame.dispose();
            MyFrame myFrame = new MyFrame();
        } else if (e.getSource() == myButton1) {
            frame.dispose();
            Game game = new Game();
        }

    }
}
