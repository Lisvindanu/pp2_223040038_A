package main.java.pertemuan8.Latihan.view.member;

import main.java.pertemuan8.Latihan.dao.MemberDao;
import main.java.pertemuan8.Latihan.model.Member;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemberButtonDeleteActionListener implements ActionListener {

    private MemberFrame memberFrame;
    private MemberDao memberDao;

    public MemberButtonDeleteActionListener(MemberFrame memberFrame, MemberDao memberDao) {
        this.memberFrame = memberFrame;
        this.memberDao = memberDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = memberFrame.getSelectedRowIndex();
        System.out.println(selectedRow);

        if(selectedRow != 1) {
            Member memberYangDipilih = memberFrame.tableModel.getMemberAt(selectedRow);
            String idDipilih = (memberYangDipilih.getId());
            memberDao.delete(idDipilih);
            memberFrame.tableModel.remove(selectedRow);
        }else {
            memberFrame.showAlert("Pilih Member Yang Ingin Dihapus");
        }
    }
}
