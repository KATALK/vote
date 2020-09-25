package com.seventh.group.controller;

import com.seventh.group.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author EdiMen
 * @Data 2020/9/20--15:40
 * @Version 1.0
 */
@Controller
@RequestMapping("/article.do")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 跳转到添加文章页面
     * @return
     */
    @GetMapping("/add")
    public String add(){
        return "addArticle";
    }

    @PostMapping("/add")
    public String add(@RequestParam("title") String title, @RequestParam("content")List<String> contents,
                      HttpSession session,RedirectAttributes attributes){
        System.out.println(title);
        return "";
    }

}
