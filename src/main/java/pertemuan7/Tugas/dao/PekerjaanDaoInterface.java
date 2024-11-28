package main.java.pertemuan7.Tugas.dao;

import main.java.pertemuan7.Tugas.model.Pekerjaan;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface PekerjaanDaoInterface {
    int addPekerjaan(Pekerjaan pekerjaan) throws SQLException;
    List<Pekerjaan> getSemuaPekerjaan()  throws SQLException;
    Map<Integer, String> getPekerjaanMap() throws SQLException;
    Pekerjaan getPekerjaanById(int id) throws SQLException;
    int updatePekerjaan(Pekerjaan pekerjaan) throws SQLException;
    int deletePekerjaan(int id) throws SQLException;
}
