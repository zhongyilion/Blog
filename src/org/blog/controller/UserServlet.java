package org.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.blog.entity.User;
import org.blog.service.UserService;
import org.blog.service.serviceimpl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    public UserService userService = new UserServiceImpl();


    /**
     * 暂时只返回一个user，以后只返回一个user显然是不行的，要返回登陆后所有可能用到的数据，以免不停的发请求
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.login(username, password);
        request.getSession().setAttribute("user",user);
        WriteValue(user,response);
    }
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nickname = request.getParameter("nickname");
        User user = new User(username, password, nickname);
        userService.register(user);
        response.sendRedirect("../index.jsp");
    }
    public void queryByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        User user = userService.queryByName(username);
        if(user != null){
            WriteValue("true",response);//该用户存在即返回true
        }else {
            WriteValue("false",response);//不存在返回false
        }
    }

}
