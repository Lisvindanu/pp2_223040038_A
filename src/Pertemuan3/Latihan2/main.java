package Pertemuan3.Latihan2;

import Pertemuan3.Latihan1.HelloBorderLayout;

import javax.swing.*;

public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new HelloGridLayout();
        });

//        HelloGridLayout layout = new HelloGridLayout();
    }
}
