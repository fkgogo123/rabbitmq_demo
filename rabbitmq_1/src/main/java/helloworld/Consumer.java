package helloworld;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.util.Timer;

public class Consumer {
    public static void main(String[] args) throws Exception {
        // ConnectionFactory connectionFactory = new ConnectionFactory();
        // connectionFactory.setHost("47.103.99.58");
        // connectionFactory.setPort(5672);
        // connectionFactory.setVirtualHost("/v1");
        // connectionFactory.setUsername("admin");
        // connectionFactory.setPassword("123");
        // Connection connection = connectionFactory.newConnection();

        Connection connection = RabbitMQUtils.getConnection();

        Channel channel = connection.createChannel();
        // 通道绑定队列
        channel.queueDeclare("hello", false, false, false, null);

        /**
         * 消费者消费消息
         * 1.消费哪个队列
         * 2.消费成功之后是否要自动应答 true 代表自动应答 false 手动应答
         * 3.消费者未成功消费的回调
         */

        //推送的消息如何进行消费的接口回调
        DeliverCallback deliverCallback=(consumerTag, delivery)->{
            String message= new String(delivery.getBody());
            System.out.println("消费消息:" + message);
        };
        //取消消费的一个回调接口 如在消费的时候队列被删除掉了
        CancelCallback cancelCallback=(consumerTag)->{
            System.out.println("消息消费被中断");
        };
        /**
         * 消费者消费消息
         * 1.消费哪个队列
         * 2.消费成功之后是否要自动应答 true 代表自动应答 false 手动应答
         * 3.消费者未成功消费的回调
         */
        channel.basicConsume("hello", true, deliverCallback, cancelCallback);

        // channel.close();
        // connection.close();

        // RabbitMQUtils.closeConnectionAndChanel(channel, connection);
    }
}
