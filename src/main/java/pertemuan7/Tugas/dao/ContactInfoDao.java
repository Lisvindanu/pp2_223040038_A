package main.java.pertemuan7.Tugas.dao;

import main.java.pertemuan7.Tugas.model.ContactInfo;
import main.java.pertemuan7.Tugas.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactInfoDao implements ContactInfoDaoInterface{
    @Override
    public int addContactInfo(ContactInfo ContactInfo) throws SQLException {
        String query = "INSERT INTO contact_info (no_hp, email, alamat, user_id) VALUES(?,?,?,?)";
        int res = -1;
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, ContactInfo.getNoHp());
            stmt.setString(2, ContactInfo.getEmail());
            stmt.setString(3, ContactInfo.getAlamat());
            stmt.setInt(4, ContactInfo.getUserId());
            res = stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            res = 0;
        }
        return res;
    }

    @Override
    public List<ContactInfo> getAllContactInfo() throws SQLException {
       String query = "SELECT * FROM contact_info";
       List<ContactInfo> contactInfoList = new ArrayList<>();
       try (Connection conn = DatabaseConnection.getInstance().getConnection();
       PreparedStatement stmt = conn.prepareStatement(query)) {
           ResultSet rs = stmt.executeQuery(query);
           while (rs.next()) {
               ContactInfo contactInfo = new ContactInfo(
                       rs.getInt("id"),
                       rs.getString("no_hp"),
                       rs.getString("email"),
                       rs.getString("alamat"),
                       rs.getInt("user_id")
               );
               contactInfoList.add(contactInfo);
           }
       }catch (SQLException e) {
           e.printStackTrace();
       }
       return contactInfoList;
    }

    @Override
    public ContactInfo getContactInfoById(int id) throws SQLException {
        String query = "SELECT * FROM contact_info WHERE id = ?";
        ContactInfo contactInfo = null;
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                contactInfo = new ContactInfo(
                        rs.getInt("id"),
                        rs.getString("no_hp"),
                        rs.getString("email"),
                        rs.getString("alamat"),
                        rs.getInt("user_id")
                );
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return contactInfo;
    }

    @Override
    public int updateContactInfo(ContactInfo ContactInfo) throws SQLException{
        String query = "Update contact_info set no_hp = ?, email = ?, alamat = ?, user_id = ? where id = ?";
        int res = -1;
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, ContactInfo.getNoHp());
            stmt.setString(2, ContactInfo.getEmail());
            stmt.setString(3, ContactInfo.getAlamat());
            stmt.setInt(4, ContactInfo.getUserId());
            stmt.setInt(5, ContactInfo.getId());
            res = stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            res = 0;
        }
        return res;
    }

    @Override
    public int deleteContactInfo(int id) throws SQLException {
        String query = "DELETE FROM contact_info WHERE id = ?";
        int res = -1;
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            res = stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            res = 0;
        }
        return res;
    }
}
