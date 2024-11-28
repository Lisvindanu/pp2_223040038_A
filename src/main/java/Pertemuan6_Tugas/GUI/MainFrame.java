package main.java.Pertemuan6_Tugas.GUI;

import com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainFrame extends JFrame {
    private MenuBars menuBars;
    private FormPanel formPanel;
    private JPanel mainPanel;
    private JPanel loadPanel;
    public MainFrame() {
        setTitle("MainFrame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new MigLayout("wrap 1", "[grow]", "[grow]"));

        String[] columnNames = {"Nama", "No HP", "Jenis Kelamin", "WNA",
                "Jenis Tabungan", "Frekuensi Transaksi",
                "Tanggal Lahir", "Pekerjaan", "Deskripsi"};

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        System.out.println("Model identity in MainFrame: " + System.identityHashCode(tableModel));
//
       formPanel = new FormPanel(tableModel);
       mainPanel = new JPanel(new MigLayout("wrap 1", "[grow]", "[grow]"));

        loadPanel = createLoadPanel();
        mainPanel.add(loadPanel, "grow");

         menuBars = new MenuBars(this,mainPanel, formPanel, tableModel);
        setJMenuBar(menuBars.dapetinMenuBar());


        add(mainPanel, "grow");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        showMainMenu();
    }

    public JPanel createLoadPanel() {
        JPanel loadPanel = new JPanel(new MigLayout("wrap 1", "[grow]", "[][]"));
        JLabel imageLabel = new JLabel(new ImageIcon("D:\\Kuliah Sem5\\Praktikum Pemrograman 2\\PP2\\src\\Pertemuan4\\MouseListener\\img\\Nokotan1.jpg"));
        loadPanel.add(imageLabel, "align center, wrap");
        JLabel mainMenuLabel = new JLabel("Main Menu"); // Label baru untuk teks "Main Menu"
        mainMenuLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Mengatur font jika perlu
        loadPanel.add(mainMenuLabel, "align center, wrap");
        return loadPanel;
    }

    public void showMainMenu() {
        menuBars.setSaveLoadVisible(false);
        menuBars.setVisibility(false);
        menuBars.getFormItem().setVisible(true);
        mainPanel.removeAll();
        mainPanel.add(loadPanel, "grow");
        System.out.println("Showing Main Menu, Load and Save should be hidden, Form should be hidden");
        revalidate();
        repaint();
    }

    public void showFormPanel() {
        menuBars.setSaveLoadVisible(true);
        menuBars.setVisibility(true);
        menuBars.getFormItem().setVisible(false);
        mainPanel.remove(loadPanel);
        System.out.println("Showing Form Panel, Load and Save should be visible, Form should be visible");
        revalidate();
        repaint();
    }



    public static void main(String[] args) {
        // Setup tema menggunakan FlatLaf
        FlatArcDarkOrangeIJTheme.setup();


        // Jalankan GUI di Event Dispatch Thread
        SwingUtilities.invokeLater(MainFrame::new);
    }
}