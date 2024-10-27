package Pertemuan6_Tugas.Service;

import Pertemuan6_Tugas.GUI.MainFrame;
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
