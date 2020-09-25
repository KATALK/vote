package com.seventh.group;



import com.seventh.group.Entity.Article;
import com.seventh.group.Entity.Option;
import com.seventh.group.repository.ArticleRepository;
import com.seventh.group.repository.OptionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author EdiMen
 * @Data 2020/9/19--15:47
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class test {

    @Autowired
    private ArticleRepository articleRepository;


    @Autowired
    private OptionRepository optionRepository;

    @Test
    public void test(){
        Article article = new Article();
        article.setTitle("2020年娱乐圈谁的演技最厉害");
        article.setCreateTime(new Date());
        article.setType(0);
        Option option = new Option();
        option.setContent("我不喜欢你");
        Option option01 = new Option();
        option01.setContent("我不喜欢");
        List<Option> options = new ArrayList<>();
        options.add(option);
        options.add(option01);
        option01.setArticle(article);
        option.setArticle(article);
        article.setOptions(options);
       // articleRepository.save(article);
//        optionRepository.saveAll(Arrays.asList(option,option01));
//        option.setArticleId(article.getId());

       articleRepository.save(article);
    }

    @Test
    public void demo002(){
        Article article = articleRepository.findById(10).get();
        article.setTitle("周杰伦的《晴天》是你的青春吗？");
        article.setCreateTime(new Date());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        System.out.println(format);
//        article.setCreateTime(new Date());
//        article.setTitle("我喜欢你");
////        article.setType(0);
//        articleRepository.save(article);
//        Option option = new Option();
//        option.setContent("是的");
//      Option  option02 = new Option();
//      option02.setContent("不是");
//        option.setArticle(article);//article跟option相互设置关联
//        option02.setArticle(article);
//       article.setOptions(Arrays.asList(option,option02));
////        optionRepository.save(option);
//        articleRepository.saveAndFlush(article);
        articleRepository.saveAndFlush(article);
    }

    @Test
    public void demo003(){
        Article article = articleRepository.findById(10).get();
        Date createTime = article.getCreateTime();
        String string = createTime.toString();
        System.out.println(string);
        System.out.println(new Date().toString());
    }
}
