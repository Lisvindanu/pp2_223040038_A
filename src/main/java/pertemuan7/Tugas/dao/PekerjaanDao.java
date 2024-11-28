package main.java.pertemuan7.Tugas.dao;

import main.java.pertemuan7.Tugas.model.Pekerjaan;
import main.java.pertemuan7.Tugas.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PekerjaanDao implements PekerjaanDaoInterface{
    @Override
    public int addPekerjaan(Pekerjaan pekerjaan) throws SQLException {
        String query = "INSERT INTO pekerjaan (label) VALUES(?)";
        int res = -1;
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, pekerjaan.getLabel());
           res = stmt.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
            res = 0;
        }
        return res;
    }

    @Override
    public List<Pekerjaan> getSemuaPekerjaan() {
        String query = "SELECT * FROM pekerjaan ORDER BY id DESC";
        List<Pekerjaan> listPekerjaan = new ArrayList<>();
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
               Pekerjaan pekerjaan = new Pekerjaan(
                       rs.getInt("id"),
                       rs.getString("label")
               );
               listPekerjaan.add(pekerjaan);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return listPekerjaan;
    }

    @Override
    public Map<Integer, String> getPekerjaanMap() throws SQLException {
        Map<Integer, String> pekerjaanMap = new HashMap<>();
        String query = "Select id, label From Pekerjaan";
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                pekerjaanMap.put(rs.getInt("id"), rs.getString("label"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return pekerjaanMap;
    }

    @Override
    public Pekerjaan getPekerjaanById(int id) throws SQLException {
        String query = "SELECT * FROM pekerjaan WHERE id = ?";
        Pekerjaan pekerjaan = null;
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                pekerjaan = new Pekerjaan(
                        rs.getInt("id"),
                        rs.getString("label")
                );
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return pekerjaan;
    }

    @Override
    public int updatePekerjaan(Pekerjaan pekerjaan) throws SQLException {
       String query = "UPDATE pekerjaan SET label = ? WHERE id = ?";
       int res = -1;
       try(Connection conn = DatabaseConnection.getInstance().getConnection();
       PreparedStatement stmt = conn.prepareStatement(query)) {
           stmt.setString(1, pekerjaan.getLabel());
           stmt.setInt(2, pekerjaan.getId());
           res = stmt.executeUpdate();
       }catch (SQLException e) {
           e.printStackTrace();
           res = 0;
       }
       return res;
    }

    @Override
    public int deletePekerjaan(int id) throws SQLException {
        String query = "DELETE FROM pekerjaan WHERE id = ?";
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
