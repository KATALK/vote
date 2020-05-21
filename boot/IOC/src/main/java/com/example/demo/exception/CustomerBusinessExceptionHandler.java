package com.example.demo.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomerBusinessExceptionHandler {
    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public Map<String ,Object> businessExceptionHandler(BusinessException e) {
        Map<String,Object> map = new HashMap<>();
        map.put("code",e.getCode());
        map.put("message",e.getMessage());
        return map;
    }

}
