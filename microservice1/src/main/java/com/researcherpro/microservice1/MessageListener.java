package com.researcherpro.microservice1;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = RabbitMQConfig.DATA_QUEUE)
    public void listener(RabbitMQMessage rabbitMQMessage){
        System.out.println(rabbitMQMessage);
    }
}
