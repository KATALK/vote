package com.seventh.group.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

/**
 * @Author EdiMen
 * @Data 2020/9/19--17:09
 * @Version 1.0
 */

@Controller
public class indexController {

    @GetMapping("/")
    public String index(HttpSession session) {
        session.setAttribute("user",null);
        return "index";
    }


//    @GetMapping("/demo")
//    public String demoTest(){
//        return "demo01";
//    }
    @GetMapping("/baidu")
    public ModelAndView baidu() {
        return new ModelAndView(new RedirectView("http://www.baidu.com"));
    }

    @GetMapping("/click")
    public String click(){
        return "click";
    }
}
