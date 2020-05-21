package cn.yimu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class SecurityApplicationTests {
    @Autowired
    private AmqpAdmin amqpAdmin;


//    @Test
//    public void amqpAdmin(){
//        amqpAdmin.declareExchange(new FanoutExchange("fanout_exchange"));
//        amqpAdmin.declareQueue(new Queue("fanout_queue_email"));
//        amqpAdmin.declareQueue(new Queue("fanout_queue_sms"));
//        amqpAdmin.declareBinding(new Binding("fanout_queue_email",Binding.DestinationType.QUEUE, "fanout_exchange","",null));
//        amqpAdmin.declareBinding(new Binding("fanout_queue_sms",Binding.DestinationType.QUEUE, "fanout_exchange","",null));
//    }

}
