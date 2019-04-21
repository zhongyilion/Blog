package org.blog.dao;

import org.blog.entity.User;

import java.util.List;

public interface UserDao {
    public User login(String name,String psw);
    public void register(User user);
    public User queryByName(String name);
    public void editUserMessage(User user);
    public List<User> showAllUsers();
    public User queryById(int id);
}
