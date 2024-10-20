package pertemuan5.jList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JListExample extends JFrame implements ListSelectionListener {
    private JList<String> list;
    private JScrollPane scrollPane;

    //data untuk list
   private String[] items = {"item 1", "item 2", "item 3", "item 4", "item 5"};

    public JListExample() {
        this.setTitle("JList Example");

        list = new JList<>(items);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);
        scrollPane = new JScrollPane(list);





        scrollPane.setBounds(50,50,150,100);
        this.add(scrollPane);

        this.setLayout(null);
        this.setSize(300,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);


    }


    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()) {
            String selectedItem = list.getSelectedValue();
            System.out.println("Selected " + selectedItem);
        }
    }
}
