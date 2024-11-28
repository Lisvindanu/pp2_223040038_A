package main.java.pertemuan8.view.jenismember;

import main.java.pertemuan8.model.JenisMember;
import main.java.pertemuan8.dao.JenisMemberDao;

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
            this.jenisMemberFrame.showAlert("Jenis Member gaboleh kosong");
        } else {
            JenisMember jenismember = new JenisMember();
            jenismember.setId(UUID.randomUUID().toString());
            jenismember.setNama(nama);

            this.jenisMemberFrame.addJenisMember(jenismember);
            this.jenisMemberDao.insert(jenismember);
        }

    }
}
