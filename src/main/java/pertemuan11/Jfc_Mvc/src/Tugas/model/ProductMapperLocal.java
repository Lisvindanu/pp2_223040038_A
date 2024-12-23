package main.java.pertemuan11.Jfc_Mvc.src.Tugas.model;

import org.apache.ibatis.annotations.*;
import java.util.List;

public interface ProductMapperLocal {
    @Select("SELECT p.id, p.name, p.price, p.quantity, p.category_id, c.name AS category_name, " +
            "p.genre_id, g.name AS genre_name, p.created_at, p.updated_at, p.image_url " +
            "FROM products p " +
            "LEFT JOIN categories c ON p.category_id = c.id " +
            "LEFT JOIN genres g ON p.genre_id = g.id")
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

    @Select("SELECT p.id, p.name, p.price, p.quantity, p.category_id, c.name AS category_name, " +
            "p.genre_id, g.name AS genre_name, p.created_at, p.updated_at, p.image_url " +
            "FROM products p " +
            "LEFT JOIN categories c ON p.category_id = c.id " +
            "LEFT JOIN genres g ON p.genre_id = g.id " +
            "WHERE p.id = #{id}")
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

    @Insert("INSERT INTO products (name, price, quantity, category_id, genre_id, image_url, created_at, updated_at) " +
            "VALUES (#{name}, #{price}, #{quantity}, #{categoryId}, #{genreId}, #{imageUrl}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Product product);

    @Update("UPDATE products SET name = #{name}, price = #{price}, quantity = #{quantity}, " +
            "category_id = #{categoryId}, genre_id = #{genreId}, image_url = #{imageUrl}, updated_at = NOW() " +
            "WHERE id = #{id}")
    void update(Product product);

    @Delete("DELETE FROM products WHERE id = #{id}")
    void delete(Long id);

    @Select("SELECT id FROM categories WHERE name = #{name}")
    Long getCategoryId(String name);

    @Select("SELECT id FROM genres WHERE name = #{name}")
    Long getGenreId(String name);

    @Select("SELECT c.id, c.name as categoryName FROM categories c")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "categoryName", column = "categoryName")
    })
    List<Product> getCategories();

    @Select("SELECT g.id, g.name as genreName FROM genres g")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "genreName", column = "genreName")
    })
    List<Product> getGenres();
}