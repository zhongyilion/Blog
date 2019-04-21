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
    @Override
    public List<Article> showAllArticle(int userId) {
        JdbcTemplate jdbcTemplate = jdbcTemplate();
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
        JdbcTemplate jdbcTemplate = jdbcTemplate();
        String sql = "insert into article values(null,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,article.getTime(),article.getTitle(),article.getImage(),article.getDianzan(),article.getPinlum(),article.getContent(),article.getUser().getUserId());

    }

    @Override
    public Article queryArticleById(Long id) {
        JdbcTemplate jdbcTemplate = jdbcTemplate();
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
}
