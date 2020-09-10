package cn.yimu;

import cn.yimu.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class testAmqp {
    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Test
    public void amqpAdmin(){
        amqpAdmin.declareExchange(new FanoutExchange("fanout_exchange"));
        amqpAdmin.declareQueue(new Queue("fanout_queue_email"));
        amqpAdmin.declareQueue(new Queue("fanout_queue_sms"));
        amqpAdmin.declareBinding(new Binding("fanout_queue_email",Binding.DestinationType.QUEUE, "fanout_exchange","",null));
        amqpAdmin.declareBinding(new Binding("fanout_queue_sms",Binding.DestinationType.QUEUE, "fanout_exchange","",null));
    }

    @Test
    public void psubPublisher(){
        User u = new User();
        u.setId(1);
        u.setUsername("一木");
        rabbitTemplate.convertAndSend("fanout_exchange","",u);
    }

    //rounting工作模式消息发送端
    @Test
    public void rountPublisher() {
        rabbitTemplate.convertAndSend("routing_exchange","info_routing_key","routing send error message!");
    }
    //topic工作模式消息发送端
    @Test
    public void topicPubilsher() {
        //1只发送邮件订阅用户消息
        //rabbitTemplate.convertAndSend("topic_exchange","info.email","topics send email message");
        //2只发送短信订阅用户消息
       // rabbitTemplate.convertAndSend("topic_exchange","info.sms","topics send sms message");
        //3.都发送
        rabbitTemplate.convertAndSend("topic_exchange","info.email.sms","topic all message");
    }
}
