package cc.rabbit.controller.pointToPoint;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class RabbitmqConsumer implements MessageListener {

    public void onMessage(Message message) {
        System.out.print("Msg consumed1:");
        System.out.println(new String(message.getBody()));
    }
}
