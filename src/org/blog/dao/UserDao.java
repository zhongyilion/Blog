package org.blog.dao;

import org.blog.entity.User;

public interface UserDao {
    public User login(String name,String psw);
    public void register(User user);
    public User queryByName(String name);
    public void editUserMessage(User user);
}
