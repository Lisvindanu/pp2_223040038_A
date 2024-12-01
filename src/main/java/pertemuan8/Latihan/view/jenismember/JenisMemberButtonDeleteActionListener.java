package main.java.pertemuan8.Latihan.view.jenismember;

import main.java.pertemuan8.Latihan.dao.JenisMemberDao;
import main.java.pertemuan8.Latihan.model.JenisMember;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JenisMemberButtonDeleteActionListener implements ActionListener {
    private JenisMemberFrame jenisMemberFrame;
    private JenisMemberDao jenisMemberDao;

    public JenisMemberButtonDeleteActionListener(JenisMemberFrame jenisMemberFrame,
                                                 JenisMemberDao jenisMemberDao) {
        this.jenisMemberFrame = jenisMemberFrame;
        this.jenisMemberDao = jenisMemberDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = jenisMemberFrame.getSelectedRowIndex();
        System.out.println("Selected Row: " + selectedRow); // Debugging line

        if (selectedRow != -1) {
            JenisMember jenisMemberYangDipilih = jenisMemberFrame.tableModel.getJenisMemberAt(selectedRow);
            String idDiPilih = (jenisMemberYangDipilih.getId());
            jenisMemberDao.delete(idDiPilih);
            jenisMemberFrame.tableModel.remove(selectedRow);
        } else {
            jenisMemberFrame.showAlert("Pilih jenis member yang ingin dihapus");
        }
        JOptionPane.showMessageDialog(jenisMemberFrame, "Data deleted successfully!");
    }

}
