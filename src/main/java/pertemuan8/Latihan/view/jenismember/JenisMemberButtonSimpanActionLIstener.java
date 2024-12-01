package main.java.pertemuan8.Latihan.view.jenismember;

import main.java.pertemuan8.Latihan.model.JenisMember;
import main.java.pertemuan8.Latihan.dao.JenisMemberDao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class JenisMemberButtonSimpanActionLIstener implements ActionListener {
    private JenisMemberFrame jenisMemberFrame;
    private JenisMemberDao jenisMemberDao;

    public JenisMemberButtonSimpanActionLIstener(JenisMemberFrame jenisMemberFrame,
                                                 JenisMemberDao jenisMemberDao) {
        this.jenisMemberFrame = jenisMemberFrame;
        this.jenisMemberDao = jenisMemberDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nama = this.jenisMemberFrame.getNama();
        if (nama.isEmpty()) {
            this.jenisMemberFrame.showAlert("Jenis Member tidak boleh kosong");
        } else {
            JenisMember jenismember = new JenisMember();
            jenismember.setId(UUID.randomUUID().toString());
            jenismember.setNama(nama);

            this.jenisMemberFrame.addJenisMember(jenismember);
            this.jenisMemberDao.insert(jenismember);
        }
        JOptionPane.showMessageDialog(jenisMemberFrame, "Data added successfully!");
    }

}
