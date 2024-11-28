package main.java.pertemuan5.DynamicJlist;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DynamicJListExample extends JFrame  implements  ActionListener {
    private JList<String> list;
    private JScrollPane scrollPane;
    private DefaultListModel<String> listModel;
    private JButton addButton;
    private JButton removeButton;
    //data untuk list


    public DynamicJListExample() {
        setTitle("Dynamic JList Example");

        listModel = new DefaultListModel<>();
        listModel.addElement("Dynamic item 1");
        listModel.addElement("Dynamic item 2");
        listModel.addElement("Dynamic item 3");

        list= new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scrollPane = new JScrollPane(list);

        addButton = new JButton("Add item");
        addButton.setBounds(50,170,100,30);
        addButton.addActionListener(this);

        removeButton = new JButton("Remove item");
        removeButton.setBounds(160,170,120,30);
        removeButton.addActionListener(this);




      setLayout(null);
      scrollPane.setBounds(50,50,150,100);
      add(scrollPane);
      add(addButton);
      add(removeButton);

      setSize(350,250);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String aksi = (e.getSource()==addButton) ?"Tambah":(e.getSource()==removeButton) ? "Hapus" : "";

        if(aksi.equals("Tambah")) {
            String itemBaru = JOptionPane.showInputDialog(this, "Tambahkan item baru");
            if(!itemBaru.equals("") && itemBaru != null) {
                listModel.addElement(itemBaru);
            }
        } else if(aksi.equals("Hapus")) {
            int itemDipilih = list.getSelectedIndex();
            if(itemDipilih != -1) {
                listModel.remove(itemDipilih);
            } else {
                JOptionPane.showMessageDialog(this, "Kamu tidak memilih item yang ingin dihapus");
            }
        }
    }
}
