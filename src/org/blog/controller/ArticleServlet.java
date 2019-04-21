package org.blog.controller;

import org.blog.dao.ArticleDao;
import org.blog.dao.UserDao;
import org.blog.dao.daoimpl.ArticleDaoImp;
import org.blog.dao.daoimpl.UserDaoImp;
import org.blog.entity.Article;
import org.blog.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/article/*")
public class ArticleServlet extends BaseServlet{
    ArticleDao articleDao = new ArticleDaoImp();
    UserDao userDao = new UserDaoImp();
    public void showAllArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String userId = request.getParameter("userId");
        User user = (User)request.getSession().getAttribute("user");
        List<Article> articles = articleDao.showAllArticle((int)user.getUserId());
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",articles.size());
        map.put("data",articles);
        map.put("writer",user.getNickname());
        WriteValue(map,response);
    }
    public void addArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String userId = request.getParameter("userId");
        User user = userDao.queryById(Integer.parseInt(userId));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
//        java.sql.Timestamp dateSQL = new java.sql.Timestamp(date.getTime());
//        java.sql.Date sqldate = new java.sql.Date(date.getTime());
        Article article = new Article(sdf.format(date),title,"",0,0,content,user);
        articleDao.addArticle(article);
    }
    public void queryArticleById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("articleId");
        Article article = articleDao.queryArticleById(Long.parseLong(id));
        request.getSession().setAttribute("article",article);
//        WriteValue(article,response);
    }
}
