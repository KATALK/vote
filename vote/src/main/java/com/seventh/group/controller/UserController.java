package com.seventh.group.controller;


import com.seventh.group.Entity.User;
import com.seventh.group.service.UserService;

import com.seventh.group.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @Author EdiMen
 * @Data 2020/9/10--19:52
 * @Version 1.0
 */
@Controller
@RequestMapping("/user.do")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 跳转到注册页面
     *
     * @return
     */

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/toLogin")
    public String login() {
        return "login";
    }

    @GetMapping("/toRegister")
    public String toAddUserPage() {
        return "signIn";
    }

    /**
     * 用户注册
     *
     * @param username
     * @param password
     * @param session
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/toAddUser")
    public String toAddUser(@RequestParam("username") String username, @RequestParam("password") String password,
                            HttpSession session, RedirectAttributes redirectAttributes) {
        if ("".equals(username) || "".equals(password) || username == null || password == null) {
            redirectAttributes.addFlashAttribute("msg", "用户名或密码为空重重新注册");
            return "redirect:/user.do/toRegister";
        }
        boolean flag = userService.checkRegisterByUsername(username);
        if (flag) {
            redirectAttributes.addFlashAttribute("msg", "用户名已经存在，请换个名字");
            return "redirect:/user.do/toRegister";
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(MD5Util.encode(password, "utf-8"));
        userService.register(user);
        user.setPassword(null);//密码不能传到前端，防止泄密
        session.setAttribute("user", user);
        return "redirect:/user.do/index";//redirect:/admin
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
                        HttpSession session, RedirectAttributes redirectAttributes) {
        if ("".equals(username) || "".equals(password) || username == null || password == null) {
            redirectAttributes.addFlashAttribute("msg", "用户名或密码为空,请重新登录");
            return "redirect:/user.do/toLogin";
        }
        User user = userService.loginCheck(username, MD5Util.encode(password,"utf-8"));
        if (null!=user){
            user.setPassword(null);//密码不能上传代前端
            session.setAttribute("user",user);
            return "redirect:/user.do/index";
        }else {
            redirectAttributes.addFlashAttribute("msg","账户或密码错误，请重新输入.......");
            return "redirect:/user.do/toLogin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        session.setAttribute("user",null);
        return "index";
    }

}

