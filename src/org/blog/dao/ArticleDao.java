package org.blog.dao;

import org.blog.entity.Article;

import java.util.List;

public interface ArticleDao {
    public List<Article> showAllArticle(int userId);
    public void addArticle(Article article);
    public Article queryArticleById(Long id);
}
