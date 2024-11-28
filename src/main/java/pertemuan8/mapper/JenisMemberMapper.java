package main.java.pertemuan8.mapper;

import org.apache.ibatis.annotations.*;
import main.java.pertemuan8.model.JenisMember;

import java.util.List;

@Mapper
public interface JenisMemberMapper {
    @Insert("Insert into jenis_member (id,nama) values(#{id}, #{nama})")


    public Integer insert(JenisMember jenisMember);

    @Update("update jenis_member set nama = #{nama} where id= #{id}")
    public Integer Update(JenisMember jenisMember);

    @Delete("delete from jenis_member where id= #{id}")
    public Integer delete(Integer id);

    @Select("SELECT * FROM  jenis_member")
    @Results( value = {
            @Result(property = "id", column = "id"),
            @Result(property = "nama", column = "nama")
    })
    List<JenisMember> findAll();
}
