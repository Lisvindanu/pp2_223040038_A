package pertemuan5.comboBoxModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComboBoxModelExample extends JFrame implements ActionListener {

    private DefaultComboBoxModel<String> comboBoxModel;
    private JComboBox<String> comboBox;


    public ComboBoxModelExample() {
        super("ComboBoxModel Example");


        comboBoxModel = new DefaultComboBoxModel<>();
        comboBoxModel.addElement("item 1");
        comboBoxModel.addElement("item 2");
        comboBoxModel.addElement("item 3");
        comboBoxModel.addElement("item 4");

        comboBox = new JComboBox<String> (comboBoxModel);

        comboBox.addActionListener(this);

        setLayout(null);
        comboBox.setBounds(50,50,150,20);
        add(comboBox);

        setSize(300,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String itemDipilih = (String) comboBox.getSelectedItem();
        System.out.println("item yang dipilih : " + itemDipilih);
    }
}
