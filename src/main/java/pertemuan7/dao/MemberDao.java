package main.java.pertemuan7.dao;

import main.java.pertemuan7.db.MysqlConnection;
import main.java.pertemuan7.model.JenisMember;
import main.java.pertemuan7.model.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MemberDao {
    public int insert(Member member) {
        int res = -1;
        try(Connection con = MysqlConnection.getInstance().getConnection();) {
            String uniqueId = UUID.randomUUID().toString();
            member.setId(uniqueId);
            PreparedStatement statement = con.prepareStatement("insert into member (id, jenis_member_id, nama) values(?,?,?)");
            statement.setString(1, member.getId());
            statement.setString(2, member.getJenisMember().getId());
            statement.setString(3, member.getNama());
            res = statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
     return res;
    }
    public int update(Member member) {
        int res = -1;
        try(Connection con = MysqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = con.prepareStatement("update member set nama=?, jenis_member_id=? where id=?");
            statement.setString(1, member.getNama());
            statement.setString(2, member.getJenisMember().getId());
            statement.setString(3, member.getId());
            res = statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
     return res;
    }
    public int delete(Member member) {
        int res = -1;
        try(Connection con = MysqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = con.prepareStatement("delete from member where id = ? ");
            statement.setString(1, member.getId());
            res = statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
     return res;
    }

    public List<Member> findAll() {
        List<Member> list = new ArrayList<>();
        try(Connection con = MysqlConnection.getInstance().getConnection();) {
            Statement statement = con.createStatement();
            try (ResultSet rs = statement.executeQuery("select member.id, member.nama, jenis_member.id as jenis_member_id, " +
                    "jenis_member.nama as jenis_member_nama from member join jenis_member on jenis_member.id = member.jenis_member_id");) {

                while (rs.next()) {
                    Member member = new Member();
                    member.setId(rs.getString("id"));
                    member.setNama(rs.getString("nama"));

                    JenisMember jenisMember = new JenisMember();
                    jenisMember.setId(rs.getString("jenis_member_id"));
                    jenisMember.setNama(rs.getString("jenis_member_nama"));

                    member.setJenisMember(jenisMember);
                    list.add(member);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
