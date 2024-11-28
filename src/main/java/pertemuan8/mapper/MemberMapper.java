package main.java.pertemuan8.mapper;

import org.apache.ibatis.annotations.*;
import main.java.pertemuan8.model.JenisMember;
import main.java.pertemuan8.model.Member;

import java.util.List;

@Mapper
public interface MemberMapper {
    @Insert("INSERT INTO member (id, nama, jenis_member_id) values (#{id},#{nama}, #{jenisMemberId})")
    public Integer insert(Member member);

    @Update("update member set nama = #{nama}, jenis_member_id = #{jenisMemberId} where id = #{id}")
    public Integer update(Member member);

    @Delete("delete from member where id = #{id}")
    public Integer delete(Integer id);



    @Select("SELECT * From member")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "nama", column = "nama"),
            @Result(property = "jenisMember", column = "jenis_member_id",
            javaType = JenisMember.class, one = @One(select = "getJenisMember"))})
    List<Member> findAll();

    @Select("SELECT * From jenis_member Where id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "nama", column = "nama")
    })
    JenisMember getJenisMember(String jenisMemberId);
}
