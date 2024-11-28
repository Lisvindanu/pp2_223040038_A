package main.java.pertemuan7.view.jenismember;

import main.java.pertemuan7.dao.JenisMemberDao;
import main.java.pertemuan7.model.JenisMember;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class JenisMemberButtonSimpanActionListener implements ActionListener {
    private JenisMemberFrame jenisMemberFrame;
    private JenisMemberDao jenisMemberDao;

    public JenisMemberButtonSimpanActionListener(JenisMemberFrame jenisMemberFrame, JenisMemberDao jenisMemberDao) {
        this.jenisMemberFrame = jenisMemberFrame;
        this.jenisMemberDao = jenisMemberDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nama = this.jenisMemberFrame.getNama();
        if(nama == null | nama.trim().equals("")) {
          this.jenisMemberFrame.showAlert("nama jenis member gaboleh kosong");
        }
        JenisMember jenisMember = new JenisMember();
        jenisMember.setId(UUID.randomUUID().toString());
        jenisMember.setNama(nama);
        System.out.println("Inserting JenisMember: ID: " + jenisMember.getId() + ", Nama: " + jenisMember.getNama());

        this.jenisMemberFrame.addJenisMember(jenisMember);
        this.jenisMemberDao.insert(jenisMember);
    }
}
