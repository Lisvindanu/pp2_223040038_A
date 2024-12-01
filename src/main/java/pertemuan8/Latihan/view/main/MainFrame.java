package main.java.pertemuan8.Latihan.view.main;

import main.java.pertemuan8.Latihan.dao.JenisMemberDao;
import main.java.pertemuan8.Latihan.dao.MemberDao;
import main.java.pertemuan8.Latihan.view.jenismember.JenisMemberFrame;
import main.java.pertemuan8.Latihan.view.member.MemberFrame;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JenisMemberFrame jenisMemberFrame;
    private MemberFrame memberFrame;
    private JButton buttonJenisMember, buttonMember;
    private JenisMemberDao jenisMemberDao;
    private MemberDao memberDao;

    public MainFrame(JenisMemberDao jenisMemberDao, MemberDao memberDao) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(400,500);
        setLocationRelativeTo(null);
        this.jenisMemberDao = jenisMemberDao;
        this.memberDao = memberDao;

        this.jenisMemberFrame = new JenisMemberFrame(jenisMemberDao);
        this.memberFrame = new MemberFrame(memberDao, jenisMemberDao);

        this.setLayout(new FlowLayout());
        MainButtonActionListener actionListener = new MainButtonActionListener(this);

        this.buttonJenisMember = new JButton("Jenis Member: ");
        this.buttonMember = new JButton("Member");

        this.buttonJenisMember.addActionListener(actionListener);
        this.buttonMember.addActionListener(actionListener);

        this.add(buttonJenisMember);
        this.add(buttonMember);
    }

    public JButton getButtonJenisMember() {
        return buttonJenisMember;
    }

    public void showJenisMemberFrame() {
        if(jenisMemberFrame == null) {
            jenisMemberFrame = new JenisMemberFrame(jenisMemberDao);
        }
        jenisMemberFrame.setVisible(true);
    }

    public void showMemberFrame() {
        if(memberFrame == null) {
            memberFrame = new MemberFrame(memberDao, jenisMemberDao);
        }
        memberFrame.populateComboJenis();
        memberFrame.setVisible(true);
    }
}
