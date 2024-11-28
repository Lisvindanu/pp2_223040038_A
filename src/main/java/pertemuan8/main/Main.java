package main.java.pertemuan8.main;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import main.java.pertemuan8.dao.JenisMemberDao;
import main.java.pertemuan8.dao.MemberDao;
import main.java.pertemuan8.view.main.MainFrame;

import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(resource);

        if (inputStream == null) {
            System.err.println("File mybatis-config.xml tidak ditemukan di classpath!");
        } else {
            System.out.println("File mybatis-config.xml ditemukan di classpath.");
        }

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


        JenisMemberDao jenisMemberDao = new JenisMemberDao(sqlSessionFactory);
        MemberDao memberDao = new MemberDao(sqlSessionFactory);
        MainFrame f = new MainFrame(jenisMemberDao, memberDao);

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                f.setVisible(true);
            }
        });
    }
}
