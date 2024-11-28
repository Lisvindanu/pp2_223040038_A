package main.java.pertemuan8.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import main.java.pertemuan8.model.Member;
import main.java.pertemuan8.mapper.MemberMapper;

import java.util.List;

public class MemberDao {
    private final SqlSessionFactory sqlSessionFactory;
    public MemberDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public int insert(Member member) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            MemberMapper mapper = session.getMapper(MemberMapper.class);
            return mapper.insert(member);
        }
    }

    public int update(Member member) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            MemberMapper mapper = session.getMapper(MemberMapper.class);
            return mapper.update(member);
        }
    }

    public int delete(Integer id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            MemberMapper mapper = session.getMapper(MemberMapper.class);
            return mapper.delete(id);
        }
    }

    public List<Member> findAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            MemberMapper mapper = session.getMapper(MemberMapper.class);
            return mapper.findAll();
        }
    }
}