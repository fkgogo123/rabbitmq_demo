package workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

import java.io.IOException;

public class Task {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work", true, false, false, null);

        for (int i = 0; i < 20; i++) {
            // 生产消息
            channel.basicPublish("", "work", null, (i + "-hello work queuq").getBytes());
            System.out.println("发送成功-" + i);
        }

        RabbitMQUtils.closeConnectionAndChanel(channel, connection);

    }
}
