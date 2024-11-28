package main.java.Pertemuan4.Button;

import javax.swing.*;

public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ButtonExample();
            }
        });
    }
}
