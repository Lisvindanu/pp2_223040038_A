package main.java.Pertemuan6.Tugas.Service;

import main.java.Pertemuan6.Tugas.GUI.MainFrame;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FlatArcDarkOrangeIJTheme.setup();
                new MainFrame();
            }
        });
    }
}
