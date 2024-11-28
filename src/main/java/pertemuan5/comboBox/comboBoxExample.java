package main.java.pertemuan5.comboBox;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class comboBoxExample extends JFrame implements ActionListener {
    private JComboBox<String> comboBox;
    private String[] options = {
            "pilihan 1",
            "pilihan 2",
            "pilihan 3",
            "pilihan 4",
    };

    public comboBoxExample () {
      super("Combo Box");
        comboBox = new JComboBox<>(options);
        comboBox.addActionListener(this);

      setLayout(null);
        comboBox.setBounds(50,50,150,20);
      add(comboBox);

       setSize(300,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedItem = (String) comboBox.getSelectedItem();
        System.out.println("pilihan yang dipilih : " + selectedItem);
    }
}
