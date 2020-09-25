package com.example.demo;

import com.example.demo.activer.producer;
import com.example.demo.pojo.user;
import com.example.demo.service.userService;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * @Author EdiMen
 * @Data 2020/9/24--15:56
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class demo {

    @Autowired
    private userService us;

    @Resource
    private producer p;
    @Test
    public void test01(){
//        Destination destination = new ActiveMQQueue("yimu.queue");//创建消息队列
//        p.setJmsMessagingTemplate(destination,"1块钱");
        user u = new user();
        u.setContent("haha");
        u.setId(1);
        u.setUsername("i");
        String s = us.asynSave(u);
        System.out.println(s);


    }
}
