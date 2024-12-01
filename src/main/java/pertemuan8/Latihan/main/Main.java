package main.java.pertemuan8.Latihan.main;

import com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import main.java.pertemuan8.Latihan.dao.JenisMemberDao;
import main.java.pertemuan8.Latihan.dao.MemberDao;
import main.java.pertemuan8.Latihan.view.main.MainFrame;

import javax.swing.*;
import java.io.InputStream;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        FlatArcDarkOrangeIJTheme.setup();
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
                java.net.URL imgURL = getClass().getClassLoader().getResource("icons/icon.png");

                if(imgURL == null) {
                    System.out.println("Gambar gada");
                }else {
                    f.setIconImage(new ImageIcon(imgURL).getImage());
                    f.setVisible(true);
                }
            }
        });
    }
}
