package main.java.Test;

import main.java.pertemuan8.main.Main;
import main.java.pertemuan8.mapper.JenisMemberMapper;
import main.java.pertemuan8.model.JenisMember;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.util.List;

public class TestMapper {
    public static void main(String[] args) {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(Main.class.getClassLoader().getResourceAsStream("mybatis-config.xml"));
        try (SqlSession session = sqlSessionFactory.openSession()) {
            JenisMemberMapper mapper = session.getMapper(JenisMemberMapper.class);
            List<JenisMember> jenisMembers = mapper.findAll();
            jenisMembers.forEach(jm -> System.out.println(jm.getNama()));
        }
    }
}
