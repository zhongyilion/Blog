package org.blog.service;

import org.blog.entity.User;

public interface UserService {
    public User login(String name,String psw);
    public void register(User user);
    public User queryByName(String name);
}
