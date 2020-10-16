package com.seventh.group.service.impl;

import com.seventh.group.Entity.Article;
import com.seventh.group.Entity.Option;
import com.seventh.group.repository.ArticleRepository;
import com.seventh.group.repository.OptionRepository;
import com.seventh.group.service.ArticleService;
import com.seventh.group.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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


    @Override
    public void deleteArticle(int id) {
         articleRepository.deleteById(id);
    }

    @Override
    public int updateArticle(int id) {
        return 0;
    }

    @Override
    public List<Article> selectAllArticle() {
        List<Article> articleList = articleRepository.findAll();
        return articleList;
    }

    @Override
    public Page<Article> articlePage(Pageable pageable) {
        return null;
    }

    @Override
    public void addArticleAndOption(String title, List<String> contents) {
        Article article = new Article();
        article.setCreateTime(new Date());
        article.setTitle(title);
        List<Option> list = new ArrayList<>();//用来存储所有option
        for (int i=0;i<contents.size();i++){
            Option option = new Option();
            option.setContent(contents.get(i));
            option.setArticle(article);
            list.add(option);
        }
        article.setOptions(list);
        articleRepository.saveAndFlush(article);
    }

    @Override
    public Page<Article> listArticle(Pageable pageable) {
        Page<Article> articlePage = articleRepository.findAll(pageable);
        return articlePage;
    }

    @Override
    public Article selectArticleById(int id) {
        Article article = articleRepository.findById(id).get();
        return article;
    }

    @Override
    public void save(Article article) {
        articleRepository.save(article);
    }

    @Override
    public Page<Article> saveAll(Page<Article> articlePage) {
        List<Article> articles = articlePage.toList();
        Pageable pageable = PageRequest.of(0,10, Sort.by("count"));
        return DataUtils.listToPage(articles,pageable);
    }
}
