package main.java.Pertemuan3.Latihan2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class HelloGridLayout extends JFrame implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel tittle_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn;


    HelloGridLayout(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(25,25,25));
        textfield.setForeground(Color.green);
        textfield.setFont(new Font("Arial",Font.BOLD,70));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("tic-tac-toe");
        textfield.setOpaque(true);

        tittle_panel.setLayout(new BorderLayout());
        tittle_panel.setBounds(0,0,800,100);

        button_panel.setLayout(new GridLayout(3,3));
        textfield.setBackground(new Color(150,150,150));

        for(int i = 0; i<9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("Arial",Font.BOLD,55));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        tittle_panel.add(textfield);
        frame.add(tittle_panel, BorderLayout.NORTH);
        frame.add(button_panel);

        langkahPertama();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0;i<9;i++) {
            if(e.getSource() == buttons[i]) {
                if(player1_turn) {
                    if(buttons[i].getText()=="") {
                        buttons[i].setForeground(Color.cyan);
                        buttons[i].setText("X");
                        player1_turn = false;
                        textfield.setText("O Jalan");
                        check();
                    }
                }else {
                    if(buttons[i].getText()=="") {
                        buttons[i].setForeground(Color.red);
                        buttons[i].setText("O");
                        player1_turn = true;
                        textfield.setText("X Jalan");
                        check();
                    }
                }
            }
        }
    }

    public void langkahPertama(){

        Timer timer = new Timer(2000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(random.nextInt(2)==0) {
                    player1_turn = true;
                    textfield.setText("X Jalan");
                } else {
                    player1_turn = false;
                    textfield.setText("O Jalan");
                }
            }
        });

        timer.setRepeats(false);
        timer.start();
    }

    public void check() {
        // check x menang
        if ((buttons[0].getText().equals("X") &&
                buttons[1].getText().equals("X") &&
                buttons[2].getText().equals("X"))) {
            xMenang(0, 1, 2);
        }
        if ((buttons[3].getText().equals("X") &&
                buttons[4].getText().equals("X") &&
                buttons[5].getText().equals("X"))) {
            xMenang(3, 4, 5);
        }
        if ((buttons[6].getText().equals("X") &&
                buttons[7].getText().equals("X") &&
                buttons[8].getText().equals("X"))) {
            xMenang(6, 7, 8);
        }
        if ((buttons[0].getText().equals("X") &&
                buttons[3].getText().equals("X") &&
                buttons[6].getText().equals("X"))) {
            xMenang(0, 3, 6);
        }
        if ((buttons[1].getText().equals("X") &&
                buttons[4].getText().equals("X") &&
                buttons[7].getText().equals("X"))) {
            xMenang(1, 4, 7);
        }
        if ((buttons[2].getText().equals("X") &&
                buttons[5].getText().equals("X") &&
                buttons[8].getText().equals("X"))) {
            xMenang(2, 5, 8);
        }
        if ((buttons[0].getText().equals("X") &&
                buttons[4].getText().equals("X") &&
                buttons[8].getText().equals("X"))) {
            xMenang(0, 4, 8);
        }

        // check o menang
        if ((buttons[0].getText().equals("O") &&
                buttons[1].getText().equals("O") &&
                buttons[2].getText().equals("O"))) {
            oMenang(0, 1, 2);
        }
        if ((buttons[3].getText().equals("O") &&
                buttons[4].getText().equals("O") &&
                buttons[5].getText().equals("O"))) {
            oMenang(3, 4, 5);
        }
        if ((buttons[6].getText().equals("O") &&
                buttons[7].getText().equals("O") &&
                buttons[8].getText().equals("O"))) {
            oMenang(6, 7, 8);
        }
        if ((buttons[0].getText().equals("O") &&
                buttons[3].getText().equals("O") &&
                buttons[6].getText().equals("O"))) {
            oMenang(0, 3, 6);
        }
        if ((buttons[1].getText().equals("O") &&
                buttons[4].getText().equals("O") &&
                buttons[7].getText().equals("O"))) {
            oMenang(1, 4, 7);
        }
        if ((buttons[2].getText().equals("O") &&
                buttons[5].getText().equals("O") &&
                buttons[8].getText().equals("O"))) {
            oMenang(2, 5, 8);
        }
        if ((buttons[0].getText().equals("O") &&
                buttons[4].getText().equals("O") &&
                buttons[8].getText().equals("O"))) {
            oMenang(0, 4, 8);
        }


        boolean isTie = true;
        for (JButton b : buttons) {
            if(b.getText().isEmpty()) {
                isTie = false;
                break;
            }
        }
        if(isTie) {
            showDialog("Game Berakhir", "Permainan berakhir seri! Apakah Anda ingin bermain lagi?");
        }
    }

    public void showDialog(String title, String message) {
        int response = JOptionPane.showConfirmDialog(frame, message, title, JOptionPane.YES_NO_OPTION);
        if(response == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }
    }

    public void resetGame() {
        for(JButton b : buttons) {
            b.setText("");
            b.setBackground(null);
            b.setEnabled(true);
        }
        langkahPertama();
    }

    public void xMenang(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        for(int i = 0; i<9; i++) {
            buttons[i].setEnabled(false);
        }
        textfield.setText("X Menang");
        showDialog("X menang!", "X Menang! apakah anda ingin bermain lagi?");
    }

    public void oMenang(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        for(int i = 0; i<9; i++) {
            buttons[i].setEnabled(false);
        }
        textfield.setText("O Menang");
        showDialog("O menang!", "O Menang! apakah anda ingin bermain lagi?");
    }
}