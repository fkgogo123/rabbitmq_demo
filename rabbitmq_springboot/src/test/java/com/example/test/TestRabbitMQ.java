package com.example.test;

import ch.qos.logback.classic.Logger;
import com.example.RabbitmqSpringbootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = RabbitmqSpringbootApplication.class)
@RunWith(SpringRunner.class)
public class TestRabbitMQ {

    // 注入rabbitmqTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 点对点模型
     */
    @Test
    public void testHelloWorld() {
        rabbitTemplate.convertAndSend("hello", "hello world");
        System.out.println("发送完成");

    }

    /**
     * 工作队列模型
     */
    @Test
    public void testWorkQueue() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work", "work模型" + i);
        }
        System.out.println("发送完成");
    }

    /**
     * 发布订阅模型，广播模型
     */
    @Test
    public void testFanout() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("logs","", "fanout模型消息" + i);
        }
        System.out.println("发送完毕");
    }

    /**
     * 路由模型
     */
    @Test
    public void testRoute() {
        rabbitTemplate.convertAndSend("directs", "info", "发送的info路由消息");
        rabbitTemplate.convertAndSend("directs", "error", "发送的error路由消息");
    }

    /**
     * 动态路由模型，topic模型
     */
    @Test
    public void testTopic() {
        String routingKey = "order.save";
        rabbitTemplate.convertAndSend("topics", routingKey, "动态路由发送的消息: " + routingKey);
    }
}
