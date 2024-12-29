package main.java.pertemuan11.Jfc_Mvc.src.latihan.model;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Select("Select * From users")
    List<User> getAll();

    @Insert("INSERT INTO users (name, email) values (#{name}, #{email})")
    void insert(User user);
}
