package main.java.pertemuan11.Jfc_Mvc.src.latihan.main;

import main.java.pertemuan11.Jfc_Mvc.src.Tugas.util.SSLUtils;
import main.java.pertemuan11.Jfc_Mvc.src.latihan.controller.UserController;
import main.java.pertemuan11.Jfc_Mvc.src.latihan.model.MyBatisUtil;
import main.java.pertemuan11.Jfc_Mvc.src.latihan.model.UserMapper;
import main.java.pertemuan11.Jfc_Mvc.src.latihan.view.UserPdf;
import main.java.pertemuan11.Jfc_Mvc.src.latihan.view.UserView;
import org.apache.ibatis.session.SqlSession;

public class main {
    public static void main(String[] args) {
        SqlSession session = MyBatisUtil.getSqlSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        UserPdf pdf = new UserPdf();

        SSLUtils.disableSSLVerification();
        UserView view = new UserView();
        new UserController(view, mapper, pdf);

        view.setVisible(true);
    }
}


