package org.blog.dao.daoimpl;

import org.blog.DButil.BaseDB;
import org.blog.dao.ArticleDao;
import org.blog.dao.UserDao;
import org.blog.entity.Article;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ArticleDaoImp extends BaseDB implements ArticleDao {
    UserDao userDao = new UserDaoImp();
    JdbcTemplate jdbcTemplate = jdbcTemplate();
    @Override
    public List<Article> showAllArticle(int userId) {
        String sql = "select * from article where user_id = ?";
        List<Article> articles=null;
        try {
            articles = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Article.class),userId);
            for(Article a:articles){
                a.setUser(userDao.queryById(userId));
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return articles;
    }

    @Override
    public void addArticle(Article article) {
        String sql = "insert into article values(null,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,article.getTime(),article.getTitle(),article.getImage(),article.getDianzan(),article.getPinlum(),article.getContent(),article.getUser().getUserId());

    }

    @Override
    public Article queryArticleById(Long id) {
        String sql = "select * from article where article_id=?";
        String s = "select user_id from article where article_id=?";
        Article article = null;
        try {
            article = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Article.class),id);
            Integer integer = jdbcTemplate.queryForObject(s, Integer.class, id);
            System.out.println(integer.intValue());
            article.setUser(userDao.queryById(integer.intValue()));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return article;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from article where article_id = ?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public void editArticle(Article article) {
        String sql = "update article set time=?,title=?,image=?,dianzan=?,pinlum=?,content=? where article_id=?";
        System.out.println(article.toString());
        jdbcTemplate.update(sql,article.getTime(),article.getTitle(),article.getImage(),article.getDianzan(),article.getPinlum(),article.getContent(),article.getArticleId());
    }

    @Override
    public Article queryLastAriticle(int id) {
        String ss = "select user_id from article where article_id=?";
        Integer integer1 = jdbcTemplate.queryForObject(ss, Integer.class, id);
        int userId = integer1.intValue();
        String s = "select article_id from article where user_id=? limit 1";
        String sql = "select * from article where article_id = (select max(article_id) from article where article_id < ? and user_id=?)";
        Integer integer = jdbcTemplate.queryForObject(s, Integer.class,userId);
        if(id==integer.intValue()){
            return null;
        }
        Article article = null;
        try {
            article = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Article.class), id,userId);
            article.setUser(userDao.queryById(userId));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return article;
    }

    @Override
    public Article queryNextAriticle(int id) {
        String ss = "select user_id from article where article_id=?";
        Integer integer1 = jdbcTemplate.queryForObject(ss, Integer.class, id);
        int userId = integer1.intValue();
        String s = "select article_id from article where user_id=? order by article_id desc limit 1";
        String sql = "select * from article where article_id = (select min(article_id) from article where article_id > ? and user_id=?)";
        Integer integer = jdbcTemplate.queryForObject(s, Integer.class,userId);
        if(id==integer.intValue()){
            return null;
        }
        Article article = null;
        try {
            article = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Article.class), id,userId);
            article.setUser(userDao.queryById(userId));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return article;
    }

}
