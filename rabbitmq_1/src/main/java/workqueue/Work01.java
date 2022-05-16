package workqueue;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

public class Work01 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        // 设置预取值（允许的未收到应答的消息缓冲区大小）
        channel.basicQos(1);
        channel.queueDeclare("work", true, false, false, null);

        channel.basicConsume("work", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-1:" + new String(body));
                // 手动确认
                channel.basicAck(envelope.getDeliveryTag(), false);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        // RabbitMQUtils.closeConnectionAndChanel(channel, connection);
    }
}
