package main.java.pertemuan7.Tugas.dao;

import main.java.pertemuan7.Tugas.model.JenisTabungan;
import main.java.pertemuan7.Tugas.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JenisTabunganDao implements JenisTabunganDaoInterface {
    @Override
    public int addJenisTabungan(JenisTabungan jenisTabungan) throws SQLException {
        {
            String query = "INSERT INTO jenis_tabungan (label) VALUES(?)";
            int res = -1;
            try(Connection conn = DatabaseConnection.getInstance().getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, jenisTabungan.getLabel());
                res = stmt.executeUpdate();

            }catch (SQLException e) {
                e.printStackTrace();
                res = 0;
            }
            return res;
        }
    }

    @Override
    public List<JenisTabungan> getAllJenisTabungan() throws SQLException {
        String query = "SELECT * FROM jenis_tabungan ORDER BY id DESC";
        List<JenisTabungan> jenisTabunganList = new ArrayList<>();
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                JenisTabungan jenisTabungan = new JenisTabungan(
                        rs.getInt("id"),
                        rs.getString("label")
                );
                jenisTabunganList.add(jenisTabungan);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return jenisTabunganList;
    }

    @Override
    public Map<Integer, String> getJenisTabunganMap() throws SQLException {
        Map<Integer, String> jenisTabunganMap = new HashMap<>();
        String query = "Select id, label from jenis_tabungan";
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                jenisTabunganMap.put(rs.getInt("id"), rs.getString("label"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return jenisTabunganMap;
    }

    @Override
    public JenisTabungan getJenisTabunganById(int id) throws SQLException {
        return null;
    }

    @Override
    public int updateJenisTabungan(JenisTabungan jenisTabungan) throws SQLException{
        String query = "UPDATE jenis_tabungan SET label = ? WHERE id = ?";
        int res = -1;
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, jenisTabungan.getLabel());
            stmt.setInt(2, jenisTabungan.getId());
            res = stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            res = 0;
        }
        return res;
    }

    @Override
    public int deleteJenisTabungan(int id) throws SQLException {
        String query = "DELETE FROM jenis_tabungan WHERE id = ?";
        int res = -1;
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            res = stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
