package main.java.pertemuan8.Latihan.view.member;

import main.java.pertemuan8.Latihan.dao.JenisMemberDao;
import main.java.pertemuan8.Latihan.dao.MemberDao;
import main.java.pertemuan8.Latihan.model.JenisMember;
import main.java.pertemuan8.Latihan.model.Member;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.util.List;

public class MemberFrame extends JFrame {
    private List<JenisMember> jenisMemberList;
    private List<Member> memberList;
    private JTextField textFieldNama;
    MemberTableModel tableModel;
    private JComboBox<String> comboJenis;
    private MemberDao memberDao;
    private JenisMemberDao jenisMemberDao;
    private JTable table;

    public MemberFrame(MemberDao memberDao, JenisMemberDao jenisMemberDao) {
        // Initialize frame
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.memberDao = memberDao;
        this.jenisMemberDao = jenisMemberDao;
        this.memberList = this.memberDao.findAll();
        this.jenisMemberList = this.jenisMemberDao.findAll();
        setLocationRelativeTo(null);


        // Initialize components
        JLabel labelInput = new JLabel("Nama: ");
        textFieldNama = new JTextField();
        JLabel labelJenis = new JLabel("Jenis Member: ");
        comboJenis = new JComboBox<>();
        JButton button = new JButton("Simpan");
        JButton buttonUpdate = new JButton("Update");
        JButton buttonDelete = new JButton("Delete");
        table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);

        // Table Model
        tableModel = new MemberTableModel(memberList);
        table.setModel(tableModel);

        // Action listeners
        MemberButtonSimpanActionListener actionListener = new MemberButtonSimpanActionListener(this, memberDao);
        button.addActionListener(actionListener);

        MemberButtonUpdateActionListener actionListenerUpdate = new MemberButtonUpdateActionListener(this, memberDao);
        buttonUpdate.addActionListener(actionListenerUpdate);

        MemberButtonDeleteActionListener actionListenerDelete = new MemberButtonDeleteActionListener(this, memberDao);
        buttonDelete.addActionListener(actionListenerDelete);



        // Populate combo box
        populateComboJenis();

        // Set layout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        // Auto-generate gaps between components
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Horizontal Group
        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(labelInput)
                                        .addComponent(labelJenis)
                                        .addComponent(button))
                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(textFieldNama, 150, 150, 150)
                                        .addComponent(comboJenis, 150, 150, 150)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(buttonUpdate, 100, 100, 100)
                                                .addComponent(buttonDelete, 100, 100, 100))))
                        .addComponent(scrollableTable)
        );

        // Vertical Group
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                .addComponent(labelInput)
                                .addComponent(textFieldNama))
                        .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                .addComponent(labelJenis)
                                .addComponent(comboJenis))
                        .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                .addComponent(button)
                                .addComponent(buttonUpdate)
                                .addComponent(buttonDelete))
                        .addComponent(scrollableTable)
        );

        // Set frame size
        setSize(400, 500);
    }

    public void populateComboJenis() {
        this.jenisMemberList = this.jenisMemberDao.findAll();
        comboJenis.removeAllItems();
        for (JenisMember jenisMember : this.jenisMemberList) {
            comboJenis.addItem(jenisMember.getNama());
        }
    }

    public String getNama() {
        return textFieldNama.getText();
    }

    public JenisMember getJenisMember() {
        return jenisMemberList.get(comboJenis.getSelectedIndex());
    }

    public void addMember(Member member) {
        tableModel.add(member);
        textFieldNama.setText("");
    }

    public int getSelectedRowIndex() {
        return table.getSelectedRow();
    }

    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
