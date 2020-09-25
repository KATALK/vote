package com.seventh.group.service.impl;

import com.seventh.group.Entity.Article;
import com.seventh.group.repository.ArticleRepository;
import com.seventh.group.repository.OptionRepository;
import com.seventh.group.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author EdiMen
 * @Data 2020/9/20--16:07
 * @Version 1.0
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private OptionRepository optionRepository;
    //添加文章
    @Override
    public void addArticle(String title) {
        Article article = new Article();
        article.setTitle(title);
        article.setCreateTime(new Date());
        articleRepository.saveAndFlush(article);
    }

    @Override
    public int deleteArticle(int id) {
        return 0;
    }

    @Override
    public int updateArticle(int id) {
        return 0;
    }

    @Override
    public List<Article> selectAllArticle() {
        return null;
    }
}
