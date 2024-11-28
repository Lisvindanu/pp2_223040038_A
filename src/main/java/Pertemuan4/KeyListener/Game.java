package main.java.Pertemuan4.KeyListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

public class Game extends JFrame implements KeyListener {
    private JLabel label;
    public Game() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        setLayout(null);


        label = new JLabel();
        label.setBounds(0,0,100,100);

        this.getContentPane().setBackground(Color.blue);

        try {
            URL labelIconUrl = new URL("https://img.icons8.com/ios/50/000000/rocket.png");
            ImageIcon labelIcon = new ImageIcon(labelIconUrl);
            label.setIcon(labelIcon);
        }catch (Exception e) {
            e.printStackTrace();
        }
        this.add(label);
        setVisible(true);
        addKeyListener(this);
        setLocationRelativeTo(null);

    }

    @Override
    public void keyTyped(KeyEvent e) {
     char keyChar = e.getKeyChar();
     switch (keyChar) {
        case 'w' -> label.setLocation(label.getX() ,label.getY()-10);

         case 'a'->label.setLocation(label.getX()-10 ,label.getY());

         case 's'->label.setLocation(label.getX() ,label.getY()+10);

         case 'd'->label.setLocation(label.getX()+10 ,label.getY());
         default -> {break;}

     }
    }

    @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case 38-> label.setLocation(label.getX() ,label.getY()-10);

                case 37->label.setLocation(label.getX()-10 ,label.getY());

                case 40->label.setLocation(label.getX() ,label.getY()+10);

                case 39->label.setLocation(label.getX()+10 ,label.getY());
                default -> {break;}
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("You released  key char: " + e.getKeyChar());
        System.out.println("You released  key char: " +e.getKeyCode());
    }
}
