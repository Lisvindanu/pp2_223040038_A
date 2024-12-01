package main.java.pertemuan8.Latihan.view.member;

import main.java.pertemuan8.Latihan.dao.MemberDao;
import main.java.pertemuan8.Latihan.model.JenisMember;
import main.java.pertemuan8.Latihan.model.Member;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemberButtonUpdateActionListener implements ActionListener {
    private MemberFrame memberFrame;
    private MemberDao memberDao;

    public MemberButtonUpdateActionListener(MemberFrame memberFrame, MemberDao memberDao) {
        this.memberFrame = memberFrame;
        this.memberDao = memberDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int RowYangDipilih = memberFrame.getSelectedRowIndex();
        System.out.println(RowYangDipilih);

        if(RowYangDipilih != -1){
            Member memberYangDipilih = memberFrame.tableModel.getMemberAt(RowYangDipilih);
            String namaBaru = memberFrame.getNama();

            if(namaBaru.isEmpty()){
                memberFrame.showAlert("Nama Member Tidak Boleh Kosong!!!");
            }else {
                JenisMember jenisMember = this.memberFrame.getJenisMember();
                memberYangDipilih.setNama(namaBaru);
                memberYangDipilih.setJenisMember(jenisMember);
                memberYangDipilih.setJenisMemberId(jenisMember.getId());
                memberDao.update(memberYangDipilih);
                memberFrame.tableModel.fireTableDataChanged();
            }
        }else {
            memberFrame.showAlert("Pilih Member Yang Ingin Di Ubah");
        }
    }
}
