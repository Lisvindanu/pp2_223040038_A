package Pertemuan6_Tugas.GUI;

import Pertemuan6_Tugas.Service.TableServices;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("MainFrame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new MigLayout("wrap 1", "[grow]", "[grow]"));


       JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new MigLayout("wrap 1", "[grow]", "[grow]"));

        FormPanel formPanel = new FormPanel();
        TableServices tableServices = new TableServices(formPanel.getTableModel(), formPanel);

        MenuBars menuBars = new MenuBars(mainPanel, formPanel, tableServices);
        setJMenuBar(menuBars.dapetinMenuBar());

        JLabel imageLabel = new JLabel(new ImageIcon(("D:\\Kuliah Sem5\\Praktikum Pemrograman 2\\PP2\\src\\Pertemuan4\\MouseListener\\img\\Nokotan1.jpg")));
        mainPanel.add(imageLabel, "align center");

        JButton button = new JButton("Tombol Baru");
        mainPanel.add(button, "align center");

        // Tambahkan mainPanel ke frame
        add(mainPanel, "grow");

        // Atur ukuran dan posisi frame
        setSize(600, 400); // Ukuran frame
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Setup tema menggunakan FlatLaf
        FlatArcDarkOrangeIJTheme.setup();

        // Jalankan GUI di Event Dispatch Thread
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
