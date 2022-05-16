package direct;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

public class Consumer02 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        String exchangeName = "logs_direct";

        channel.exchangeDeclare(exchangeName, "direct");

        String queueName = channel.queueDeclare().getQueue();

        channel.queueBind(queueName, exchangeName, "info");
        channel.queueBind(queueName, exchangeName, "error");
        channel.queueBind(queueName, exchangeName, "warning");

        channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-2 :" + new String(body));
            }
        });
    }
}
