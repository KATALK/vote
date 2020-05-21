package com.example.demo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/error")
public class TestErrorController implements ErrorController {
    @Override
    public String getErrorPath() {
        return null;
    }

    @RequestMapping
    public Map<String,Object> handleError() {
        Map<String,Object> map = new HashMap<>();
        map.put("code",404);
        map.put("msg","不存在");
        return map;
    }

    @RequestMapping("ok")
    public Map<String,Object> noError() {
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","正常，者是测试页面");
        return map;
    }

    @RequestMapping(value = "/html",produces = "text/html;charset=UTF-8")
    public String errorHtml(HttpServletRequest request, HttpServletResponse response) {
        return "404错误，不存在";
    }

    @RequestMapping(value = "/html")
    public Map<String,Object> errorJson() {
        Map<String,Object> map = new HashMap<>();
        map.put("code",404);
        map.put("msg","不存在");
        return map;
    }
}
