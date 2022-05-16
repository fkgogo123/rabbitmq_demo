package topic;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

public class Consumer01 {
    public static void main(String[] args) throws IOException {
        // 获取连接
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        // 声明交换机及交换机类型
        String exchangeName = "logs_topic";
        channel.exchangeDeclare(exchangeName, "topic");
        // 创建一个临时队列
        String queueName = channel.queueDeclare().getQueue();
        // 绑定队列和交换机，使用动态routingKey
        String routingKey = "*.user.*";
        channel.queueBind(queueName, exchangeName, routingKey);
        // 消费消息
        channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-01*: " + new String(body));
            }
        });
    }
}
