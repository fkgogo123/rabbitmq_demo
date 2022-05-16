package helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {

        // ConnectionFactory connectionFactory = new ConnectionFactory();
        // connectionFactory.setHost("47.103.99.58");
        // connectionFactory.setPort(5672);
        // connectionFactory.setVirtualHost("/v1");
        // connectionFactory.setUsername("admin");
        // connectionFactory.setPassword("123");
        // Connection connection = connectionFactory.newConnection();
        // System.out.println(connection);

        Connection connection = RabbitMQUtils.getConnection();

        // 获取通道
        Channel channel = connection.createChannel();

        // 通道绑定消息队列
        /**
         * 生成一个队列
         * 1.队列名称
         * 2.队列里面的消息是否持久化 默认消息存储在内存中
         * 3.该队列是否只供一个消费者进行消费 是否进行共享 true 可以多个消费者消费
         * 4.是否自动删除 最后一个消费者端开连接以后 该队列是否自动删除 true 自动删除
         * 5.其他参数
         */
        channel.queueDeclare("hello", false, false, false, null);
        /**
         * 发送一个消息
         * 1.发送到那个交换机
         * 2.路由的 key 是哪个
         * 3.其他的参数信息
         * 4.发送消息的消息体
         */
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
            String msg = "Hello World " + i;
            channel.basicPublish("", "hello", null, msg.getBytes());
        }

        // channel.close();
        // connection.close();
        RabbitMQUtils.closeConnectionAndChanel(channel, connection);
    }
}
