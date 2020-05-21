package cn.yimu.service;

import cn.yimu.domain.User;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    /*@RabbitListener(queues = "fanout_queue_email")
    public void psubConsumerEmail(Message message){
        byte[] body = message.getBody();
        String s = new String(body);
        System.out.println("邮件业务接受到消息"+s);
    }

    @RabbitListener(queues = "fanout_queue_sms")
    public void psubConsumerSms(Message message){
        byte[] body = message.getBody();
        String s = new String(body);
        System.out.println("短信业务接受到消息"+s);
    }*/

    //1.1使用基于注解的方法实现消息服务
    @RabbitListener(bindings = @QueueBinding(value =
                               @Queue(value = "fanout_queue_email",durable = "true"),
                    exchange = @Exchange(value = "fanout_exchange", type = ExchangeTypes.FANOUT)))
    public void psubConsumerEmail(User user) {
        System.out.println("邮件业务接受到消息"+user);
    }

    //1.2使用基于注解的方法实现消息服务
    @RabbitListener(bindings = @QueueBinding(value =
                               @Queue("fanout_queue_sms"),
                    exchange = @Exchange(value = "fanout_exchange",type = ExchangeTypes.FANOUT)))
    public void psubConsumerSms(User user) {
        System.out.println("短信业务接受到消息"+user);
    }

    //2.1路由模式消息接收、处理error级别的日志信息
    @RabbitListener(bindings = @QueueBinding(value =
                                @Queue("routing_queue_error"),
                                exchange = @Exchange(value = "routing_exchange",type =ExchangeTypes.TOPIC),
                                key = "error_routing_key"))
    public void routingConsumerError(String message) {
        System.out.println("接收到error级别日志消息: "+message);
    }

    //2.2路由模式消息接收、处理info、error、warning级别的日志信息
    @RabbitListener(bindings = @QueueBinding(value =
                                @Queue("routing_queue_all"),exchange =
                                @Exchange(value ="routing_exchange",type = "direct"),
                                key = {"error_routing_key","info_routing_key","warning_routing_key"}))
    public void rountConsumerAll(String message) {
        System.out.println("接收到info、erroe、warning等级别日志信息: "+message);
    }

    //3.1通配符模式消息接收、进行邮件业务订阅处理
    @RabbitListener(bindings = @QueueBinding(value =
                                @Queue("topic_queue_email"),exchange =
                                @Exchange(value = "topic_exchange",type = "topic"),
                                key = "info.#.email.#"))
    public void topicConsumerEmail(String message) {
        System.out.println("接收到邮件订阅需求处理消息: "+message);
    }

    //3.2通配符模式消息接收、进行短信业务订阅处理
    @RabbitListener(bindings = @QueueBinding(value =
                                @Queue("topic_queue_sms"),exchange =
                                @Exchange(value = "topic_exchange",type = "topic"),
                                key = "info.#.sms.#"))
    public void topicConsumerSms(String message) {
        System.out.println("接收到短信订阅需求处理消息: "+message);
    }
}
