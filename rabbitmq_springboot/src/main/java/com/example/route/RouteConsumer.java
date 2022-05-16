package com.example.route;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RouteConsumer {

    @RabbitListener(bindings =
        @QueueBinding(
                value = @Queue,  // 临时队列
                exchange = @Exchange(value = "directs", type = "direct"),
                key = {"info", "error", "warning"}
        )
    )
    public void reveive1(String message) {
        System.out.println("message1 = " + message);
    }

    @RabbitListener(bindings =
    @QueueBinding(
            value = @Queue,  // 临时队列
            exchange = @Exchange(value = "directs", type = "direct"),
            key = {"error"}
    )
    )
    public void reveive2(String message) {
        System.out.println("message2 = " + message);
    }
}
