package com.seventh.group.repository;

import com.seventh.group.Entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author EdiMen
 * @Data 2020/9/10--18:35
 * @Version 1.0
 */
public interface ArticleRepository extends JpaRepository<Article,Integer> {
    @Query("select b from  Article b order by b.createTime desc ")
    List<Article> findAll();
}
