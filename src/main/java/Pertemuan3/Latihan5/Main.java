package main.java.Pertemuan3.Latihan5;

import main.java.Pertemuan3.Latihan5.GUI.MFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
     SwingUtilities.invokeLater(() ->{
        new MFrame().setVisible(true);
     });
    }
}
