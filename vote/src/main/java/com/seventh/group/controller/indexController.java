package com.seventh.group.controller;

import com.seventh.group.Entity.Article;
import com.seventh.group.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author EdiMen
 * @Data 2020/9/19--17:09
 * @Version 1.0
 */

@Controller
public class indexController {


    @Autowired
    private ArticleService articleService;
    @GetMapping("/")
    public String index(@RequestParam(value = "start",defaultValue = "0")Integer start,
                        @RequestParam(value = "limit",defaultValue = "7")Integer limit,
                        Model model, HttpSession session) {
        session.setAttribute("user",null);
        start = start <0 ? 0:start;
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(start,limit,sort);
        model.addAttribute("articleList",articleService.listArticle(pageable));
        return "index";
    }


    @GetMapping("/baidu")
    public ModelAndView baidu() {
        return new ModelAndView(new RedirectView("http://www.baidu.com"));
    }




}
