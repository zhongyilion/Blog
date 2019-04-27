package org.blog.dao;

import org.blog.entity.Article;

import java.util.List;

public interface ArticleDao {
    public List<Article> showAllArticle(int userId,int nowpage,int limit);
    public void addArticle(Article article);
    public Article queryArticleById(Long id);
    public void deleteById(Long id);
    public void editArticle(Article article);
    public Article queryNextAriticle(int id);
    public Article queryLastAriticle(int id);
    public int countAriticle(int id);
}
