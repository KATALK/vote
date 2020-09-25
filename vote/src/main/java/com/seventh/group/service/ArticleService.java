package com.seventh.group.service;

import com.seventh.group.Entity.Article;

import java.util.List;

/**
 * @Author EdiMen
 * @Data 2020/9/19--15:16
 * @Version 1.0
 */
public interface ArticleService {
    public void addArticle(String title);//添加文章
    public int deleteArticle(int id);//根据主键id删除文章
    public int updateArticle(int id);//根据主键id更改文章
    public List<Article> selectAllArticle();//查询所有投票
}
