package main.java.pertemuan5.JTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class JTableExample extends JFrame {
    //bikin objek
    private DefaultTableModel tableModel;
    private JTable table;
    private JScrollPane scrollPane;
    private Object[][] data = {
            {1, "Lisvin", 20},
            {2, "Unad", 2},
            {3, "Danu", 230},
    };
    private String[] kolomNama = {"id", "nama", "umur"};

    //constructor
    public JTableExample() {
        super("JTable Example");

        tableModel = new DefaultTableModel(data, kolomNama) {
          public boolean isCellEditable(int row, int column) {
              //gabisa di edit
              return false;
          }
        };
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);

        add(scrollPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(500, 500);

    }

    public static void main(String[] args) {
        new JTableExample();
    }
}
