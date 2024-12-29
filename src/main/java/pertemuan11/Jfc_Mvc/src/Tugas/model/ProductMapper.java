package main.java.pertemuan11.Jfc_Mvc.src.Tugas.model;

import org.apache.ibatis.annotations.*;
import java.util.List;

public interface ProductMapper {
    @Select("SELECT * FROM products")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "price", column = "price"),
            @Result(property = "quantity", column = "quantity"),
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "categoryName", column = "category_name"),
            @Result(property = "genreId", column = "genre_id"),
            @Result(property = "genreName", column = "genre_name"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "imageUrl", column = "image_url")
    })
    List<Product> getAll();

    @Select("SELECT * FROM products WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "price", column = "price"),
            @Result(property = "quantity", column = "quantity"),
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "categoryName", column = "category_name"),
            @Result(property = "genreId", column = "genre_id"),
            @Result(property = "genreName", column = "genre_name"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "imageUrl", column = "image_url")
    })
    Product getById(Long id);

    @Insert("INSERT INTO products (name, price, quantity, category_id, genre_id, image_url) " +
            "VALUES (#{name}, #{price}, #{quantity}, #{categoryId}, #{genreId}, #{imageUrl})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Product product);

    @Update("UPDATE products SET name = #{name}, price = #{price}, quantity = #{quantity}, " +
            "category_id = #{categoryId}, genre_id = #{genreId}, image_url = #{imageUrl} " +
            "WHERE id = #{id}")
    void update(Product product);

    @Delete("DELETE FROM products WHERE id = #{id}")
    void delete(Long id);
}
