package main.java.pertemuan7.dao;

import main.java.pertemuan7.db.MysqlConnection;
import main.java.pertemuan7.model.JenisMember;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JenisMemberDao {
    public int insert(JenisMember jenisMember) {
        int res = -1;
        try(Connection con = MysqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = con.prepareStatement("INSERT INTO jenis_member(id, nama) values (?, ?)");
            statement.setString(1, jenisMember.getId());
            statement.setString(2, jenisMember.getNama());

            res = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    public int update(JenisMember jenisMember) {
        int res = -1;
        try(  Connection con = MysqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = con.prepareStatement("update jenis_member set nama = ? where id = ?");
            statement.setString(1, jenisMember.getNama());
            statement.setString(2, jenisMember.getId());
            res = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    public int delete(JenisMember jenisMember) {
        int res = -1;
        try (Connection con = MysqlConnection.getInstance().getConnection();){
            PreparedStatement statement = con.prepareStatement("delete from jenis_member where id = ?");
            statement.setString(1, jenisMember.getId());
            res = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    public List<JenisMember> findAll() {
        List<JenisMember> list = new ArrayList<>();
        try(Connection con = MysqlConnection.getInstance().getConnection()){
            Statement statement = con.createStatement();
            try (ResultSet rs = statement.executeQuery("select * from jenis_member");){
                while (rs.next()){
                    JenisMember jenisMember = new JenisMember();
                    jenisMember.setId(rs.getString("id"));
                    jenisMember.setNama(rs.getString("nama"));
                    list.add(jenisMember);
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
