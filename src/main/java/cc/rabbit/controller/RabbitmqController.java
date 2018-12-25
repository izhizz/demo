package cc.rabbit.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/rabbit")
public class RabbitmqController {
    @Resource
    private RabbitTemplate amqpTemplate;

    @RequestMapping(value = "/rmq")
    public void sendMsg(HttpServletResponse response) {

        try {
            for (int i = 0; i < 5; i++){
                amqpTemplate.convertAndSend("test_rmq_exchange", "test_queue_patt", "rmqProducer-sendMsg.......");
                System.out.println("One Msg sended.....");
            }
            response.getWriter().write("message sended....................");
        } catch (Exception e) {
        }
    }
}
