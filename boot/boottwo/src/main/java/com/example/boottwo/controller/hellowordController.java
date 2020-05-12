package com.example.boottwo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class hellowordController {

    @RequestMapping("/hello")
    public String hello(){
        return "index";
    }

    @RequestMapping("/getjson")
    public String getjson(){
        return "getjson";
    }
}
