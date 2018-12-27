package cc.rabbit.controller.pointToPoint;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/rabbit2")
public class RabbitmqController2 {
    @Resource
    private RabbitTemplate amqpTemplate;

    @RequestMapping(value = "/rmq2")
    public void sendMsg(HttpServletResponse response) {

        try {

            for (int i = 0; i < 5; i++){
                amqpTemplate.convertAndSend("test_rmq_exchange", "test_queue_patt2", "rmqProducer-sendMsg.......");
            }
            response.getWriter().write("message2 sended....................");
        } catch (Exception e) {
        }
    }
}
