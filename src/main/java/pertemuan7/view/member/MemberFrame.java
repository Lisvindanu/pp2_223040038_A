package main.java.pertemuan7.view.member;

import main.java.pertemuan7.dao.JenisMemberDao;
import main.java.pertemuan7.dao.MemberDao;
import main.java.pertemuan7.model.JenisMember;
import main.java.pertemuan7.model.Member;

import javax.swing.*;
import java.util.List;

public class MemberFrame extends JFrame {
    private List<JenisMember> jenisMemberList;
    private List<Member> memberList;
    private JTextField texfieldNama;
    private MemberTableModel tableModel;
    private JComboBox comboJenis;
    private MemberDao memberDao;
    private JenisMemberDao jenisMemberDao;

    public MemberFrame(MemberDao memberDao, JenisMemberDao jenisMemberDao) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.memberDao = memberDao;
        this.jenisMemberDao = jenisMemberDao;

        this.memberList = this.memberDao.findAll();
        this.jenisMemberList = this.jenisMemberDao.findAll();

        JLabel labelInput = new JLabel("Nama : ");
        labelInput.setBounds(15, 40,350,10);

        texfieldNama = new JTextField(15);
        texfieldNama.setBounds(15, 60,350,30);

        JLabel labelJenis = new JLabel("Jenis Member : ");
        labelJenis.setBounds(15, 100,350,10);

        comboJenis = new JComboBox();
        comboJenis.setBounds(15, 120,350,30);

        JButton buttonSave = new JButton("Save");
        buttonSave.setBounds(15, 160,100,40);

        javax.swing.JTable table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 220,350,200);

        tableModel = new MemberTableModel(memberList);
        table.setModel(tableModel);

        MemberButtonSimpanActionListener actionListener = new MemberButtonSimpanActionListener(this, memberDao);
        buttonSave.addActionListener(actionListener);
        add(buttonSave);
        add(texfieldNama);
        add(labelInput);
        add(labelJenis);
        add(comboJenis);
        add(scrollableTable);
        setSize(400,500);
        setLayout(null);

    }

    public void populateComboJenis() {
        this.jenisMemberList = this.jenisMemberDao.findAll();
        comboJenis.removeAllItems();
        for(JenisMember jenisMember : this.jenisMemberList) {
            comboJenis.addItem(jenisMember.getNama());
        }
    }

    public String getNama() {
        return texfieldNama.getText();
    }
    public JenisMember getJenisMember() {
        return jenisMemberList.get(comboJenis.getSelectedIndex());
    }

    public void addMember(Member member) {
        tableModel.add(member);
        texfieldNama.setText("");
    }

    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
