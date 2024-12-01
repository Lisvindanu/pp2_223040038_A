package main.java.pertemuan8.Latihan.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import main.java.pertemuan8.Latihan.model.JenisMember;
import main.java.pertemuan8.Latihan.mapper.JenisMemberMapper;

import java.util.List;

public class JenisMemberDao {
    private final SqlSessionFactory sqlSessionFactory;
    public JenisMemberDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public int insert(JenisMember jenisMember) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            JenisMemberMapper mapper = session.getMapper(JenisMemberMapper.class);
            return mapper.insert(jenisMember);
        }
    }

    public int update(JenisMember jenisMember) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            JenisMemberMapper mapper = session.getMapper(JenisMemberMapper.class);
            return mapper.update(jenisMember);
        }
    }

    public int delete(String id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            JenisMemberMapper mapper = session.getMapper(JenisMemberMapper.class);
            return mapper.delete(id);
        }
    }

    public List<JenisMember> findAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            JenisMemberMapper mapper = session.getMapper(JenisMemberMapper.class);
            return mapper.findAll();
        }
    }
}