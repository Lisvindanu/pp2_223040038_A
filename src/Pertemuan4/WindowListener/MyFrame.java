package Pertemuan4.WindowListener;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MyFrame implements WindowListener {
    private JFrame frame;
    private JLabel label;


    public MyFrame() {
        frame = new JFrame("WindowsListener Example"); // membuat frame baru
        label = new JLabel("Lakukan Operasi Pada Jendela"); //membuat label untuk menampilkan pesan
        label.setBounds(50,50,300,30); //mengatur ukuran label
        frame.addWindowListener(this); //membuat frame terhubung dengan action listener
        frame.add(label); // menambahkan label kedalam frame

        //mengatur frame
        frame.setSize(400,200);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// agar jendela benar benar tertutup

    }


    //dijalankan ketika windows terbuka
        @Override
        public void windowOpened(WindowEvent e) {
            label.setText("Window Opened");
        }
    //dijalankan ketika windows dalam proses penutupan
        @Override
        public void windowClosing(WindowEvent e) {
            label.setText("Window Closed");
            System.exit(0);
        }

    //dijalannkan ketika windows benar benar ditutup
    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("Window Closed");
        System.out.println("di Closed");

    }

    // dijalankan ketika windows di minimalkan
    @Override
    public void windowIconified(WindowEvent e) {
        System.out.println("Window Minimized");
    }

    // dijalankan ketika window dipulihkan dari kondisi => diminimalkan
    @Override
    public void windowDeiconified(WindowEvent e) {
        label.setText("Window Restored");
    }
//dijalankan ketika windows sedang aktif // fokus
    @Override
    public void windowActivated(WindowEvent e) {
        label.setText("Window Activated");
    }
//dijalankan ketika windows kehilangan fokus
    @Override
    public void windowDeactivated(WindowEvent e) {
        label.setText("Window Deactivated");
    }

}
