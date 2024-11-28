package main.java.pertemuan7.Tugas.dao;

import main.java.pertemuan7.Tugas.model.JenisTabungan;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface JenisTabunganDaoInterface {
    int addJenisTabungan(JenisTabungan jenisTabungan) throws SQLException;
    List<JenisTabungan> getAllJenisTabungan() throws SQLException;
    Map<Integer, String> getJenisTabunganMap() throws SQLException;
    JenisTabungan getJenisTabunganById(int id)  throws SQLException;
    int updateJenisTabungan(JenisTabungan jenisTabungan) throws SQLException;
    int deleteJenisTabungan(int id) throws SQLException;
}
