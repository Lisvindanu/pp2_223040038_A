package pertemuan5.JTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class JTableEditableExample extends JFrame {
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
    public JTableEditableExample() {
        super("JTable Example");

        tableModel = new DefaultTableModel(data, kolomNama) {
            public boolean isCellEditable(int row, int column) {
                //hanya kolom nama yang bisa di edit
                //index java dimulai dari 0
                return column == 1;
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
        new JTableEditableExample();
    }
}

