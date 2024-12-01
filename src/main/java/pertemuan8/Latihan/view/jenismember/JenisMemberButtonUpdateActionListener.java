package main.java.pertemuan8.Latihan.view.jenismember;

import main.java.pertemuan8.Latihan.dao.JenisMemberDao;
import main.java.pertemuan8.Latihan.model.JenisMember;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JenisMemberButtonUpdateActionListener implements ActionListener {
    private JenisMemberFrame jenisMemberFrame;
    private JenisMemberDao jenisMemberDao;

    public JenisMemberButtonUpdateActionListener(JenisMemberFrame jenisMemberFrame, JenisMemberDao jenisMemberDao) {
        this.jenisMemberFrame = jenisMemberFrame;
        this.jenisMemberDao = jenisMemberDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int RowYangDipilih = jenisMemberFrame.getSelectedRowIndex();
        System.out.println(RowYangDipilih);

        if(RowYangDipilih != -1){
            JenisMember jenisMemberYangDipilih = jenisMemberFrame.tableModel.getJenisMemberAt(RowYangDipilih);
            String namaBaru = jenisMemberFrame.getNama();

            if(namaBaru.isEmpty()) {
                jenisMemberFrame.showAlert("Nama Jenis Member Tidak Boleh Kosong");
            } else {
                jenisMemberYangDipilih.setNama(namaBaru);
                jenisMemberDao.update(jenisMemberYangDipilih);
                jenisMemberFrame.tableModel.fireTableDataChanged();
            }
        }else {
            jenisMemberFrame.showAlert("Pilih Jenis Member Yang Ingin Di Ubah");
        }
        JOptionPane.showMessageDialog(jenisMemberFrame, "Data changed successfully!");
    }
}
