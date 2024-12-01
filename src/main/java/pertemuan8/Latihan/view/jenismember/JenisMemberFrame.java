package main.java.pertemuan8.Latihan.view.jenismember;

import main.java.pertemuan8.Latihan.dao.JenisMemberDao;
import main.java.pertemuan8.Latihan.model.JenisMember;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import java.util.List;

public class JenisMemberFrame extends JFrame {
    private List<JenisMember> jenisMemberList;
    private JTextField textFieldNama;
    public JenisMemberTableModel tableModel;
    private JenisMemberDao jenisMemberDao;
    private JTable table;

    public JenisMemberFrame(JenisMemberDao jenisMemberDao) {
        // Initialize
        this.jenisMemberDao = jenisMemberDao;
        this.jenisMemberList = jenisMemberDao.findAll();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        // Initialize Components
        JLabel labelInput = new JLabel("Nama: ");
        textFieldNama = new JTextField(20);
        JButton buttonSave = new JButton("Simpan");
        JButton buttonUpdate = new JButton("Update");
        JButton buttonDelete = new JButton("Delete");
        table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);

        // Table Model
        tableModel = new JenisMemberTableModel(jenisMemberList);
        table.setModel(tableModel);

        // Action Listeners
        JenisMemberButtonSimpanActionLIstener actionLIstener = new JenisMemberButtonSimpanActionLIstener(this, jenisMemberDao);
        buttonSave.addActionListener(actionLIstener);

        JenisMemberButtonUpdateActionListener actionUpdate = new JenisMemberButtonUpdateActionListener(this, jenisMemberDao);
        buttonUpdate.addActionListener(actionUpdate);

        JenisMemberButtonDeleteActionListener actionDelete = new JenisMemberButtonDeleteActionListener(this, jenisMemberDao);
        buttonDelete.addActionListener(actionDelete);

        // Set Layout
        setLayout(new MigLayout("", "[][grow]", "[][][][][][]"));

        // Add components with layout constraints
        add(labelInput);
        add(textFieldNama, "growx, wrap");

        add(buttonSave, "split 3, sizegroup buttons");
        add(buttonUpdate);
        add(buttonDelete, "wrap");

        add(scrollableTable, "span 2, growx, growy");

        // Set frame size
        setSize(400, 500);
    }

    public String getNama() {
        return textFieldNama.getText();
    }

    public int getSelectedRowIndex() {
        return table.getSelectedRow();
    }

    public void addJenisMember(JenisMember jenisMember) {
        tableModel.add(jenisMember);
        textFieldNama.setText("");
    }

    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
