package direct;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

public class Consumer01 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        String exchangeName = "logs_direct";

        // 通道声明交换机及类型
        channel.exchangeDeclare(exchangeName, "direct");
        // 创建临时队列
        String queueName = channel.queueDeclare().getQueue();
        // 通道绑定队列和交换机
        channel.queueBind(queueName, exchangeName, "error");

        // 消费消息
        channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-1 :" + new String(body));
            }
        });

    }
}
