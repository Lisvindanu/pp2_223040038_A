package main.java.pertemuan11.Jfc_Mvc.src.latihan.controller;

import main.java.pertemuan11.Jfc_Mvc.src.latihan.model.MyBatisUtil;
import main.java.pertemuan11.Jfc_Mvc.src.latihan.model.User;
import main.java.pertemuan11.Jfc_Mvc.src.latihan.model.UserMapper;
import main.java.pertemuan11.Jfc_Mvc.src.latihan.view.UserPdf;
import main.java.pertemuan11.Jfc_Mvc.src.latihan.view.UserView;
import org.apache.ibatis.session.SqlSession;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class UserController {
    private UserView view;
    private UserMapper mapper;
    private UserPdf pdf;

    public UserController(UserView view, UserMapper mapper, UserPdf pdf) {
        this.view = view;
        this.mapper = mapper;
        this.pdf = pdf;

        this.view.addAddUserListener(new AddUserListener());
        this.view.addRefreshUserListener(new RefreshListener());
        this.view.addExportListener(new ExportListener());
    }

    class AddUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getNameInput();
            String email = view.getEmailInput();
            if (!name.isEmpty() && !email.isEmpty()) {
                User user = new User();
                user.setName(name);
                user.setEmail(email);

                SqlSession session = MyBatisUtil.getSqlSession();
                UserMapper mapper = session.getMapper(UserMapper.class);
                mapper.insert(user);

                session.commit();
                session.close();

                JOptionPane.showMessageDialog(view, "User added successfully!");
            } else {
                JOptionPane.showMessageDialog(view, "Please fill in all fields!");
            }
        }
    }


    class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           List<User> users = mapper.getAll();
           String[] userArray  = users.stream().map(u->u.getName() + " (" + u.getEmail() + ")").toArray(String[]::new);
           view.setUserList(userArray);
        }
    }

    class ExportListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<User> users = mapper.getAll();
            pdf.exportPdf(users);
        }
    }
}
