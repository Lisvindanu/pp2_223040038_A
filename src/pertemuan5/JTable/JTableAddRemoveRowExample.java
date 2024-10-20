package pertemuan5.JTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JTableAddRemoveRowExample extends JFrame implements ActionListener {


    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;
    private String[] NamaKolom = {"Id", "Nama", "Umur"};
   private JButton addButton;
   private JButton removeButton;

    public JTableAddRemoveRowExample() {
        super("Add/Remove Row Example");

        tableModel = new DefaultTableModel(NamaKolom, 0); // model denagn 0 baris awal
        table = new JTable(tableModel); // panggil objek table dengan mereferensikan tableModel sebagai data
        addButton = new JButton("Tambah Data");
        addButton.setBounds(50,220,100,30);
        addButton.addActionListener(this);


        removeButton = new JButton("Hapus Baris");
        removeButton.setBounds(160,220,120,30);
        removeButton.addActionListener(this);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20,20,300,180);

        setLayout(null);
        add(scrollPane);
        add(addButton);
        add(removeButton);

        setSize(400,400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String opsi = (e.getSource() == addButton) ? "TambahData" : (e.getSource() == removeButton) ? "hapus" : "" ;

        if(opsi.equals("TambahData")) {
            //input nama dan umur
           String nama = JOptionPane.showInputDialog(this, "Masukkan Nama : ");
           if(!nama.equals("") && nama != null) {
               try{
                   int umur = Integer.parseInt(JOptionPane.showInputDialog(this, "Masukkan Umur : "));
                   Object[] BarisBaru = {
                           tableModel.getRowCount() + 1, nama, umur
                   };
                   tableModel.addRow(BarisBaru);
               } catch(Exception ex){
                   JOptionPane.showMessageDialog(this, "Kolom Harus Diisi dengan angka");
               }
           }
        } else if(opsi.equals("hapus")) {
            int pilihan = table.getSelectedRow();
            if(pilihan != -1) {
                tableModel.removeRow(pilihan);
            } else {
                JOptionPane.showMessageDialog(this, "Kamu tidak memilih baris yang ingin dihapus");
            }
        }
    }

    public static void main(String[] args) {
         new JTableAddRemoveRowExample();
    }
}
