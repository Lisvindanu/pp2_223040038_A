package main.java.pertemuan7.Tugas.service;

import main.java.pertemuan7.Tugas.dao.PekerjaanDaoInterface;
import main.java.pertemuan7.Tugas.model.Pekerjaan;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class PekerjaanService {
    private PekerjaanDaoInterface pekerjaanDao;

    public PekerjaanService(PekerjaanDaoInterface pekerjaanDao) {
        this.pekerjaanDao = pekerjaanDao;
    }

    public int addPekerjaan(Pekerjaan pekerjaan) throws SQLException {
        return pekerjaanDao.addPekerjaan(pekerjaan);
    }

    public List<Pekerjaan> getAllPekerjaan() throws SQLException {
        return pekerjaanDao.getSemuaPekerjaan();
    }

    public Map<Integer, String> getPekerjaanMap() throws SQLException {
        return pekerjaanDao.getPekerjaanMap();
    }

    public Pekerjaan getPekerjaanById(int id) throws SQLException {
        return pekerjaanDao.getPekerjaanById(id);
    }

    public int updatePekerjaan(Pekerjaan pekerjaan) throws SQLException {
        return pekerjaanDao.updatePekerjaan(pekerjaan);
    }

    public int deletePekerjaan(int id) throws SQLException {
        return pekerjaanDao.deletePekerjaan(id);
    }
}
