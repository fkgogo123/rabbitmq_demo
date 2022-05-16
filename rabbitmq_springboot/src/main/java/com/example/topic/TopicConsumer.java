package com.example.topic;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicConsumer {

    @RabbitListener(bindings =
        @QueueBinding(
                value = @Queue,
                exchange = @Exchange(value = "topics", type = "topic"),
                key = {"user.save", "user.*",}
        )
    )
    public void reveive1(String message) {
        System.out.println("message1 = " + message);
    }

    @RabbitListener(bindings =
    @QueueBinding(
            value = @Queue,
            exchange = @Exchange(value = "topics", type = "topic"),
            key = {"order.#", "product.#" ,"user.*",}
    )
    )
    public void reveive2(String message) {
        System.out.println("message2 = " + message);
    }
}
