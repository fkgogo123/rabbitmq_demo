package com.example.hello;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

// 监听者，即消费者。
@Component
@RabbitListener(queuesToDeclare = @Queue(value = "hello"))
public class HelloConsumer {

    // 代表得到消息后的回调方法
    @RabbitHandler
    public void receive(String message) {
        System.out.println("message = " + message);
    }

}
