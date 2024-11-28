package main.java.Pertemuan4.KeyListener;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyFrame extends JFrame implements KeyListener {
    JLabel label;
    JTextField textField;

    public MyFrame() {
        setTitle("KeyListener Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // menampilkan label untuk menampilkan pesan
        label = new JLabel("Tekan Tombol Pada Keyboard");
        label.setBounds(50,50,300,30);
        // membuat text field untuk fokus keyboard
        textField = new JTextField();
        textField.setBounds(50,100,200,30);
        textField.addKeyListener(this);
        add (label);
        add (textField);
        setSize(400,200);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);

    }
    @Override
    public void keyTyped(KeyEvent e) {
            label.setText("Key Typed : " + e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        label.setText("Key Pressed:  " + KeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        label.setText("Key Released:  " + KeyEvent.getKeyText(e.getKeyCode()));
    }


}
