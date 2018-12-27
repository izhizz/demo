package cc.rabbit.controller.pointToPoint;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class RabbitMqConsumer2 implements MessageListener{
    @Override
    public void onMessage(Message message) {
        System.out.println(message.getBody());
    }
}
