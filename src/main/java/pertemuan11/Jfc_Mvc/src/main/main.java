package main.java.pertemuan11.Jfc_Mvc.src.main;

import main.java.pertemuan11.Jfc_Mvc.src.controller.UserController;
import main.java.pertemuan11.Jfc_Mvc.src.model.MyBatisUtil;
import main.java.pertemuan11.Jfc_Mvc.src.model.UserMapper;
import main.java.pertemuan11.Jfc_Mvc.src.view.UserView;
import org.apache.ibatis.session.SqlSession;

public class main {
    public static void main(String[] args) {
        SqlSession session = MyBatisUtil.getSqlSession();
        UserMapper mapper = session.getMapper(UserMapper.class);

        UserView view = new UserView();
        new UserController(view, mapper);

        view.setVisible(true);
    }
}
