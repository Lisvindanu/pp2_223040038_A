package main.java.pertemuan7.Tugas.dao;

import main.java.pertemuan7.Tugas.model.ContactInfo;
import main.java.pertemuan7.Tugas.model.User;
import main.java.pertemuan7.Tugas.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements UserDaoInterface{
    private ContactInfoDao contactInfoDao = new ContactInfoDao();
    @Override
    public int addUser(User user) throws SQLException {
        String userQuery = "INSERT INTO users (nama, tanggal_lahir, pekerjaan_id, jenis_tabungan_id, frekuensi_transaksi, wna) VALUES(?,?,?,?,?,?)";
        int res = -1;
        try (Connection conn = DatabaseConnection.getInstance().getConnection()) {
            conn.setAutoCommit(true);
            try (PreparedStatement userStmt = conn.prepareStatement(userQuery, Statement.RETURN_GENERATED_KEYS)) {
                userStmt.setString(1, user.getNama());
                userStmt.setString(2, user.getTanggalLahir());
                userStmt.setInt(3, user.getPekerjaanId());
                userStmt.setInt(4, user.getJenisTabunganId());
                userStmt.setInt(5, user.getFrekuensiTransaksi());
                userStmt.setBoolean(6, user.isWna());
                userStmt.executeUpdate();

                ResultSet rs = userStmt.getGeneratedKeys();
                if (rs.next()) {
                    int userId = rs.getInt(1);
                    user.getContactInfo().setUserId(userId);
                    int contactRes = contactInfoDao.addContactInfo(user.getContactInfo());

                  res = contactRes == 1 ? 1 : 0;
                }
            }catch (SQLException e) {
                e.printStackTrace();
                res =0;
            }
        }
        return res;
    }

    @Override
    public List<User> getAllUser() throws SQLException {
        String query = "SELECT u.*,\n" +
                "       p.label AS pekerjaan_label,\n" +
                "       jt.label AS jenis_tabungan_label,\n" +
                "       ci.no_hp,\n" +
                "       ci.email,\n" +
                "       ci.alamat\n" +
                "FROM users u\n" +
                "         JOIN pekerjaan p ON u.pekerjaan_id = p.id\n" +
                "         JOIN jenis_tabungan jt ON u.jenis_tabungan_id = jt.id\n" +
                "         LEFT JOIN contact_info ci ON ci.user_id = u.id;\n";
        List<User> users = new ArrayList<>();
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)){
            while(rs.next()){
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("nama"),
                        rs.getString("tanggal_lahir"),
                        rs.getInt("frekuensi_transaksi"),
                        rs.getBoolean("wna"),
                        rs.getInt("pekerjaan_id"),
                        rs.getInt("jenis_tabungan_id"),
                        rs.getString("pekerjaan_label"),
                        rs.getString("jenis_tabungan_label")
                );
                ContactInfo contactInfo = new ContactInfo(
                        rs.getInt("id"),
                        rs.getString("no_hp"),
                        rs.getString("email"),
                        rs.getString("alamat"),
                        rs.getInt("id")
                );
                user.setContactInfo(contactInfo);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User getUserById(int id) throws SQLException {
        String query = "SELECT u.*, p.label AS pekerjaan_label, jt.label AS jenis_tabungan_label, ci.no_hp, ci.email, ci.alamat " +
                "FROM users u " +
                "JOIN pekerjaan p ON u.pekerjaan_id = p.id " +
                "JOIN jenis_tabungan jt ON u.jenis_tabungan_id = jt.id " +
                "LEFT JOIN contact_info ci ON u.id = ci.user_id " +
                "WHERE u.id = ?";
        User user = null;
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                user = new User(
                        rs.getInt("id"),
                        rs.getString("nama"),
                        rs.getString("tanggal_lahir"),
                        rs.getInt("frekuensi_transaksi"),
                        rs.getBoolean("wna"),
                        rs.getInt("pekerjaan_id"),
                        rs.getInt("jenis_tabungan_id"),
                        rs.getString("pekerjaan_label"),
                        rs.getString("jenis_tabungan_label")
                );
                ContactInfo contactInfo = new ContactInfo(
                        rs.getInt("id"),
                        rs.getString("no_hp"),
                        rs.getString("email"),
                        rs.getString("alamat"),
                        rs.getInt("id")
                );
                user.setContactInfo(contactInfo);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int updateUser(User user) throws SQLException {
      String userQuery = "UPDATE users set nama = ?, tanggal_lahir = ?, pekerjaan_id = ?, jenis_tabungan_id = ?, frekuensi_transaksi = ?, wna = ? WHERE id = ?";
      String contactQuery = "UPDATE contact_info SET no_hp = ?, email = ?, alamat = ? WHERE id = ?";
      int res = -1;

      try(Connection conn = DatabaseConnection.getInstance().getConnection()){
          conn.setAutoCommit(true);

          try(PreparedStatement userStmt = conn.prepareStatement(userQuery)) {
              userStmt.setString(1, user.getNama());
              userStmt.setString(2, user.getTanggalLahir());
              userStmt.setInt(3, user.getPekerjaanId());
              userStmt.setInt(4, user.getJenisTabunganId());
              userStmt.setInt(5, user.getFrekuensiTransaksi());
              userStmt.setBoolean(6, user.isWna());
              userStmt.setInt(7, user.getId());
              int userRes = userStmt.executeUpdate();

              if(userRes == 1) {
                  try(PreparedStatement contactStmt = conn.prepareStatement(contactQuery)) {
                      ContactInfo contactInfo = user.getContactInfo();
                      contactStmt.setString(1, contactInfo.getNoHp());
                      contactStmt.setString(2, contactInfo.getEmail());
                      contactStmt.setString(3, contactInfo.getAlamat());
                      contactStmt.setInt(4, user.getId());
                      int contactRes = contactStmt.executeUpdate();

                      res = contactRes == 1 ? 1 : 0;
                  }
              }
          }catch (SQLException e) {
              e.printStackTrace();
              res = 0;
          }
      }
      return res;
    }

    @Override
    public int deleteUser(int id) throws SQLException {
        String userQuery = "DELETE FROM users WHERE id = ?";
        String contactQuery = "DELETE FROM contact_info WHERE id = ?";
        int res = -1;
        try(Connection conn = DatabaseConnection.getInstance().getConnection()) {
            conn.setAutoCommit(false);

                try (PreparedStatement contactStmt = conn.prepareStatement(contactQuery)) {
                    contactStmt.setInt(1, id);
                    contactStmt.executeUpdate();

                    try (PreparedStatement userStmt = conn.prepareStatement(userQuery)) {
                        userStmt.setInt(1, id);
                        int userRes = userStmt.executeUpdate();
                        if(userRes == 1) {
                            conn.commit();
                            res = 1;
                        }else {
                            conn.rollback();
                            res = 0;
                        }
                    }
                } catch (SQLException e) {
                    conn.rollback();
                    e.printStackTrace();
                }finally {
                    conn.setAutoCommit(true);
                }
            }
         return res;
        }
}
