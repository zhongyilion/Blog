package org.blog.controller;

import org.blog.dao.ArticleDao;
import org.blog.dao.UserDao;
import org.blog.dao.daoimpl.ArticleDaoImp;
import org.blog.dao.daoimpl.UserDaoImp;
import org.blog.entity.Article;
import org.blog.entity.User;
import org.omg.PortableInterceptor.INACTIVE;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@MultipartConfig
@WebServlet("/article/*")
public class ArticleServlet extends BaseServlet{
    ArticleDao articleDao = new ArticleDaoImp();
    UserDao userDao = new UserDaoImp();
    public void showAllArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String userId = request.getParameter("userId");
        System.out.println("有分页？"+request.getParameter("limit"));
        String limit = request.getParameter("limit");
        String nowpage = request.getParameter("page");
        System.out.println("有当前页？"+nowpage);
        User user = (User)request.getSession().getAttribute("user");
        List<Article> articles = articleDao.showAllArticle((int)user.getUserId(),Integer.parseInt(nowpage),Integer.parseInt(limit));
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",articleDao.countAriticle((int)user.getUserId()));
        System.out.println(articleDao.countAriticle((int)user.getUserId()));
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

    /**
     * 返回文章的信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void queryArticleById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("articleId");
        Article article = articleDao.queryArticleById(Long.parseLong(id));
        request.getSession().setAttribute("article",article);
    }

    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("articleId");
        articleDao.deleteById(Long.parseLong(id));
    }

    /**
     * 编辑文章的中转站
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void skipeditpage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("articleId");
        Article article = articleDao.queryArticleById(Long.parseLong(id));
        request.getSession().setAttribute("article",article);
//        request.getRequestDispatcher("views/backstage/editorarticle.jsp").forward(request,response);
    }
    public void editArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String userId = request.getParameter("userId");
        String articleId = request.getParameter("articleId");
        User user = userDao.queryById(Integer.parseInt(userId));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Article article = new Article(sdf.format(date),title,"",0,0,content,user);
        article.setArticleId(Long.parseLong(articleId));
        articleDao.editArticle(article);
        request.getSession().removeAttribute("article");
    }

    public void queryLastAriticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("articleId");
        Article article = articleDao.queryLastAriticle(Integer.parseInt(id));
        WriteValue(article,response);
        request.getSession().setAttribute("article",article);
    }

    public void queryNextAriticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("articleId");
        Article article = articleDao.queryNextAriticle(Integer.parseInt(id));
        WriteValue(article,response);
        request.getSession().setAttribute("article",article);
    }
    public void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");

        request.setCharacterEncoding("UTF-8");
        Part mf = request.getPart("file");
//        System.out.println(mf.getHeader("Content-Disposition"));
        /**
         * header 包含文件头信息 具体内容为:form-data; name="file"; filename="`)XDE4]R_X%CAM}`@X]X_S5.png"
         */
        String header = mf.getHeader("Content-Disposition");
        //得到文件后缀
        String end = header.substring(header.lastIndexOf('.'),header.length()-1);
        String filename = UUID.randomUUID().toString();
        String path = request.getServletContext().getRealPath("res/imgs");
//        System.out.println(path);
        String fullpath = path+"\\"+"article"+user.getUserId()+"\\"+filename+end;
        //创建每个用户的头像存放路径
        new File(path+"\\"+"article"+user.getUserId()).mkdir();

        FileOutputStream out = new FileOutputStream(new File(fullpath));
        System.out.println("图片路径:"+fullpath);
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
//        user.setImage(fullpath);
//        userService.editUserMessage(user);
//        System.out.println("UPUP文章");
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        Map<String,Object> data = new HashMap<>();
        data.put("src","/res/imgs"+"/article"+user.getUserId()+"/"+filename+end);
        data.put("title","孙卫东的帅照");
        map.put("data",data);
        WriteValue(map,response);
    }

}
