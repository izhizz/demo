package cc.rabbit.controller;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class RabbitmqConsumer implements MessageListener {

    public void onMessage(Message message) {
        System.out.print("Msg consumed:");
        System.out.println(new String(message.getBody()));
    }
}
