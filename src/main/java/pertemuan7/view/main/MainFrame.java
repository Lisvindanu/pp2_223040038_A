package main.java.pertemuan7.view.main;

import main.java.pertemuan7.dao.JenisMemberDao;
import main.java.pertemuan7.dao.MemberDao;
import main.java.pertemuan7.view.jenismember.JenisMemberFrame;
import main.java.pertemuan7.view.member.MemberFrame;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JenisMemberFrame jenisMemberFrame;
    public MemberFrame memberFrame;
    private JButton buttonJenisMember;
    private JButton buttonMember;
    private JenisMemberDao jenisMemberDao;
    private MemberDao memberDao;

    public MainFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,500);
        this.jenisMemberDao = new JenisMemberDao();
        this.memberDao = new MemberDao();

        this.jenisMemberFrame = new JenisMemberFrame(jenisMemberDao);
        this.memberFrame = new MemberFrame(memberDao, jenisMemberDao);

        this.setLayout(new FlowLayout());
        MainButtonActionListener actionListener = new MainButtonActionListener(this);

        this.buttonJenisMember = new JButton("Jenis Member");
        this.buttonMember = new JButton("Member");

        this.buttonJenisMember.addActionListener(actionListener);
        this.buttonMember.addActionListener(actionListener);

        add(buttonJenisMember);
        add(buttonMember);
    }

    public JButton getButtonJenisMember() {
        return buttonJenisMember;
    }
    public JButton getButtonMember() {
        return buttonMember;
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

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
            }
        });
    }
}
