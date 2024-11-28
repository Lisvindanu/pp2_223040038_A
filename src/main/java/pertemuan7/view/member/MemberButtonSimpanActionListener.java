package main.java.pertemuan7.view.member;

import main.java.pertemuan7.dao.MemberDao;
import main.java.pertemuan7.model.JenisMember;
import main.java.pertemuan7.model.Member;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemberButtonSimpanActionListener implements ActionListener {
     private MemberFrame memberFrame;
     private MemberDao memberDao;

     public MemberButtonSimpanActionListener(MemberFrame memberFrame, MemberDao memberDao) {
         this.memberFrame = memberFrame;
         this.memberDao = memberDao;
     }


    @Override
    public void actionPerformed(ActionEvent e) {
        String nama = this.memberFrame.getNama();
        if(nama.isEmpty()) {
            this.memberFrame.showAlert("Nama tidak boleh kosong");
        } else {
            JenisMember jenisMember = this.memberFrame.getJenisMember();
            Member member = new Member();
            member.setNama(nama);
            member.setJenisMember(jenisMember);

            this.memberFrame.addMember(member);
            this.memberDao.insert(member);
        }
    }
}
