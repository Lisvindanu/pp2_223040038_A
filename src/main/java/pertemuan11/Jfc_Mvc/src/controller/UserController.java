package main.java.pertemuan11.Jfc_Mvc.src.controller;

import main.java.pertemuan11.Jfc_Mvc.src.model.MyBatisUtil;
import main.java.pertemuan11.Jfc_Mvc.src.model.User;
import main.java.pertemuan11.Jfc_Mvc.src.model.UserMapper;
import main.java.pertemuan11.Jfc_Mvc.src.view.UserView;
import org.apache.ibatis.session.SqlSession;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class UserController {
    private UserView view;
    private UserMapper mapper;

    public UserController(UserView view, UserMapper mapper) {
        this.view = view;
        this.mapper = mapper;

        this.view.addAddUserListener(new AddUserListener());
        this.view.addRefreshUserListener(new RefreshListener());
    }

    class AddUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getNameINput();
            String email = view.getEmailINput();
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
}
