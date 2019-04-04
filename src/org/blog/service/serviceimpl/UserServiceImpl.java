package org.blog.service.serviceimpl;

import org.blog.dao.UserDao;
import org.blog.dao.daoimpl.UserDaoImp;
import org.blog.entity.User;
import org.blog.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userdao = new UserDaoImp();
    @Override
    public User login(String name, String psw) {

        return userdao.login(name,psw);
    }

    @Override
    public void register(User user) {
        userdao.register(user);
    }

    @Override
    public User queryByName(String name) {
        return userdao.queryByName(name);
    }

    @Override
    public void editUserMessage(User user) {
        userdao.editUserMessage(user);
    }
}
