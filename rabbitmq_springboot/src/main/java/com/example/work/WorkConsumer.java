package com.example.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WorkConsumer {

    // 一个消费者
    @RabbitListener(queuesToDeclare = @Queue(value = "work"))
    public void receive01(String msg) {
        System.out.println("message1 = " + msg);
    }

    // 一个消费者
    @RabbitListener(queuesToDeclare = @Queue(value = "work"))
    public void receive02(String msg) {
        System.out.println("message2 = " + msg);
    }
}
