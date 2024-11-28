package main.java.Pertemuan3.Latihan1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelloBorderLayout extends JFrame implements ActionListener {


    private JLabel labelpertanyaan;
    private JLabel labelHasil;
    private JButton buttonA;
    private JButton buttonB;
    private JButton buttonC;
    public HelloBorderLayout() {



        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        labelpertanyaan = new JLabel("Apa nama ibukota Indonesia?");
        labelHasil = new JLabel("Jawab pertanyaan di atas");

        buttonA = new JButton("Jakarta");
        buttonB = new JButton("Bandung");
        buttonC = new JButton("Surabaya");

        buttonA.addActionListener(this);
        buttonB.addActionListener(this);
        buttonC.addActionListener(this);


        this.setLayout(new BorderLayout(5, 5)); //h and v margin

        this.add(labelpertanyaan, BorderLayout.NORTH);
        this.add(labelHasil, BorderLayout.SOUTH);
        this.add(buttonA, BorderLayout.WEST);
        this.add(buttonB, BorderLayout.CENTER);
        this.add(buttonC, BorderLayout.EAST);
        this.setSize(420, 420);
//        this.pack();
        this.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonA) {
            labelHasil.setText("Jawaban anda benar");
        } else {
            labelHasil.setText("Jawaban anda salah");
        }
    }


}
