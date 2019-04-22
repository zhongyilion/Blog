package org.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.blog.dao.UserDao;
import org.blog.dao.daoimpl.UserDaoImp;
import org.blog.entity.User;
import org.blog.service.UserService;
import org.blog.service.serviceimpl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@MultipartConfig
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    public UserService userService = new UserServiceImpl();
    public UserDao userDao = new UserDaoImp();


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
        String sex = request.getParameter("sex");
        String age = request.getParameter("age");
        String telephone = request.getParameter("phone");
        String email = request.getParameter("email");
        User user = new User(username, password, nickname,sex,Long.parseLong(age),telephone,email);
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
    public void editUserMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String nickname = request.getParameter("nickname");
        String sex = request.getParameter("sex");
        String age = request.getParameter("age");
        String telephone = request.getParameter("phone");
        String email = request.getParameter("email");
        User user = userService.queryByName(username);
        user.setNickname(nickname);
        user.setSex(sex);
        user.setAge(Long.parseLong(age));
        user.setTelephone(telephone);
        user.setEmail(email);
        userService.editUserMessage(user);
        WriteValue(user,response);
//        response.sendRedirect("../index.jsp");
    }

    public void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        request.setCharacterEncoding("UTF-8");
        Part mf = request.getPart("file");
        System.out.println(mf.getHeader("Content-Disposition"));
        /**
         * header 包含文件头信息 具体内容为:form-data; name="file"; filename="`)XDE4]R_X%CAM}`@X]X_S5.png"
         */
        String header = mf.getHeader("Content-Disposition");
        //得到文件后缀
        String end = header.substring(header.lastIndexOf('.'),header.length()-1);
//        String realPath = request.getServletContext().getRealPath("/upload");
//        System.out.println(realPath);
//        mf.write("upload"+"/aaa.jpg");
        String filename = UUID.randomUUID().toString();
        String path = request.getServletContext().getRealPath("res/imgs");
//        System.out.println(path);
        String fullpath = path+"\\"+user.getUserId()+"\\"+filename+end;
        //创建每个用户的头像存放路径
        new File(path+"\\"+user.getUserId()).mkdir();
        FileOutputStream out = new FileOutputStream(new File(fullpath));
        InputStream in=mf.getInputStream();
        byte[] bs=new byte[1024];
        int len=-1;
        while((len=in.read(bs))!=-1){

            out.write(bs,0,len);
        }
        out.flush();
        out.close();
        in.close();

        /**
         * 用相对路径也能得到项目打包的相对路径
         */
        user.setImage("/res/imgs"+"/"+user.getUserId()+"/"+filename+end);
        userService.editUserMessage(user);

        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        Map<String,Object> data = new HashMap<>();
        data.put("src","upload/aaa.jpg");
        map.put("data",data);
        map.put("user",user);
        WriteValue(map,response);
    }

    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("user");
    }

    /**
     * 查询所有用户
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void showAllUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userDao.showAllUsers();
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",users.size());
        map.put("data",users);
        WriteValue(map,response);
    }

    public void showpersoninfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("userId");
        User user = userDao.queryById(Integer.parseInt(id));
        request.setAttribute("personinfo",user);
        request.getRequestDispatcher("personinformation.jsp").forward(request,response);
    }


}
