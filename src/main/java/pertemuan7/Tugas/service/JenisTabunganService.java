package main.java.pertemuan7.Tugas.service;

import main.java.pertemuan7.Tugas.dao.JenisTabunganDaoInterface;
import main.java.pertemuan7.Tugas.model.JenisTabungan;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class JenisTabunganService {
    private JenisTabunganDaoInterface jenisTabunganDao;

    public JenisTabunganService(JenisTabunganDaoInterface jenisTabunganDao) {
        this.jenisTabunganDao = jenisTabunganDao;
    }

    public int addJenisTabungan(JenisTabungan jenisTabungan) throws SQLException {
        return jenisTabunganDao.addJenisTabungan(jenisTabungan);
    }

    public List<JenisTabungan> getAllJenisTabungan() throws SQLException {
        return jenisTabunganDao.getAllJenisTabungan();
    }

    public Map<Integer, String> getJenisTabunganMap() throws SQLException {
      return jenisTabunganDao.getJenisTabunganMap();
    }

    public JenisTabungan getJenisTabunganById(int id) throws SQLException {
        return jenisTabunganDao.getJenisTabunganById(id);
    }

    public int updateJenisTabungan(JenisTabungan jenisTabungan) throws SQLException {
        return jenisTabunganDao.updateJenisTabungan(jenisTabungan);
    }

    public int deleteJenisTabungan(int id) throws SQLException {
        return jenisTabunganDao.deleteJenisTabungan(id);
    }
}
